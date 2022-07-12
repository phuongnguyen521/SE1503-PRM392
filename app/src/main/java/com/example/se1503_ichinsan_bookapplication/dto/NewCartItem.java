package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;

public class NewCartItem implements Serializable {
    private long cartId;
    private String bookId;
    private String image;
    private String quantity;
    private String price;
    private String statusId;
    private Publisher publisher;

    public NewCartItem(long cartId, String bookId, String image, String quantity, String price, String statusId, Publisher publisher) {
        this.cartId = cartId;
        this.bookId = bookId;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.statusId = statusId;
        this.publisher = publisher;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
