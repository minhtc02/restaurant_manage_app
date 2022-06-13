package com.example.restaurant_manager_app.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant_manager_app.Model.Dish;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private final SQLiteDatabase db;

    public CartDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public long insert(Dish obj) {
        ContentValues values = new ContentValues();
        values.put("id", obj.getId());
        values.put("name", obj.getName());
        values.put("describe", obj.getDescribe());
        values.put("vote", obj.getVote());
        values.put("price", obj.getPrice());
        values.put("image", obj.getImage());

        return db.insert("Cart", null, values);
    }

    //delete
    public void delete(String id) {
        db.delete("Cart", "id=?", new String[]{id});
    }
    //delete
    public void resetC() {
        db.delete("Cart", "", null);
    }

    // get tat ca data
    public ArrayList<Dish> getAll() {
        String sql = "SELECT * FROM Cart";
        return getData(sql);
    }

    public String getDishes() {

        String sql = "SELECT name FROM Cart";
        List list = new ArrayList();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            @SuppressLint("Range") String a = c.getString(c.getColumnIndex("name"));
            list.add(a);
        }
        String a = list.toString();
        a = a.replace("[","");
        a = a.replace("]","");
        return a;
    }

    int a;

    @SuppressLint("Range")
    public String getBill() {

        String sql = "SELECT SUM(price) AS \"total\"\n" +
                "FROM Cart";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            a = Integer.parseInt(c.getString(c.getColumnIndex("total")));

        }
        return String.valueOf(a);
    }

    public void resetCart() {
        String sql = "DELETE FROM Cart";
        Cursor c = db.rawQuery(sql, null);

    }


    @SuppressLint("Range")
    private ArrayList<Dish> getData(String sql, String... selectionArgs) {
        ArrayList<Dish> list = new ArrayList<>();
        @SuppressLint("Recycle") Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Dish obj = new Dish();
            obj.setId(c.getString(c.getColumnIndex("id")));
            obj.setName(c.getString(c.getColumnIndex("name")));
            obj.setDescribe(c.getString(c.getColumnIndex("describe")));
            obj.setVote(c.getString(c.getColumnIndex("vote")));
            obj.setPrice(c.getString(c.getColumnIndex("price")));
            obj.setImage(c.getString(c.getColumnIndex("image")));
            list.add(obj);
        }
        return list;
    }

    public int checkCart() {
        int check = 1;
        String getLS = "SELECT * FROM Cart";
        Cursor cursor = db.rawQuery(getLS, null);
        if (cursor.getCount() != 0) {
            check = -1;
        }
        return check;
    }
    public int checkExistsManga(String id) {
        int check = 1;
        String getMG = "SELECT * FROM Cart WHERE name=" + "'"+id+"'" ;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(getMG, null);
        if (cursor.getCount() != 0) {
            check = -1;
        }
        return check;
    }
}

