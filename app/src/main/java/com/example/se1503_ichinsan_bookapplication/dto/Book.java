package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String id;
    private String name;
    private List<Category> categoryList;
    private Publisher publisher;
    private int quantity;
    private int price;
    private String description;
    private String image;
    private String status;

    public Book() {
    }

    public Book(String id, String name,
                List<Category> categoryList,
                Publisher publisher, int quantity,
                int price, String description,
                String image, String status) {
        this.id = id;
        this.name = name;
        this.categoryList = categoryList;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public Book(String id, String name,
                Publisher publisher, int quantity,
                int price, String image) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public static List<Book> getBookList(int choice){
        List<Book> bookList = new ArrayList<>();
        if (choice == 0){
            bookList.add(new Book("1", "The Time Machine",
                    Publisher.getPublisher(),
                    3, 20000,
                    "https://salt.tikicdn.com/cache/w1200/ts/product/81/a5/b7/801792e7dfd941d1a334791f27fd35da.jpg"));
        } else {
            bookList.add(new Book("1", "The Time Machine",
                    new Publisher("Tiki", "0102010212"),
                    1, 20000,
                    "https://salt.tikicdn.com/cache/w1200/ts/product/81/a5/b7/801792e7dfd941d1a334791f27fd35da.jpg"));
            bookList.add(new Book("2", "The Invisible Man",
                    new Publisher("Tiki", "0102010212"),
                    3, 40000,
                    "https://salt.tikicdn.com/ts/product/b5/44/cd/c9ba2032040779d22c6cc71ab5322500.jpg"));
            bookList.add(new Book("3", "The Island of Doctor Moreau",
                    new Publisher("Fahasa", "0102010322"),
                    2, 60000,
                    "https://salt.tikicdn.com/cache/400x400/ts/product/ae/f9/7b/b9b5d4c125c0419b301198a2fee9a918.jpg"));
        }
        return bookList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
