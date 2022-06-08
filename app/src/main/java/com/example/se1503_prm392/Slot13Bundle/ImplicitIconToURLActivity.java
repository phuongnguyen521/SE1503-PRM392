package com.example.se1503_prm392.Slot13Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.se1503_prm392.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImplicitIconToURLActivity extends AppCompatActivity {

    private ImageView ivVnExpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_icon_to_urlactivity);

        ivVnExpress = findViewById(R.id.ivVnExpress);

        ivVnExpress.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://vnexpress.net/"));
            startActivity(intent);
        });
    }
}