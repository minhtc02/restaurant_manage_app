package com.example.restaurant_manager_app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_manager_app.Api.ApiRunSql;
import com.example.restaurant_manager_app.Interface.RunSql;
import com.example.restaurant_manager_app.R;

public class Register_Activity extends AppCompatActivity implements RunSql {
    EditText edName, edUsername, edPassword, edPhoneNum, edEmail, edImage;
    Button btnRegister;
    TextView btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edName = findViewById(R.id.edName);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edPhoneNum = findViewById(R.id.edPhoneNum);
        edEmail = findViewById(R.id.edEmail);
        edImage = findViewById(R.id.edImage);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            add();
        });
        btnLogIn.setOnClickListener(v -> {
            startActivity(new Intent(this, Login_Activity.class));
        });
    }

    private void add() {
        String name = edName.getText().toString();
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String phoneNum = edPhoneNum.getText().toString();
        String email = edEmail.getText().toString();
        String image = edImage.getText().toString();
        String sql = "INSERT INTO `account` (`id`, `permission`, `username`, `password`, `name`, `phoneNum`,`email`, `image`) VALUES (NULL, 'customer', '" +
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
        new ApiRunSql(sql, this).execute();
    }

    @Override
    public void start() {

    }

    @Override
    public void error() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
    }
}