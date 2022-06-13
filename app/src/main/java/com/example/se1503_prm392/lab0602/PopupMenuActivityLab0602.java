package com.example.se1503_prm392.lab0602;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.se1503_prm392.R;

public class PopupMenuActivityLab0602 extends AppCompatActivity {

    private Button btnPopupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu_lab0602);

        btnPopupMenu = findViewById(R.id.btnPopupMenu);

        btnPopupMenu.setOnClickListener(view -> {
            ShowMenu();
        });
    }

    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuAdd:
                        btnPopupMenu.setText("Menu Add");
                        break;
                    case R.id.menuEdit:
                        btnPopupMenu.setText("Menu Edit");
                        break;
                    case R.id.menuDelete:
                        btnPopupMenu.setText("Menu Delete");
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}