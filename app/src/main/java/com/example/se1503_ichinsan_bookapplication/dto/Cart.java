package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable{
    private long id;
    private String userID;
    private List<CartItem> cartItems;

    public Cart(String userID) {
        this.userID = userID;
    }

    public Cart(long id, String userID, List<CartItem> cartItems) {
        this.id = id;
        this.userID = userID;
        this.cartItems = cartItems;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getUserID() { return userID; }
    public void setUserID(String value) { this.userID = value; }

    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> value) { this.cartItems = value; }
}
