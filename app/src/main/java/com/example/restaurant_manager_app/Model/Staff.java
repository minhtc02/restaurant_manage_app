package com.example.restaurant_manager_app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Staff implements Serializable {
    private String id;
    private String name;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private String image;

    public Staff() {
    }

    public Staff(String id, String name, String username, String password, String phoneNum, String email, String image) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.image = image;
    }
    public Staff(JSONObject o) throws JSONException {
        id = o.getString("id");
        name = o.getString("name");
        username = o.getString("username");
        password = o.getString("password");
        phoneNum = o.getString("phoneNum");
        email = o.getString("email");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
