package com.example.se1503_prm392.lab0301;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class Soccer {
    private int avatar;
    private String name;
    private String dob;
    private int age;
    private int countryImage;

    public Soccer (int avatar, String name, String dob, int age, int countryImage){
        this.avatar = avatar;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.countryImage = countryImage;
    }

    public int getAvatar() { return avatar;}
    public String getName() { return name; }
    public String getDob() {return dob;}
    public int getAge() {return age;}
    public int getCountryImage() {return countryImage; }

    public static ArrayList<Soccer> getSoccers() {
        ArrayList<Soccer> soccers = new ArrayList<>();
        soccers.add(new Soccer(R.drawable.pele, "Pele", "October 23, 1940", 82, R.drawable.brazil));
        soccers.add(new Soccer(R.drawable.diego_maradona, "Diego Maradona", "October 30, 1960", 62, R.drawable.argentina));
        soccers.add(new Soccer(R.drawable.johan_cruyff, "Johan Cruyff", "April 25, 1947", 75, R.drawable.nederland));
        soccers.add(new Soccer(R.drawable.franz_beckenbauer, "Franz Beckenbauer", "September 11, 1945", 77, R.drawable.germany));
        soccers.add(new Soccer(R.drawable.michel_platini, "Michel Platini", "June 21, 1955", 67, R.drawable.france));
        soccers.add(new Soccer(R.drawable.ronaldo_de_lima, "Ronaldo De Lima", "September 22, 1976", 46, R.drawable.brazil));
        return soccers;
    }
}
