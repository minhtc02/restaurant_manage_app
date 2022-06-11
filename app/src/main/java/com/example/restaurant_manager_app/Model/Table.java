package com.example.restaurant_manager_app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Table implements Serializable {
    private String id;
    private String name;
    private String floor;
    private String status;

    public Table() {
    }

    public Table(String id, String name, String floor, String status) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.status = status;
    }
    public Table(JSONObject o) throws JSONException {
        id = o.getString("id");
        name = o.getString("name");
        floor = o.getString("floor");
        status = o.getString("status");

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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
