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

public class AddStaffFragment extends Fragment implements RunSql {

    View view;
    MainActivity mMainActivity;
    EditText edName, edUsername, edPassword, edPhoneNum, edEmail, edImage;
    Button btnAdd;
    ImageView imgBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_add_staff, container, false);


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
        edUsername = view.findViewById(R.id.edUsername);
        edPassword = view.findViewById(R.id.edPassword);
        edPhoneNum = view.findViewById(R.id.edPhoneNum);
        edEmail = view.findViewById(R.id.edEmail);
        edImage = view.findViewById(R.id.edImage);
        btnAdd = view.findViewById(R.id.btnAdd);
        imgBack = view.findViewById(R.id.imgBack);
    }

    private void setClick() {
        btnAdd.setOnClickListener(v -> add());
        imgBack.setOnClickListener(v -> mMainActivity.replaceFragmentSetting());

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
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String phoneNum = edPhoneNum.getText().toString();
        String email = edEmail.getText().toString();
        String image = edImage.getText().toString();
        if (name.equals("") || username.equals("") || password.equals("") || phoneNum.equals("") || email.equals("") || image.equals("")) {
            String m = "Bạn phải nhập đầy đủ thông tin";
            noitifyR(m);
            return false;
        }
        return true;
    }

    private void add() {
        String name = edName.getText().toString();
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String phoneNum = edPhoneNum.getText().toString();
        String email = edEmail.getText().toString();
        String image = edImage.getText().toString();
        String sql = "INSERT INTO `account` (`id`, `permission`, `username`, `password`, `name`, `phoneNum`,`email`, `image`) VALUES (NULL, 'staff', '" +
                username +
                "', '" +
                password +
                "', '" +
                name +
                "', '" +
                phoneNum +
                "', '" +
                email +
                "', '" +
                image +
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
        builder.setMessage("Hoàn thành");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
    }


}