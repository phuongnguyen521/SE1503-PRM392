package com.example.se1503_prm392.Slot13Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se1503_prm392.R;
import com.example.se1503_prm392.Slot9PassingData.Student;

public class MainActivitySlot13 extends AppCompatActivity {

    private Button btnClickMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slot13);

        btnClickMe = findViewById(R.id.btnClickMe);

        btnClickMe.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivitySlot13.this, SecondActivitySlot13.class);

            String[] arrName = {"Sai Gon", "Ha Noi", "Da Nang", "Can Tho"};
            Student hs = new Student("Tran Van Tam", "2000");

            Bundle bundle = new Bundle();
            bundle.putString("choi", "Truyen data voi Bundle");
            bundle.putInt("dangso", 255);
            bundle.putStringArray("Mangten", arrName);
            bundle.putSerializable("obj", hs);

            //send
            intent.putExtra("Dulieu", bundle);
            startActivity(intent);
        });
    }
}