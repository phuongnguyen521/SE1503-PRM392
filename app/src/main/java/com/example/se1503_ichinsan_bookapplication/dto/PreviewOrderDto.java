package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.List;

public class PreviewOrderDto implements Serializable {
    private List<CartItem> cartItemList;
    private String previousActivity;
    private Book book;
    private ReceiverDetail receiverDetail;

    public PreviewOrderDto(List<CartItem> cartItemList, String previousActivity, Book book, ReceiverDetail receiverDetail) {
        this.cartItemList = cartItemList;
        this.previousActivity = previousActivity;
        this.book = book;
        this.receiverDetail = receiverDetail;
    }

    public ReceiverDetail getReceiverDetail() {
        return receiverDetail;
    }

    public void setReceiverDetail(ReceiverDetail receiverDetail) {
        this.receiverDetail = receiverDetail;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getPreviousActivity() {
        return previousActivity;
    }

    public void setPreviousActivity(String previousActivity) {
        this.previousActivity = previousActivity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
