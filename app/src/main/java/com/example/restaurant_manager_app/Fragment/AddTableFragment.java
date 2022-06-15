package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.Model.Dish;
import com.example.restaurant_manager_app.R;

public class AddTableFragment extends Fragment implements RunSql {

    public static final String TAG = MainActivity.class.getSimpleName();
    View view;
    MainActivity mMainActivity;
    EditText edName,edFloor,edStatus;
    Button btnAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_add_table, container, false);



        init();
        mapping();
        updateView();
        setClick();
        return view;
    }
    private void init() {


    }

    private void mapping() {
        edName  = view.findViewById(R.id.edName);
        edFloor = view.findViewById(R.id.edFloor);
        edStatus  = view.findViewById(R.id.edStatus);
        btnAdd = view.findViewById(R.id.btnAdd);
    }

    private void setClick() {
        btnAdd.setOnClickListener(v -> {
            add();
        });
    }

    private void updateView() {
    }
    private void add() {
        String name = edName.getText().toString();
        String floor =  edFloor.getText().toString();
        String status = edStatus.getText().toString();
        String sql = "INSERT INTO `tables` (`id`, `name`, `floor`, `status`) VALUES (NULL, '" +
                name+
                "', '" +
                floor+
                "', '" +
                status+
                "')";
        new ApiRunSql(sql,this).execute();
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        //Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Hoàn thành");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }



}