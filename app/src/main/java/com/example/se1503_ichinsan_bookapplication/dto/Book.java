package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private CategoryDto categoryDto;
    private String id;
    private String name;
    private String supplier;
    private Publisher publisher;
    private long quantity;
    private long price;
    private String description;
    private String image;
    private String language;
    private String size;
    private long page;
    private long releaseYear;
    private String createDate;
    private String status;

    public Book() {
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String id, String name,
                CategoryDto categoryDto,
                Publisher publisher, int quantity,
                int price, String description,
                String image, String status) {
        this.id = id;
        this.name = name;
        this.categoryDto = categoryDto;
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

    public CategoryDto getCategoryDto() { return categoryDto; }
    public void setCategoryDto(CategoryDto value) { this.categoryDto = value; }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String value) { this.supplier = value; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher value) { this.publisher = value; }

    public long getQuantity() { return quantity; }
    public void setQuantity(long value) { this.quantity = value; }

    public long getPrice() { return price; }
    public void setPrice(long value) { this.price = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getLanguage() { return language; }
    public void setLanguage(String value) { this.language = value; }

    public String getSize() { return size; }
    public void setSize(String value) { this.size = value; }

    public long getPage() { return page; }
    public void setPage(long value) { this.page = value; }

    public long getReleaseYear() { return releaseYear; }
    public void setReleaseYear(long value) { this.releaseYear = value; }

    public String getCreateDate() { return createDate; }
    public void setCreateDate(String value) { this.createDate = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }
}
