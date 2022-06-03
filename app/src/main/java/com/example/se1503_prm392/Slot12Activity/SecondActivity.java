package com.example.se1503_prm392.Slot12Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.se1503_prm392.R;

public class SecondActivity extends Activity {

    private Button btnFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        Log.d("BBB","onCreate SecondActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA","onRestart SecondActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA","onResume SecondActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","onPause SecondActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA","onPause SecondActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","onPause SecondActivity");
    }
}