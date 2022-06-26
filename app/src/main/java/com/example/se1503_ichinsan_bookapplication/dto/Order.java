package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private String id;
    private String userID;
    private String totalMoney;
    private String quantity;
    private OrderDetail orderDetai;

    public Order() {
    }

    public Order(String id, String userID, String totalMoney, String quantity) {
        this.id = id;
        this.userID = userID;
        this.totalMoney = totalMoney;
        this.quantity = quantity;
    }

    public Order(String id, String userID, String totalMoney, String quantity, OrderDetail orderDetai) {
        this.id = id;
        this.userID = userID;
        this.totalMoney = totalMoney;
        this.quantity = quantity;
        this.orderDetai = orderDetai;
    }

    public static List<Order> getOrderList(){
        List<Order> orderList = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.getOrderDetail(1);
        List<Book> bookList = orderDetail.getBookDetailList();
        int quantity = 0, totalMoney = 0;
        for (Book book : bookList) {
            quantity += book.getQuantity();
            totalMoney += book.getPrice();
        }
        Order order = new Order("1", "1", String.valueOf(totalMoney),
                String.valueOf(quantity), orderDetail);
        orderList.add(order);
        orderDetail = OrderDetail.getOrderDetail(1);
        bookList = orderDetail.getBookDetailList();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public OrderDetail getOrderDetai() {
        return orderDetai;
    }

    public void setOrderDetai(OrderDetail orderDetai) {
        this.orderDetai = orderDetai;
    }
}
