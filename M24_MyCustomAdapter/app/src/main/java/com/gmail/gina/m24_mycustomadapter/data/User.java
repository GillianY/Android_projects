package com.gmail.gina.m24_mycustomadapter.data;

/**
 * Created by Administrator on 2016/11/1.
 */

public class User {
    private String name;
    private String address;
    private int picture;

    public User(String name, String address, int picture) {
        this.name = name;
        this.address = address;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }



}
