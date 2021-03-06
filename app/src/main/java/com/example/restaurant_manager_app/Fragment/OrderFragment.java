package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.OrderAdapter;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Order;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OrderFragment extends Fragment implements GetData, RunSql {
    ListView listView;
    OrderAdapter adapter;
    ArrayList<Order> list;
    Order item;
    View view;
    MainActivity mMainActivity;
    String tableName = "getDataOrder.php";
    SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_order, container, false);

        init();
        mapping();
        new ApiGetData(tableName, this).execute();
        updateView();
        mySwipeRefreshLayout.setOnRefreshListener(
                this::myUpdateOperation
        );
        return view;
    }

    public void myUpdateOperation() {
        new ApiGetData(tableName, this).execute();
        mySwipeRefreshLayout.setRefreshing(false);
        updateView();
    }

    public OrderFragment() {
    }

    private void init() {
        list = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvOrder);
        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
    }

    public void helper() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Vui l??ng li??n h??? : 012345678" + "\n" +
                "????? ???????c h??? tr??? s???m nh???t c?? th??? ");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void deleteR(String id) {
        String sql = "DELETE FROM `orders` WHERE `orders`.`id` = " +
                "" +
                id + "";
        new ApiRunSql(sql, this).execute();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("???? x??a");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.cancel();
            myUpdateOperation();
        });
        builder.show();
    }

    public void accept(Order order) {
        item = order;
        String id = item.getId();
        String status = item.getStatus();

        if (status.equals("??ang x??? l??")) {
            String sql = "UPDATE `orders` SET `status` = '??ang ti???n h??nh' WHERE `orders`.`id` = " +
                    id + "";
            new ApiRunSql(sql, this).execute();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("????n h??ng ???? ???????c x??c nh???n");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            builder.show();
        }
        myUpdateOperation();
    }

    private void updateView() {
        adapter = new OrderAdapter(getContext(), this, list);
        listView.setAdapter(adapter);
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {

    }

    @Override
    public void finish(String data) {
        try {
            list.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Order order = new Order(o);
                list.add(order);
                updateView();
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}