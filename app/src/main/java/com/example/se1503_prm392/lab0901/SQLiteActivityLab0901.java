package com.example.se1503_prm392.lab0901;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1503_prm392.R;

public class SQLiteActivityLab0901 extends AppCompatActivity {

    private Database database;
    private TextView tvLab0901;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_lab0901);

        //tvLab0901 = findViewById(R.id.tvLab0901);

        //Tao database Ghichu
        database = new Database(this, "GhiChu.sqlite", null, 1);

        //Tao table CongViec
        database.QueryData("Create table if not exists CongViec(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "TenCV nvarchar(200))");

        //Insert data
        //database.QueryData("Insert into CongViec values(null, 'Project Android')");
        //database.QueryData("Insert into CongViec values(null, 'Design app')");

        //Select data
        Cursor dataCongViec = database.GetData("Select id, TenCV from CongViec");
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            Toast.makeText(this,ten, Toast.LENGTH_LONG).show();
        }
    }
}