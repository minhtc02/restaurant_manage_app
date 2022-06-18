package com.example.restaurant_manager_app.Api;

import android.os.AsyncTask;

import com.example.restaurant_manager_app.Interface.FindData;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiFindData extends AsyncTask<Void, Void, Void> {
    String data;
    String tableName;
    String name;
    FindData findData;
    OkHttpClient client = new OkHttpClient();

    public ApiFindData(String tableName, String name, FindData findData) {
        this.tableName = tableName;
        this.name = name;
        this.findData = findData;
        this.findData.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RequestBody body = new FormBody.Builder().add("name", name)
                .build();
        Request request = new Request.Builder()
                .url("https://mangareaderrecreate.000webhostapp.com/" + tableName)
                .post(body)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            data = response.body().string();
        } catch (IOException e) {
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null) {
            this.findData.error();
        } else {
            this.findData.finish(data);
        }
    }
}
