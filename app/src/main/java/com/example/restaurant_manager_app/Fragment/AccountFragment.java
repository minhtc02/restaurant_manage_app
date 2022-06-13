package com.example.restaurant_manager_app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.login_activity;
import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Model.Admin;
import com.example.restaurant_manager_app.Model.Table;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountFragment extends Fragment implements GetData {
    ArrayList<Admin> list;
    Admin admin;
    Button btn_log, btn_res, btn_out;
    String tableName = "getDataAdmin.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.activity_user, container, false);
        btn_log = view.findViewById(R.id.btn_dangNhap);
        btn_log.setOnClickListener(v-> {
            startActivity(new Intent(getContext(), login_activity.class));
        });
        init();
        new ApiGetData(tableName,this).execute();

        return view;
    }

    private void init() {
        list = new ArrayList<>();
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
                Admin admin = new Admin(o);
                list.add(admin);
                Log.d("TAG", "finish: " + admin.getUsername() + " - " + admin.getPassword());
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }

}