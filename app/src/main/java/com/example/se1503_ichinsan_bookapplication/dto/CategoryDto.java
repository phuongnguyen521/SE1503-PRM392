package com.example.se1503_ichinsan_bookapplication.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable{
    private String categoryId;
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String value) { this.categoryId = value; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String value) { this.categoryName = value; }
}
