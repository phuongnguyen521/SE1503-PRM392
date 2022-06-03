package com.example.se1503_prm392.Slot9PassingData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se1503_prm392.R;

import java.io.Serializable;

public class IntentExplicitFirstActivity extends AppCompatActivity {

    private Button btnSendData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_explicit_first);

        btnSendData = (Button) findViewById(R.id.btnSendData);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntentExplicitFirstActivity.this, IntentExplicitSecondActivity.class);
                //intent.putExtra("Du lieu", "Hello world");
                //intent.putExtra("Du lieu", 123);
                //String [] Array = {"Android", "IOS","PHP", "Unity"};
                //intent.putExtra("Du lieu", Array);
                Student student = new Student("Nguyen Van A","1");
                intent.putExtra("Du lieu", (Serializable) student);
                startActivity(intent);
                finish();
            }
        });
    }
}