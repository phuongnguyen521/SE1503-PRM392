package com.example.se1503_prm392.lab0603;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.se1503_prm392.R;

public class ContextMenuActivityLab0603 extends AppCompatActivity {

    private Button btnContextMenu;
    private ConstraintLayout clScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu_lab0603);

        btnContextMenu = findViewById(R.id.btnContextMenu);
        clScreen = findViewById(R.id.clScreen);

        registerForContextMenu(btnContextMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);

//        menu.setHeaderTitle("Choose color");
//        menu.setHeaderIcon(R.drawable.ic_red_background);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.red:
                clScreen.setBackgroundColor(Color.RED);
            break;
            case R.id.yellow:
                clScreen.setBackgroundColor(Color.YELLOW);
            break;
            case R.id.Blue:
                clScreen.setBackgroundColor(Color.BLUE);
            break;
        }
        return super.onContextItemSelected(item);
    }
}