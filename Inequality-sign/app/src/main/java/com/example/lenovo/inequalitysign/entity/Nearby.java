package com.example.lenovo.inequalitysign.entity;

/**
 * Created by zhangzhixin on 2016/12/5.
 */
public class Nearby {
    private String name;
    private int img;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Nearby(String name, int img) {
        this.name = name;
        this.img = img;
    }
}
