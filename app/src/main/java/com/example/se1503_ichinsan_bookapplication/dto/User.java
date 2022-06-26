package com.example.se1503_ichinsan_bookapplication.dto;

import android.content.Context;

import com.example.se1503_ichinsan_bookapplication.R;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String uid;
    private String phone;
    private String address;

    public User() {
    }

    public User(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public User(String id, String name, String email, String uid, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.address = address;
    }

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public static User getUnknownUser(Context context){
        String defaultString = context.getString(R.string.unknown);
        return new User("", defaultString, defaultString, defaultString, defaultString, defaultString);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
