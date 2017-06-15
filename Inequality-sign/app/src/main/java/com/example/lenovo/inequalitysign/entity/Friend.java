package com.example.lenovo.inequalitysign.entity;

/**
 * Created by 李泽 on 2017/6/14.
 */
public class Friend {
    private int img;
    private String name;
//    private String misoshu;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getMisoshu() {
//        return misoshu;
//    }
//
//    public void setMisoshu(String misoshu) {
//        this.misoshu = misoshu;
//    }

    public Friend(int img, String name) {

        this.img = img;
        this.name = name;
//        this.misoshu = misoshu;
    }


}
