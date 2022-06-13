package com.example.se1503_prm392.lab0502;

import com.example.se1503_prm392.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Module {
    private int imageModule;
    private String title;
    private String description;
    private String operationSystem;

    public Module(int imageModule, String title, String description, String operationSystem) {
        this.imageModule = imageModule;
        this.title = title;
        this.description = description;
        this.operationSystem = operationSystem;
    }

    public int getImageModule() {
        return imageModule;
    }

    public void setImageModule(int imageModule) {
        this.imageModule = imageModule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public static ArrayList<Module> getList(){
        ArrayList<Module> list = new ArrayList<>();
        list.add(new Module(R.drawable.android, "ListView trong Android", "Listview trong Android la mot thanh phan dung de nhom nhieu muc (item) v...", "Android"));
        list.add(new Module(R.drawable.ios, "Xu ly su kien trong iOS", "Xu ly su kien trong iOS. Sau khi cac ban da biet cach thiet ke giao dien cho cac ung...", "iOS"));
        return list;
    }
}
