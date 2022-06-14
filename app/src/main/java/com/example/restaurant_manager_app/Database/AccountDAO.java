package com.example.restaurant_manager_app.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant_manager_app.Model.Account;
import com.example.restaurant_manager_app.Model.Dish;

import java.util.ArrayList;
import java.util.List;

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

    //delete
    public void delete(String id) {
        db.delete("Account", "id=?", new String[]{id});
    }
    //delete
    public void resetA() {
        db.delete("Account", "", null);
    }

    // get tat ca data
    public ArrayList<Account> getAll() {
        String sql = "SELECT * FROM Account";
        return getData(sql);
    }

    @SuppressLint("Range")
    private ArrayList<Account> getData(String sql, String... selectionArgs) {
        ArrayList<Account> list = new ArrayList<>();
        @SuppressLint("Recycle") Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Account obj = new Account();
            obj.setId(c.getString(c.getColumnIndex("id")));
            obj.setPermission(c.getString(c.getColumnIndex("permission")));
            obj.setUsername(c.getString(c.getColumnIndex("username")));
            obj.setPassword(c.getString(c.getColumnIndex("password")));
            obj.setName(c.getString(c.getColumnIndex("name")));
            obj.setPhoneNum(c.getString(c.getColumnIndex("phoneNum")));
            obj.setEmail(c.getString(c.getColumnIndex("email")));
            obj.setImage(c.getString(c.getColumnIndex("image")));
            list.add(obj);
        }
        return list;
    }

    public int checkExist() {
        int check = 1; // khong
        String getLS = "SELECT * FROM Account";
        Cursor cursor = db.rawQuery(getLS, null);
        if (cursor.getCount() != 0) {
            check = -1;// co
        }
        return check;
    }
    public int checkExistsCart(String id) {
        int check = 1;
        String getMG = "SELECT * FROM Account WHERE name=" + "'"+id+"'" ;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(getMG, null);
        if (cursor.getCount() != 0) {
            check = -1;
        }
        return check;
    }
}

