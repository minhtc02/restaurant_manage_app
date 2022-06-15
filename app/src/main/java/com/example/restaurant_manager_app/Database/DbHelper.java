package com.example.restaurant_manager_app.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    final String createTableCart =
            "create table Cart (id TEXT  PRIMARY KEY, name TEXT , describe TEXT, vote TEXT, price INTEGER, image TEXT)";
    final String createTableAccount =
            "create table Account (id TEXT  PRIMARY KEY, permission TEXT , username TEXT, password TEXT, name TEXT,phoneNum TEXT, email TEXT, image TEXT)";

    final String dropTableCart = "drop table if exists Cart";
    final String dropTableAccount = "drop table if exists Account";

    public DbHelper(Context context) {
        super(context, "res", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableAccount);
        db.execSQL(createTableCart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dropTableCart);
        db.execSQL(dropTableAccount);
    }
}

