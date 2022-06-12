package com.example.restaurant_manager_app.Api;

import android.os.AsyncTask;

import com.example.restaurant_manager_app.Interface.RunSql;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.FormBody;
import okhttp3.Response;

public class ApiRunSql extends AsyncTask<Void, Void, Void> {
    String data;
    String sql;
    RunSql runSql;
    OkHttpClient client = new OkHttpClient();

    public ApiRunSql(String sql, RunSql runSql) {
        this.sql = sql;
        this.runSql = runSql;
        this.runSql.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        RequestBody body = new FormBody.Builder().add("sql",sql)
                .build();
        Request request = new Request.Builder()
                .url("https://mangareaderrecreate.000webhostapp.com/runsql.php")
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
            this.runSql.error();
        } else {
            if(data.equals("ok")){
                this.runSql.finish();
            }
            else {
                this.runSql.error();
            }
        }
    }
}
