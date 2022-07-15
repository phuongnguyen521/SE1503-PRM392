package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.UUID;

public class CartItem implements Serializable {
    private long cartId;
    private String bookId;
    private String bookName;
    private String image;
    private String quantity;
    private String price;
    private String statusId;
    private String bookTotalQuantity;
    private Publisher publisher;

    public CartItem(long cartId, String bookName, String image, String quantity, String price, String statusId, Publisher publisher) {
        this.cartId = cartId;
        this.bookName = bookName;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.statusId = statusId;
        this.publisher = publisher;
    }

    public CartItem(long cartId, String bookId, String bookName, String image, String quantity, String price, String statusId, Publisher publisher) {
        this.cartId = cartId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.statusId = statusId;
        this.publisher = publisher;
    }

    public long getCartId() { return cartId; }
    public void setCartId(long value) { this.cartId = value; }

    public String getBookId() { return bookId; }
    public void setBookId(String value) { this.bookId = value; }

    public String getBookName() { return bookName; }
    public void setBookName(String value) { this.bookName = value; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String value) { this.quantity = value; }

    public String getPrice() { return price; }
    public void setPrice(String value) { this.price = value; }

    public String getStatusId() { return statusId; }
    public void setStatusId(String value) { this.statusId = value; }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getBookTotalQuantity() {
        return bookTotalQuantity;
    }

    public void setBookTotalQuantity(String bookTotalQuantity) {
        this.bookTotalQuantity = bookTotalQuantity;
    }
}
