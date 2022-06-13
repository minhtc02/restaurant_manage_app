package com.example.restaurant_manager_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurant_manager_app.Api.ApiGetData;
import com.example.restaurant_manager_app.Interface.GetData;
import com.example.restaurant_manager_app.Model.Admin;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class login_activity extends AppCompatActivity implements GetData {
    EditText ed_user, ed_pass;
    Button btn_login;
    ArrayList<Admin> list;
    Admin admin;
    String tableName = "getDataAdmin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user = findViewById(R.id.ed_username_login);
        ed_pass = findViewById(R.id.ed_pass_login);
        btn_login = findViewById(R.id.button_login);

        init();
        new ApiGetData(tableName,this).execute();

        btn_login.setOnClickListener(v -> {
            if(ed_user.getText().toString().trim() == "" || ed_pass.getText().toString().trim() == ""){
                Toast.makeText(this, "Bạn phải điền đầy đủ tài khoản và mật khẩu !!!", Toast.LENGTH_SHORT).show();
            }
            else if(ed_user.getText().toString().trim().equals(admin.getUsername().trim()) && ed_pass.getText().toString().trim().equals(admin.getPassword().trim())){
                Log.d("TAG", "onCreate: " + admin.getUsername() + admin.getPassword());
                startActivity(new Intent(this, Main_Admin.class));
            }else{
                Log.d("TAG", "onCreate: " + admin.getUsername() + " " + admin.getPassword());
                Toast.makeText(this , "Thông tin đăng nhập sai !!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
    }

    @Override
    public void start() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void finish(String data) {
        try {
            list.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                admin = new Admin(o);
                list.add(admin);
                Log.d("TAG", "finish: " + admin.getUsername() + " - " + admin.getPassword());
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

}