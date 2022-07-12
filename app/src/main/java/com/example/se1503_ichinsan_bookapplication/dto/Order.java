package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private String orderId;
    private String userId;
    private String totalMoney;
    private String quantity;
    private OrderDetail orderDetail;

    public Order() {
    }

    public Order(String orderId, String userID, String totalMoney, String quantity) {
        this.orderId = orderId;
        this.userId = userID;
        this.totalMoney = totalMoney;
        this.quantity = quantity;
    }

    public Order(String orderId, String userID, String totalMoney, String quantity, OrderDetail orderDetail) {
        this.orderId = orderId;
        this.userId = userID;
        this.totalMoney = totalMoney;
        this.quantity = quantity;
        this.orderDetail = orderDetail;
    }

    public static List<Order> getOrderList(){
        List<Order> orderList = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.getOrderDetail(1);
        //List<Book> bookList = orderDetail.getBookDetailList();
        List<Book> bookList = new ArrayList<>();
        int quantity = 0, totalMoney = 0;
        for (Book book : bookList) {
            quantity += book.getQuantity();
            totalMoney += book.getPrice();
        }
        Order order = new Order("1", "1", String.valueOf(totalMoney),
                String.valueOf(quantity), orderDetail);
        orderList.add(order);
        orderDetail = OrderDetail.getOrderDetail(1);
        //bookList = orderDetail.getBookDetailList();
        bookList = new ArrayList<>();
        quantity = 0;
        totalMoney = 0;
        for (Book book : bookList) {
            quantity += book.getQuantity();
            totalMoney += book.getPrice();
        }
        order = new Order("2", "1", String.valueOf(totalMoney),
                String.valueOf(quantity), orderDetail);
        orderList.add(order);
        return orderList;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String value) { this.orderId = value; }

    public String getUserId() { return userId; }
    public void setUserId(String value) { this.userId = value; }

    public String getTotalMoney() { return totalMoney; }
    public void setTotalMoney(String value) { this.totalMoney = value; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String value) { this.quantity = value; }

    public OrderDetail getOrderDetail() { return orderDetail; }
    public void setOrderDetail(OrderDetail value) { this.orderDetail = value; }
}
