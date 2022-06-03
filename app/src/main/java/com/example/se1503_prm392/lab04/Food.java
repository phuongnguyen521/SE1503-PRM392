package com.example.se1503_prm392.lab04;

public class Food {
    private int image;
    private String name;

    public Food(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
