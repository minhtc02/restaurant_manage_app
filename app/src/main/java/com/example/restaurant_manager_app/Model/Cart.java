package com.example.restaurant_manager_app.Model;

import java.io.Serializable;

public class Cart implements Serializable {
    public String id;
    public String name;
    public String phoneNum;
    public String dishes;
    public String time;
    public String bill;

    public Cart() {
    }

    public Cart(String id, String name, String phoneNum, String dishes, String time, String bill) {
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
