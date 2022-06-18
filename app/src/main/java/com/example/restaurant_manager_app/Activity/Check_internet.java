package com.example.restaurant_manager_app.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_manager_app.R;

public class Check_internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        if (CheckConnetion.haveNetworkConnection(getApplicationContext()))//neu co mang
        {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            CheckConnetion.ShowToastLong(getApplicationContext(), "Kiem tra lai mang");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Lỗi Mạng !!!")
                    .setMessage("Vui lòng kiểm tra lại kết nối mạng của bạn !!!")
                    .setPositiveButton("OK", (dialog, which) -> {
                        finish();
                        finishAffinity();
                        System.exit(0);
                    })
                    .setCancelable(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public static class CheckConnetion {
        public static boolean haveNetworkConnection(Context context) {
            boolean wifi = false, mobile = false;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
            for (NetworkInfo info : networkInfos) {
                if (info.getTypeName().equals("WIFI"))
                    if (info.isConnected())
                        wifi = true;
                if (info.getTypeName().equals("MOBILE"))
                    if (info.isConnected())
                        wifi = true;

            }
            return wifi || mobile;
        }

        public static void ShowToastLong(Context context, String tb) {
            Toast.makeText(context, tb, Toast.LENGTH_LONG).show();
        }
    }
}