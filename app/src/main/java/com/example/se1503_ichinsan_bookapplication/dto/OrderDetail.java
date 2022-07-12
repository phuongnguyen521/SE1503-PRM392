package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderDetail implements Serializable{
    private long orderDetailId;
    private String orderId;
    private String orderDate;
    private ReceiverDetail receiverDetail;
    private List<BookDetail> bookDetails;

    public OrderDetail() {
    }

    public OrderDetail(long orderDetailId, String orderDate, ReceiverDetail receiverDetail, List<BookDetail> bookDetails) {
        this.orderDetailId = orderDetailId;
        this.orderDate = orderDate;
        this.receiverDetail = receiverDetail;
        this.bookDetails = bookDetails;
    }

    public OrderDetail(long orderDetailId, String orderId, String orderDate, ReceiverDetail receiverDetail, List<BookDetail> bookDetails) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.receiverDetail = receiverDetail;
        this.bookDetails = bookDetails;
    }

    public static OrderDetail getOrderDetail(int choice){
        String date = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = String.valueOf(LocalDate.now());
        }
        //OrderDetail orderDetail = new OrderDetail("1", date, Receiver.getReceiver(), Book.getBookList(choice));
        OrderDetail orderDetail = new OrderDetail();
        return  orderDetail;
    }

    public long getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(long value) { this.orderDetailId = value; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String value) { this.orderId = value; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String value) { this.orderDate = value; }

    public ReceiverDetail getReceiverDetail() { return receiverDetail; }
    public void setReceiverDetail(ReceiverDetail value) { this.receiverDetail = value; }

    public List<BookDetail> getBookDetails() { return bookDetails; }
    public void setBookDetails(List<BookDetail> value) { this.bookDetails = value; }
}
