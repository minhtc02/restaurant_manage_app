package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.DishAdapter;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DishFragment extends Fragment implements GetData, RunSql {
    ListView listView;
    DishAdapter adapter;
    ArrayList<Dish> list;
    View view;
    MainActivity mMainActivity;
    String tableName = "getDataDish.php";
    CartDAO dao;
    SwipeRefreshLayout mySwipeRefreshLayout;
    Dialog dialog;
    EditText edId, edName, edDescribe, edVote, edPrice, edImage;
    Button btnSave, btnCancel, btn_choseimg;
    ImageView img_item;
    Dish item;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public DishFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_dish, container, false);

        init();
        mapping();
        new ApiGetData(tableName, this).execute();
        updateView();
        mySwipeRefreshLayout.setOnRefreshListener(
                this::myUpdateOperation
        );
        return view;
    }


    private void init() {
        dao = new CartDAO(getContext());
        list = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }

    public void myUpdateOperation() {
        new ApiGetData(tableName, this).execute();
        mySwipeRefreshLayout.setRefreshing(false);
        updateView();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvDish);
        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);

    }

    public void addToCart(final Dish dish) {
        dao.insert(dish);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã thêm vào giỏ hàng");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void deleteR(String id) {
        String sql = "DELETE FROM `dish` WHERE `dish`.`id` = " +
                "" +
                id + "";
        new ApiRunSql(sql, this).execute();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã xóa");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.cancel();
            myUpdateOperation();
        });
        builder.show();
    }

    public void updateR(Dish dish) {
        item = dish;
        openDialog(getActivity());
    }

    protected void openDialog(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_dish);
        edId = dialog.findViewById(R.id.edID);
        edName = dialog.findViewById(R.id.edName);
        edDescribe = dialog.findViewById(R.id.edDescribe);
        edVote = dialog.findViewById(R.id.edVote);
        edPrice = dialog.findViewById(R.id.edPrice);
        edImage = dialog.findViewById(R.id.edImage);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSave);
        edId.setEnabled(false);
        edId.setText(item.getId());
        edName.setText(item.getName());
        edDescribe.setText(item.getDescribe());
        edVote.setText(item.getVote());
        edPrice.setText(item.getPrice());
        edImage.setText(item.getImage());

        img_item = dialog.findViewById(R.id.img_item);
        btn_choseimg = dialog.findViewById(R.id.btn_chonanh);

        Picasso.get().load(item.getImage()).into(img_item);

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnSave.setOnClickListener(v -> {
            String id = edId.getText().toString();
            String name = edName.getText().toString();
            String describe = edDescribe.getText().toString();
            String vote = edVote.getText().toString();
            String price = edPrice.getText().toString();
            String image = edImage.getText().toString();
            String sql = "UPDATE `dish` SET `name` = '" +
                    name +
                    "', `describes` = '" +
                    describe +
                    "', `vote` = '" +
                    vote +
                    "', `price` = '" +
                    price +
                    "', `image` = '" +
                    image +
                    "' WHERE `dish`.`id` = " +
                    id + "";
            new ApiRunSql(sql, this).execute();
            dialog.dismiss();
        });
        dialog.show();
    }

    private void updateView() {
        adapter = new DishAdapter(getContext(), this, list);
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
                Dish dish = new Dish(o);
                list.add(dish);
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