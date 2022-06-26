package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.Publisher;
import com.example.se1503_ichinsan_bookapplication.dto.PublisherTransaction;

import java.util.HashMap;
import java.util.List;

public class OrderDetailPublisherAdapter extends RecyclerView.Adapter<OrderDetailPublisherAdapter.ViewHolder>{
    private List<PublisherTransaction> publisher;

    public OrderDetailPublisherAdapter(List<PublisherTransaction> publisher) {
        this.publisher = publisher;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_publisher_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvOrderDetailPublisherName.setText(publisher.get(position).getPublisher().getName());
        holder.ivOrderDetailPublisherPhone.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + publisher.get(position).getPublisher().getPhone()));
            holder.itemView.getContext().startActivity(intent);
        });

        OrderDetailPublisherBookAdapter adapter = new OrderDetailPublisherBookAdapter(publisher.get(position).getBookList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        holder.rvOrderDetailPublisherDetail.setLayoutManager(linearLayoutManager);
        holder.rvOrderDetailPublisherDetail.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return publisher.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        //List of order detail - Publisher
        TextView tvOrderDetailPublisherName;
        ImageView ivOrderDetailPublisherPhone;
        RecyclerView rvOrderDetailPublisherDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Get the view of the elements of order detail - Publisher
            tvOrderDetailPublisherName = itemView.findViewById(R.id.tvOrderDetailPublisherName);
            ivOrderDetailPublisherPhone = itemView.findViewById(R.id.ivOrderDetailPublisherPhone);
            rvOrderDetailPublisherDetail = itemView.findViewById(R.id.rvOrderDetailPublisherDetail);
        }
    }
}
