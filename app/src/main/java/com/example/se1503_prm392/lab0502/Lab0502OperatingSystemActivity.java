package com.example.se1503_prm392.lab0502;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.se1503_prm392.R;

public class Lab0502OperatingSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab0502_operating_system);

        RecyclerView rvOS = findViewById(R.id.rvOS);

        Lab0502OSAdapter adapter = new Lab0502OSAdapter(Module.getList());

        rvOS.setAdapter(adapter);

        rvOS.setLayoutManager(new LinearLayoutManager(this));
    }
}