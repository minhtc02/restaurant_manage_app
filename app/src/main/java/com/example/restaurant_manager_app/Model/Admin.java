package com.example.restaurant_manager_app.Model;

import java.io.Serializable;

public class Admin implements Serializable {
    private String username;
    private String pass;

    public Admin(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
