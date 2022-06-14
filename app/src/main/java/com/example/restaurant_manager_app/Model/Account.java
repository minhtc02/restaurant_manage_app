package com.example.restaurant_manager_app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private String permission;
    private String username;
    private String password;
    private String name;
    private String phoneNum;
    private String email;
    private String image;

    public Account(JSONObject o) throws JSONException {
        id = o.getString("id");
        permission = o.getString("permission");
        username = o.getString("username");
        password = o.getString("password");
        name = o.getString("name");
        phoneNum = o.getString("phoneNum");
        email = o.getString("email");
        image = o.getString("image");
    }

    public Account() {
    }

    public Account(String id, String permission, String username, String password, String name, String phoneNum, String email, String image) {
        this.id = id;
        this.permission = permission;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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
