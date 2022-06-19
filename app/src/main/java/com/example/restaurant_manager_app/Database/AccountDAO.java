package com.example.restaurant_manager_app.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant_manager_app.Model.Account;

public class AccountDAO {
    private final SQLiteDatabase db;

    public AccountDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insert(Account obj) {
        ContentValues values = new ContentValues();
        values.put("id", obj.getId());
        values.put("permission", obj.getPermission());
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("name", obj.getName());
        values.put("phoneNum", obj.getPhoneNum());
        values.put("email", obj.getEmail());
        values.put("image", obj.getImage());

        return db.insert("Account", null, values);
    }

    public void resetA() {
        db.delete("Account", "", null);
    }

    // get tat ca data
    public Account getAll() {
        String sql = "SELECT * FROM Account";
        return getData(sql);
    }

    Account obj;

    @SuppressLint("Range")
    private Account getData(String sql, String... selectionArgs) {
        @SuppressLint("Recycle") Cursor c = db.rawQuery(sql, selectionArgs);

        while (c.moveToNext()) {
            obj = new Account();
            obj.setId(c.getString(c.getColumnIndex("id")));
            obj.setPermission(c.getString(c.getColumnIndex("permission")));
            obj.setUsername(c.getString(c.getColumnIndex("username")));
            obj.setPassword(c.getString(c.getColumnIndex("password")));
            obj.setName(c.getString(c.getColumnIndex("name")));
            obj.setPhoneNum(c.getString(c.getColumnIndex("phoneNum")));
            obj.setEmail(c.getString(c.getColumnIndex("email")));
            obj.setImage(c.getString(c.getColumnIndex("image")));
        }
        return obj;
    }

    public int checkExist() {
        int check = 1; // khong
        String getLS = "SELECT * FROM Account";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(getLS, null);
        if (cursor.getCount() != 0) {
            check = -1;// co
        }
        return check;
    }
}

