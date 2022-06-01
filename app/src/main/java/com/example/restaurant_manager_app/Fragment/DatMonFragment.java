package com.example.restaurant_manager_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Adapter.DishAdapter;
import com.example.restaurant_manager_app.Api.ApiGetDish;
import com.example.restaurant_manager_app.Interface.GetDish;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DatMonFragment extends Fragment implements GetDish {
    ListView listView;
    DishAdapter adapter;
    ArrayList<Dish> list;
    Dish dish;
    View view;
    MainActivity mMainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_dish, container, false);
        init();
        mapping();
        new ApiGetDish(this).execute();
        updateView();
        setClick();
               return view;
    }
    private void init() {
        list = new ArrayList<>();
    }

    private void mapping() {
        listView = view.findViewById(R.id.lvDish);
    }

    private void setClick() {
//        listView.setOnItemClickListener((parent, view1, position, id) -> {
//            dish = list.get(position);
//            mMainActivity.replaceFragment(dish);
//        });
    }

    private void updateView() {
        adapter = new DishAdapter(getContext(), 0, list);
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