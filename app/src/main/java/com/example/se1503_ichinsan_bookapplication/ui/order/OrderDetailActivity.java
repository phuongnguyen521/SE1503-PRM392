package com.example.se1503_ichinsan_bookapplication.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.OrderDetailAdapter;
import com.example.se1503_ichinsan_bookapplication.ui.user.transaction.TransactionActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private Order order;
    private ImageView ivOrderDetailBack;
    private RecyclerView rvOrderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setData();

        ivOrderDetailBack.setOnClickListener(view -> {
            Intent intent = new Intent(OrderDetailActivity.this, TransactionActivity.class);
            startActivity(intent);
        });


        OrderDetailAdapter adapter = new OrderDetailAdapter(order);
        rvOrderDetail.setHasFixedSize(true);
        rvOrderDetail.setAdapter(adapter);
        rvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData(){
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(getString(R.string.getUserOrder));
        ivOrderDetailBack = findViewById(R.id.ivOrderDetailPreviewBack);
        rvOrderDetail = findViewById(R.id.rvOrderDetailPreview);
    }

    public OrderDetailActivity getActivity(){
        return OrderDetailActivity.this;
    }
}