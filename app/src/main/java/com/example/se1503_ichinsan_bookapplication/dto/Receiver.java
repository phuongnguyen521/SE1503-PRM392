package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;

public class Receiver implements Serializable {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;

    public Receiver() {
    }

    public Receiver(String id) {
        this.id = id;
    }

    public Receiver(String name, String phoneNumber, String address, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public Receiver(String id, String name, String phoneNumber, String address, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public static Receiver getReceiver(){
        Receiver _receiver = new Receiver("Nguyen Van A", "0101010101", "District 9", "nguyenvana@gmail.com");
        return _receiver;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
