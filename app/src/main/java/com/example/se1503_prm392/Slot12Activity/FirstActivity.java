package com.example.se1503_prm392.Slot12Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.se1503_prm392.R;

public class FirstActivity extends Activity {

    private Button btnSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.d("AAA","onCreateMain FirstMain");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA","onRestart FirstMain");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA","onResume FirstMain");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA","onPause FirstMain");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA","onPause FirstMain");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA","onPause FirstMain");
    }
}