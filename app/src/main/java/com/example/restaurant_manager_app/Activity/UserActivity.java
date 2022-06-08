package com.example.restaurant_manager_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.restaurant_manager_app.R;

public class UserActivity extends AppCompatActivity {
    Button btn_log , btn_res, btn_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_log = findViewById(R.id.btn_dangNhap);
        btn_res = findViewById(R.id.btn_dangKy);
        btn_out = findViewById(R.id.btn_dang_xuat);

        btn_log.setOnClickListener(v -> {
            startActivity(new Intent(this, login_activity.class));
        });

        btn_res.setOnClickListener(v -> {
            // res
        });

        btn_out.setOnClickListener(v -> {
            // out
        });
    }
}