package com.example.se1503_prm392.lab0302;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class Fruit {
    private int avatar;
    private String name;
    private String description;

    public Fruit (int avatar, String name, String description){
        this.avatar = avatar;
        this.name = name;
        this.description = description;
    }

    public int getAvatar() { return avatar;}
    public String getName() { return name; }
    public String getDescription() {return description;}

    public static ArrayList<Fruit> getFruits() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(R.drawable.apple, "Apple", "apple...some description goes here..."));
        fruits.add(new Fruit(R.drawable.banana, "banana", "banana...some description goes here..."));
        fruits.add(new Fruit(R.drawable.blueberry, "blueberry", "blueberry...some description goes here..."));
        fruits.add(new Fruit(R.drawable.corn, "corn", "corn...some description goes here..."));
        fruits.add(new Fruit(R.drawable.grapes, "grapes", "grapes...some description goes here..."));
        return fruits;
    }
}
