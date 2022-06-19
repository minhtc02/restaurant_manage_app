package com.example.restaurant_manager_app.Api;

import android.os.AsyncTask;

import com.example.restaurant_manager_app.Interface.GetData;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiGetData extends AsyncTask<Void, Void, Void> {
    String data;
    String tableName;
    GetData getData;
    OkHttpClient client = new OkHttpClient();

    public ApiGetData(String tableName, GetData getData) {
        this.tableName = tableName;
        this.getData = getData;
        this.getData.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Request request = new Request.Builder()
                .url("https://mangareaderrecreate.000webhostapp.com/" + tableName)
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            data = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null) {
            this.getData.error();
        } else {
            this.getData.finish(data);
        }
    }
}

