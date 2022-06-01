package com.example.restaurant_manager_app.Api;

import android.os.AsyncTask;

import com.example.restaurant_manager_app.Interface.GetTable;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiGetTable extends AsyncTask<Void, Void, Void> {
    String data;
    GetTable getTable;
    OkHttpClient client = new OkHttpClient();

    public ApiGetTable(GetTable getTable) {
        this.getTable = getTable;
        this.getTable.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Request request = new Request.Builder()
                .url("https://restaurantapp01.herokuapp.com/dataTable")
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
            this.getTable.error();
        } else {
            this.getTable.finish(data);
        }
    }
}


