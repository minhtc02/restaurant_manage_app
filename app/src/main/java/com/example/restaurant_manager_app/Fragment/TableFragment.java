package com.example.restaurant_manager_app.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restaurant_manager_app.Adapter.TableAdapter;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.Model.Table;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TableFragment extends Fragment implements GetData, RunSql {
    ListView listView;
    TableAdapter adapter;
    ArrayList<Table> list;
    Table table;
    View view;
    String tableName = "getDataTable.php";
    CartDAO dao;
    Dish dish;
    SwipeRefreshLayout mySwipeRefreshLayout;
    Dialog dialog;
    EditText edId, edName, edFloor, edStatus;
    Button btnSave, btnCancel;
    Table item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_table, container, false);
        init();
        mapping();
        new ApiGetData(tableName, this).execute();
        updateView();
        setClick();
        mySwipeRefreshLayout.setOnRefreshListener(
                () -> myUpdateOperation()
        );
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        dao = new CartDAO(getContext());
    }

    public void myUpdateOperation() {
        new ApiGetData(tableName, this).execute();
        mySwipeRefreshLayout.setRefreshing(false);
        updateView();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvTable);
        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
    }

    private void setClick() {
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            dao = new CartDAO(getContext());
            table = list.get(position);
            dish = new Dish();
            dish.setId("ban" + table.getId());
            dish.setName(table.getName() + " / " + table.getFloor());
            dish.setDescribe("10 điểm-");
            dish.setVote("5");
            dish.setPrice("50000");
           // dish.setImage("https://bizweb.dktcdn.net/100/121/953/products/ban-ghe-nha-hang-174350.jpg?v=1505367058920");

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            if (table.getStatus().equals("Trống")) {
                builder.setMessage("Bạn muốn đặt bàn này ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Đúng", (dialog, which) -> {
                    String ids = dish.getName();
                    if (dao.checkExistsCart(ids) < 0) {
                        dialog.cancel();
                        existCart();
                    } else {
                        dao.insert(dish);
                        dialog.cancel();
                        addToCart();
                    }
                });
                builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());
            } else {
                builder.setMessage("Rất xin lỗi!! Bàn đã được đặt...");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
            }
            builder.show();
        });
    }

    private void existCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bàn đã được thêm, vui lòng kiểm tra giỏ hàng");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void addToCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đặt bàn thành công");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void deleteR(String id) {
        String sql = "DELETE FROM `tables` WHERE `tables`.`id` = " +
                "" +
                id + "";
        new ApiRunSql(sql, this).execute();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã xóa");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                scNotify();
            }
        });
        builder.show();
    }
    public void updateR(Table table) {
        item = table;
        openDialog(getActivity());
    }

    protected void openDialog(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_table);
        edId = dialog.findViewById(R.id.edID);
        edName = dialog.findViewById(R.id.edName);
        edFloor = dialog.findViewById(R.id.edFloor);
        edStatus = dialog.findViewById(R.id.edStatus);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSave);
        edId.setEnabled(false);
        edId.setText(item.getId());
        edName.setText(item.getName());
        edFloor.setText(item.getFloor());
        edStatus.setText(item.getStatus());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(v -> {
            String id = edId.getText().toString();
            String name = edName.getText().toString();
            String floor = edFloor.getText().toString();
            String status = edStatus.getText().toString();
            String sql = "UPDATE `tables` SET `name` = '" +
                    name+
                    "', `floor` = '" +
                    floor+
                    "', `status` = '" +
                    status+
                    "' WHERE `tables`.`id` = " +
                    id+"";
            new ApiRunSql(sql, this).execute();
            dialog.dismiss();
        });
        dialog.show();
    }
    public void setOn(String id){
        String sql = "UPDATE `tables` SET `status` = 'Trống' WHERE `tables`.`id` = " +
                id+"";
        new ApiRunSql(sql, this).execute();
        scNotify();

    }
    public void setOff(String id){
        String sql = "UPDATE `tables` SET `status` = 'Đã đặt' WHERE `tables`.`id` = " +
                id+"";
        new ApiRunSql(sql, this).execute();
        scNotify();
        Log.d("TAG", "onCreate: " +sql);
    }

    private void scNotify(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Hoàn thành");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                myUpdateOperation();
            }
        });
        builder.show();
    }


    private void updateView() {
        adapter = new TableAdapter(getContext(), this, list);
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
                Table table = new Table(o);
                list.add(table);
                updateView();
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        //Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}