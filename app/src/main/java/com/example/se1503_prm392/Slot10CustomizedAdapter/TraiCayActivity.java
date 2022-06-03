package com.example.se1503_prm392.Slot10CustomizedAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class TraiCayActivity extends AppCompatActivity {

    //Declare Variables
    private ListView lvTraiCay;
    ArrayList<TraiCay> traiCays;
    TraiCayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trai_cay);
        Anhxa();
        adapter = new TraiCayAdapter(TraiCayActivity.this, R.layout.list_traicay, traiCays);
        lvTraiCay.setAdapter(adapter);
    }

    private void Anhxa(){
        lvTraiCay = (ListView) findViewById(R.id.lvTraiCay);
        traiCays = new ArrayList<>();
        traiCays.add(new TraiCay("Grape", "Something about grape", R.drawable.grapes));
        traiCays.add(new TraiCay("Apple", "Something about apple", R.drawable.apple));
        traiCays.add(new TraiCay("Banana", "Something about banana", R.drawable.banana));

    }
}