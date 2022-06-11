package com.example.restaurant_manager_app.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    final String createTable =
            "create table Cart (id TEXT  PRIMARY KEY , name TEXT , describe TEXT, vote TEXT, price INTEGER, image TEXT)";

    final String dropTable = "drop table if exists Cart";

    public DbHelper(Context context) {
        super(context, "res", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dropTable);
    }
}

