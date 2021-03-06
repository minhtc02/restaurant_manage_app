package com.example.restaurant_manager_app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurant_manager_app.Activity.MainActivity;
import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.R;

public class AddTableFragment extends Fragment implements RunSql {
    View view;
    MainActivity mMainActivity;
    EditText edName, edFloor, edStatus;
    Button btnAdd;
    ImageView imgBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_add_table, container, false);


        init();
        mapping();
        updateView();
        setClick();
        return view;
    }

    private void init() {
        mMainActivity = (MainActivity) getActivity();

    }

    private void mapping() {
        edName = view.findViewById(R.id.edName);
        edFloor = view.findViewById(R.id.edFloor);
        edStatus = view.findViewById(R.id.edStatus);
        btnAdd = view.findViewById(R.id.btnAdd);
        imgBack = view.findViewById(R.id.imgBack);
    }

    private void setClick() {
        btnAdd.setOnClickListener(v -> add());
        imgBack.setOnClickListener(view -> back());
    }

    private void back() {
        mMainActivity.replaceFragmentSetting();
    }

    private void updateView() {
    }

    private void noitifyR(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("" + message);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private boolean validate() {
        String name = edName.getText().toString();
        String floor = edFloor.getText().toString();
        String status = edStatus.getText().toString();
        if (name.equals("") || floor.equals("") || status.equals("")) {
            String m = "B???n ph???i nh???p ?????y ????? th??ng tin";
            noitifyR(m);
            return false;
        }
        return true;
    }

    private void add() {
        String name = edName.getText().toString();
        String floor = edFloor.getText().toString();
        String status = edStatus.getText().toString();
        String sql = "INSERT INTO `tables` (`id`, `name`, `floor`, `status`) VALUES (NULL, '" +
                name +
                "', '" +
                floor +
                "', '" +
                status +
                "')";
        if (validate()) {
            new ApiRunSql(sql, this).execute();
        }
    }


    @Override
    public void start() {
        //Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        //Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Ho??n th??nh");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}