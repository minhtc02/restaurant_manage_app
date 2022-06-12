package com.example.restaurant_manager_app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Order implements Serializable {
    private String id;
    private String name;
    private String phoneNum;
    private String dishes;
    private String time;
    private String bill;

    public Order(JSONObject o) throws JSONException {
        id = o.getString("id");
        name = o.getString("name");
        phoneNum = o.getString("phoneNum");
        dishes = o.getString("dishes");
        time = o.getString("time");
        bill = o.getString("bill");
    }

    public Order() {
    }

    public Order(String id, String name, String phoneNum, String dishes, String time, String bill) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.dishes = dishes;
        this.time = time;
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
