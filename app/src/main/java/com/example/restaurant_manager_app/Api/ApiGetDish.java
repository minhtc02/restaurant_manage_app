package com.example.restaurant_manager_app.Api;

import android.os.AsyncTask;

import com.example.restaurant_manager_app.Interface.GetDish;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiGetDish extends AsyncTask<Void, Void, Void> {
    String data;
    GetDish getDish;
    OkHttpClient client = new OkHttpClient();

    public ApiGetDish(GetDish getDish) {
        this.getDish = getDish;
        this.getDish.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Request request = new Request.Builder()
                .url("https://restaurantapp01.herokuapp.com/dataDish")
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
            this.getDish.error();
        } else {
            this.getDish.finish(data);
        }
    }
}

