package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.DishAdapter;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Database.CartDAO;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Interface.OnClickItemDish;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DishFragment extends Fragment implements GetData {
    ListView listView;
    DishAdapter adapter;
    ArrayList<Dish> list;
    View view;
    MainActivity mMainActivity;
    String tableName = "getDataDish.php";
    CartDAO dao;
    OnClickItemDish onClickItemDish;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickItemDish) {
            onClickItemDish = (OnClickItemDish) context;
        } else {
            throw new RuntimeException(context.toString() + "Can phai implement");
        }
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

        return view;
    }


    private void init() {
        dao = new CartDAO(getContext());
        list = new ArrayList<>();
        mMainActivity = (MainActivity) getActivity();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvDish);

    }



    public void setClick(Dish dish) {
//        listView.setOnItemClickListener((parent, view1, position, id) -> {
//            dish = list.get(position);
        //onClickItemDish.onClickItem(dish);
        mMainActivity.replaceFragment();
//        });
    }

    public void addToCart(final Dish dish) {
        dao.insert(dish);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Đã thêm vào giỏ hàng");
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

    private void updateView() {
        adapter = new DishAdapter(getContext(), this, list, onClickItemDish);
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