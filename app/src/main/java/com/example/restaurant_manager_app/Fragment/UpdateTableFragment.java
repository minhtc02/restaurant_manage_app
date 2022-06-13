package com.example.restaurant_manager_app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;


public class UpdateTableFragment extends Fragment implements RunSql {
    View view;
    public static final String TAG = MainActivity.class.getSimpleName();
    MainActivity mMainActivity;
    EditText edID,edName, edFloor, edStatus;
    Button btnUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_update_table, container, false);


        init();
        mapping();
        setUp();
        setClick();
        return view;
    }

    private void init() {
    }

    private void mapping() {
        edID = view.findViewById(R.id.edID);
        edName = view.findViewById(R.id.edName);
        edFloor = view.findViewById(R.id.edFloor);
        edStatus = view.findViewById(R.id.edStatus);
        btnUpdate = view.findViewById(R.id.btnUpdate);
    }

    private void setClick() {
        btnUpdate.setOnClickListener(v -> {
            updateDish();
        });
    }

    private void setUp() {
    }

    private void updateDish() {
        String id = edID.getText().toString();
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
        Log.i(TAG,sql);
        new ApiRunSql(sql, this).execute();
    }


    @Override
    public void start() {
        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}