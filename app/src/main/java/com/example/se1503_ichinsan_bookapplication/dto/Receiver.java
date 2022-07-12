package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.List;

public class Receiver implements Serializable {

    private long id;
    private String userId;
    private List<ReceiverDetail> detail;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public String getUserId() { return userId; }
    public void setUserId(String value) { this.userId = value; }

    public List<ReceiverDetail> getDetail() { return detail; }
    public void setDetail(List<ReceiverDetail> value) { this.detail = value; }

    public Receiver() {
    }

    public Receiver(long id) {
        this.id = id;
    }

    public Receiver(long id, String userId, List<ReceiverDetail> detail) {
        this.id = id;
        this.userId = userId;
        this.detail = detail;
    }

    public Receiver(List<ReceiverDetail> detail) {
        this.detail = detail;
    }

    public Receiver(long id, List<ReceiverDetail> detail) {
        this.id = id;
        this.detail = detail;
    }
}
