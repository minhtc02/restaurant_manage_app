package com.example.restaurant_manager_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurant_manager_app.Model.Admin;

public class login_activity extends AppCompatActivity {
    EditText ed_user, ed_pass;
    Button btn_login;
    Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user = findViewById(R.id.ed_username_login);
        ed_pass = findViewById(R.id.ed_pass_login);
        btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(v -> {
            if(ed_user.getText().toString() == admin.getUsername() && ed_pass.getText().toString() == admin.getPass()){
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Toast.makeText(this , "SomeThing Wrong !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}