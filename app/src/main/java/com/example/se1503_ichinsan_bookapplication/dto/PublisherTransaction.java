package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.List;

public class PublisherTransaction implements Serializable {
    private Publisher publisher;
    private List<Book> bookList;

    public PublisherTransaction() {
    }

    public PublisherTransaction(Publisher publisher) {
        this.publisher = publisher;
    }

    public PublisherTransaction(Publisher publisher, List<Book> bookList) {
        this.publisher = publisher;
        this.bookList = bookList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
