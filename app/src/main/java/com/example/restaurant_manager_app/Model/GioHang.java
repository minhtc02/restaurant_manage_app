package com.example.restaurant_manager_app.Model;

import java.io.Serializable;

public class GioHang implements Serializable {
    public int id_sp;
    public String name_sp;
    public long price_sp;
    public String img_sp;
    public int quali_sp;

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getName_sp() {
        return name_sp;
    }

    public void setName_sp(String name_sp) {
        this.name_sp = name_sp;
    }

    public long getPrice_sp() {
        return price_sp;
    }

    public void setPrice_sp(long price_sp) {
        this.price_sp = price_sp;
    }

    public String getImg_sp() {
        return img_sp;
    }

    public void setImg_sp(String img_sp) {
        this.img_sp = img_sp;
    }

    public int getQuali_sp() {
        return quali_sp;
    }

    public void setQuali_sp(int quali_sp) {
        this.quali_sp = quali_sp;
    }

    public GioHang() {
    }

    public GioHang(int id_sp, String name_sp, long price_sp, String img_sp, int quali_sp) {
        this.id_sp = id_sp;
        this.name_sp = name_sp;
        this.price_sp = price_sp;
        this.img_sp = img_sp;
        this.quali_sp = quali_sp;
    }
}
