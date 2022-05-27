package com.example.se1503_prm392.lab0301;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class SoccerActivity extends AppCompatActivity {

    //Khai bien
    private ListView lvSoccers;
    private CustomSoccersAdapter adapter;
    private ArrayList<Soccer> soccers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer);

        //Anh xa
        lvSoccers = (ListView) findViewById(R.id.lvSoccers);
        soccers = Soccer.getSoccers();
        adapter = new CustomSoccersAdapter(this,
                android.R.layout.simple_list_item_1,
                soccers);
        lvSoccers.setAdapter(adapter);
    }
}