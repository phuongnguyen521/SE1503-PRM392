package com.example.se1503_prm392.lab0501;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private RecyclerView rvUserList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rvUserList = findViewById(R.id.rvUserList);

        ArrayList<User> list = new ArrayList<>();
        list.add(new User("NguyenTT", "Tran Thanh Nguyen", "Nguyennt@fpt.edu.vn"));
        list.add(new User("Antv", "Tran Van An", "antv@fpt.edu.vn"));
        list.add(new User("BangTT", "Tran Thanh Bang", "bangtt@fpt.edu.vn"));
        list.add(new User("KhangTT", "Tran Thanh Khang", "khangtt@fpt.edu.vn"));
        list.add(new User("BaoNT", "Nguyen Thanh Bao", "baont@fpt.edu.vn"));
        list.add(new User("HungVH", "Vo Huy Hung", "hungvh@fpt.edu.vn"));

        UserAdapter adapter = new UserAdapter(list, this);
        rvUserList.setAdapter(adapter);

        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }
}