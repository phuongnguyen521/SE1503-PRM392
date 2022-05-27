package com.example.se1503_prm392.lab0302;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class FruitActivity extends AppCompatActivity {

    private ArrayList<Fruit> fruits;
    private ListView lvFruits;
    private CustomFruitsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        fruits = Fruit.getFruits();
        adapter = new CustomFruitsAdapter(this,
                android.R.layout.simple_list_item_1,
                fruits);
        lvFruits = (ListView) findViewById(R.id.lvFruits);
        lvFruits.setAdapter(adapter);
    }
}