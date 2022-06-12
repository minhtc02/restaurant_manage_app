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
import com.example.restaurant_manager_app.R;


public class DeleteStaffFragment extends Fragment implements RunSql {

    public static final String TAG = MainActivity.class.getSimpleName();
    View view;
    MainActivity mMainActivity;
    EditText edID;
    Button btnDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_delete_staff, container, false);


        init();
        mapping();
        updateView();
        setClick();
        return view;
    }

    private void init() {

    }

    private void mapping() {
        edID = view.findViewById(R.id.edID);
        btnDelete = view.findViewById(R.id.btnDelete);
    }

    private void setClick() {
        btnDelete.setOnClickListener(v -> {
            deleteDish();
        });
    }

    private void updateView() {
    }

    private void deleteDish() {
        String id = edID.getText().toString();
        String sql = "DELETE FROM `staff` WHERE `staff`.`id` = " +
                "" +
                id+"";
        Log.i(TAG, sql);
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