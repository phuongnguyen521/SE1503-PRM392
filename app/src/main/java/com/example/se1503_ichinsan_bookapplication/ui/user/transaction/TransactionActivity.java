package com.example.se1503_ichinsan_bookapplication.ui.user.transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.TransactionAdapter;

import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    private ImageView ivTransactionBack;
    private RecyclerView rvOrderList;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        setData();

        ivTransactionBack.setOnClickListener(view -> {
            Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
            startActivity(intent);
            finish();
        });

        TransactionAdapter adapter = new TransactionAdapter(orderList, this);
        rvOrderList.setAdapter(adapter);
        rvOrderList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData(){
        ivTransactionBack = findViewById(R.id.ivTransactionBack);
        rvOrderList = findViewById(R.id.rvOrderList);
        //TO-DO: Get list orderList by API - Huy
        orderList = Order.getOrderList();
    }
}