package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;

public class ReceiverDetail implements Serializable {
    private long id;
    private long receiverId;
    private String name;
    private String phone;
    private String address;
    private String email;

    public ReceiverDetail() {
    }

    public ReceiverDetail(long id, long receiverId, String name, String phone, String address, String email) {
        this.id = id;
        this.receiverId = receiverId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public long getReceiverId() { return receiverId; }
    public void setReceiverId(long value) { this.receiverId = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
