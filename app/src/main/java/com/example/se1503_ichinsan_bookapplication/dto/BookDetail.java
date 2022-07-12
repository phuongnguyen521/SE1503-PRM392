package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;


public class BookDetail implements Serializable {
    private String id;
    private String name;
    private String image;
    private long quantity;
    private long price;
    private Publisher publisher;

    public BookDetail(String id) {
        this.id = id;
    }

    public BookDetail(String id, String name, long quantity, long price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public BookDetail(String id, String name, String image, long quantity, long price, Publisher publisher) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.publisher = publisher;
    }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public long getQuantity() { return quantity; }
    public void setQuantity(long value) { this.quantity = value; }

    public long getPrice() { return price; }
    public void setPrice(long value) { this.price = value; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher value) { this.publisher = value; }
}
