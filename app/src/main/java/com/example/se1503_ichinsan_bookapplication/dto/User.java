package com.example.se1503_ichinsan_bookapplication.dto;
import android.content.Context;

import com.example.se1503_ichinsan_bookapplication.R;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String uid;
    private String phone;
    private String address;
    private Object createDate;
    private Object statusID;
    private String role;

    public User(String userId, String name, String email, String uid, String phone, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.address = address;
    }

    public User(String userId, String name, String email, String uid, String phone, String address, Object createDate, Object statusID, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.statusID = statusID;
        this.role = role;
    }

    public User(String name, String email, String uid, String phone, String address, String createDate, String statusID, String role) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.statusID = statusID;
        this.role = role;
    }

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public static User getUnknownUser(Context context){
        String defaultString = context.getString(R.string.unknown);
        return new User(null, defaultString, defaultString, defaultString, defaultString, defaultString);
    }

    public String getUserId() { return userId; }
    public void setUserId(String value) { this.userId = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getUid() { return uid; }
    public void setUid(String value) { this.uid = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public Object getCreateDate() { return createDate; }
    public void setCreateDate(Object value) { this.createDate = value; }

    public Object getStatusID() { return statusID; }
    public void setStatusID(Object value) { this.statusID = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }

    public User(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

}
