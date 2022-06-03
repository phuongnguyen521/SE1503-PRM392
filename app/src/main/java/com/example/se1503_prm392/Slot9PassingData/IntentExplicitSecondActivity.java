package com.example.se1503_prm392.Slot9PassingData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.se1503_prm392.R;

public class IntentExplicitSecondActivity extends AppCompatActivity {

    private TextView tvShowData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_explicit_second);

        tvShowData = (TextView) findViewById(R.id.tvShowData);
        Intent intent = getIntent();
        //String noidung = intent.getStringExtra("Du lieu");
        //int noidung = intent.getIntExtra("Du lieu", 1234);
        //tvShowData.setText(noidung);
        //String[] Courses = intent.getStringArrayExtra("Du lieu");
        //tvShowData.setText(String.valueOf(Courses.length));
        Student student = (Student) intent.getSerializableExtra("Du lieu");
        tvShowData.setText(student.getId() + " " + student.getName());
    }
}