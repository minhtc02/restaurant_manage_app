package com.example.restaurant_manager_app.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurant_manager_app.Activity.MainActivity;
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
import java.util.Calendar;

public class TableFragment extends Fragment implements GetData {
    ListView listView;
    TableAdapter adapter;
    ArrayList<Table> list;
    Table table;
    View view;
    MainActivity mMainActivity;
    String tableName = "getDataTable.php";
    CartDAO dao;
    Dish dish;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_table, container, false);
        init();
        mapping();
        new ApiGetData(tableName,this).execute();
        updateView();
        setClick();
        return view;
    }
    private void init() {
        list = new ArrayList<>();
        dao = new CartDAO(getContext());
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvTable);
    }

    private void setClick() {
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            dao = new CartDAO(getContext());
            table  = list.get(position);
            dish = new Dish();
            dish.setId("ban"+table.getId());
            dish.setName(table.getName()+" / "+table.getFloor());
            dish.setDescribe("10 điểm-");
            dish.setVote("5");
            dish.setPrice("50000");
            dish.setImage("https://bizweb.dktcdn.net/100/121/953/products/ban-ghe-nha-hang-174350.jpg?v=1505367058920");
            if (table.getStatus().equals("Trống")){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn muốn đặt bàn này ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Đúng", (dialog, which) -> {
                    String ids = dish.getId();
                    if (dao.checkExistsManga(ids) > 0){
                        dao.insert(dish);
                        Toast.makeText(getActivity(), "Đặt bàn thành công", Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();

                });
                builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());
                builder.show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Rất xin lỗi!! Bàn đã được đặt...");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert  = builder.create();
                builder.show();
            }

            //Toast.makeText(getActivity(), "Loading"+table.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void updateView() {
        adapter = new TableAdapter(getContext(), 0, list);
        listView.setAdapter(adapter);
    }


    @Override
    public void start() {
        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }



}