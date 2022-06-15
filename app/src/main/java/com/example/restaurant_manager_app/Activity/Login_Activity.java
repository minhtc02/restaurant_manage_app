package com.example.restaurant_manager_app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_manager_app.Api.ApiFindData;
import com.example.restaurant_manager_app.Database.AccountDAO;
import com.example.restaurant_manager_app.Interface.FindData;
import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login_Activity extends AppCompatActivity implements FindData {
    EditText ed_user, ed_pass;
    Button btn_login;
    ArrayList<Account> list;
    Account account;
    String username;
    AccountDAO dao;

    String tableName = "findDataAccount.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        mapping();
        ed_pass.setOnClickListener(v -> {
            username = "'" + ed_user.getText().toString() + "'";
            Toast.makeText(this, "" + username, Toast.LENGTH_SHORT).show();
            new ApiFindData(tableName, username, this).execute();

        });
        btn_login.setOnClickListener(v -> {
            if (account==null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Không tìm thấy tài khoản, vui lòng thử lại");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else {
                if (ed_user.getText().toString().trim() == "" || ed_pass.getText().toString().trim() == "") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Bạn phải điền đầy đủ tài khoản và mật khẩu !!!");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (ed_pass.getText().toString().trim().equals(account.getPassword().trim())) {
                    //tai khoan admin
                    //mat khau passwordAdmin
                    Log.d("TAG", "onCreate: " + account.getUsername() + account.getPassword() + account.getPermission());
                    dao.insert(account);
                    if (account.getPermission().equals("admin")) {
                        startActivity(new Intent(this, Main_Admin.class));
                    }else if (account.getPermission().equals("staff")) {
                        startActivity(new Intent(this, Main_Admin.class));
                    }
                    else {
                        startActivity(new Intent(this, MainActivity.class));
                    }

                } else {
                    Log.d("TAG", "onCreate: " + account.getUsername() + " " + account.getPassword());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Thông tin đăng nhập sai !!!");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }

        });
    }

    private void checkLogin() {

    }

    private void init() {
        list = new ArrayList<>();
        dao = new AccountDAO(this);

    }

    private void mapping() {
        ed_user = findViewById(R.id.ed_username_login);
        ed_pass = findViewById(R.id.ed_pass_login);
        btn_login = findViewById(R.id.button_login);
    }

    @Override
    public void start() {
        //Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void finish(String data) {
        try {
            list.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                account = new Account(o);
                list.add(account);
                Log.d("TAG", "finish: " + account.getUsername() + " - " + account.getPassword() + " - " + account.getPermission());
            }
        } catch (JSONException ignored) {
        }
    }

    @Override
    public void error() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

}