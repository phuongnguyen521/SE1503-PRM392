package com.example.se1503_prm392.Slot13Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.se1503_prm392.R;
import com.example.se1503_prm392.Slot9PassingData.Student;

public class SecondActivitySlot13 extends AppCompatActivity {

    private TextView tvDisplaySlot13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_slot13);

        tvDisplaySlot13 = findViewById(R.id.tvDisplaySlot13);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Dulieu");
        //check bundle co data khong
        if (bundle != null){
            String ten = bundle.getString("chuoi");
            int so = bundle.getInt("dangso", 123);
            String[] arr = bundle.getStringArray("MangTen");
            Student hs = (Student) bundle.getSerializable("obj");
            tvDisplaySlot13.setText(ten + "\n" + so + "\n" + arr[0] + "\n" + hs.getName());
        }
    }
}