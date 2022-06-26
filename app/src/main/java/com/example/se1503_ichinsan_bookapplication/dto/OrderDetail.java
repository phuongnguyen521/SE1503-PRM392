package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderDetail implements Serializable {
    private String id;
    private String orderDate;
    private Receiver receiverOrderDetail;
    private List<Book> bookDetailList;

    public OrderDetail() {
    }

    public OrderDetail(String id, String orderDate, Receiver receiverOrderDetail, List<Book> bookDetailList) {
        this.id = id;
        this.orderDate = orderDate;
        this.receiverOrderDetail = receiverOrderDetail;
        this.bookDetailList = bookDetailList;
    }

    public static OrderDetail getOrderDetail(int choice){
        String date = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = String.valueOf(LocalDate.now());
        }
        OrderDetail orderDetail = new OrderDetail("1", date, Receiver.getReceiver(), Book.getBookList(choice));
        return  orderDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Receiver getReceiverOrderDetail() {
        return receiverOrderDetail;
    }

    public void setReceiverOrderDetail(Receiver receiverOrderDetail) {
        this.receiverOrderDetail = receiverOrderDetail;
    }

    public List<Book> getBookDetailList() {
        return bookDetailList;
    }

    public void setBookDetailList(List<Book> bookDetailList) {
        this.bookDetailList = bookDetailList;
    }
}
