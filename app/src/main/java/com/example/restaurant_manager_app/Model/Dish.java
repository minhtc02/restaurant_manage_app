package com.example.restaurant_manager_app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Dish implements Serializable {
    private String id;
    private String name;
    private String describe;
    private String vote;
    private String price;
    private String image;

    public Dish() {
    }

    public Dish(String id, String name, String describe, String vote, String price, String image) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.vote = vote;
        this.price = price;
        this.image = image;
    }
    public Dish(JSONObject o) throws JSONException {
        id = o.getString("id");
        name = o.getString("name");
        describe = o.getString("describes");
        vote = o.getString("vote");
        price = o.getString("price");
        image = o.getString("image");
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
