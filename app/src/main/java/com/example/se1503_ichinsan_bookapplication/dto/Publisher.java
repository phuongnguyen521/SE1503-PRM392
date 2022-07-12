package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;

public class Publisher implements Serializable {
    private String name;
    private String phone;

    public Publisher() {
    }

    public Publisher(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public static Publisher getPublisher(){
        Publisher publisher = new Publisher("Tiki", "0102010212");
        return publisher;
    }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }
}

