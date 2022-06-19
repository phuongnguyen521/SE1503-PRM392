package com.example.se1503_prm392.Lab07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import com.example.se1503_prm392.R;

public class PermissionLab07 extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lab07);

        Button btnResquestPermission = findViewById(R.id.btn_request_permission);
        Button btnOpenSettingsPermission = findViewById(R.id.btn_open_setting_permission);

        btnResquestPermission.setOnClickListener(view -> {
            ClickRequestPermission();
        });

        btnOpenSettingsPermission.setOnClickListener(view -> {
            ClickToAppInfo();
        });
    }

    private void ClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            String permissions = (Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(new String[]{permissions}, REQUEST_PERMISSIONS_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ClickToAppInfo(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}