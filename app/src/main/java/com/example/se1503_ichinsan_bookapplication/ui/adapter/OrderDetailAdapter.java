package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.dto.OrderDetail;
import com.example.se1503_ichinsan_bookapplication.dto.Publisher;
import com.example.se1503_ichinsan_bookapplication.dto.PublisherTransaction;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{

    private Order order;
    private List<PublisherTransaction> publisherTransactions;

    public OrderDetailAdapter(Order order) {
        this.order = order;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_item, parent, false);
        publisherTransactions = getPublisherTransaction(order.getOrderDetai());
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail orderDetail = order.getOrderDetai();
        Receiver receiver = orderDetail.getReceiverOrderDetail();
        holder.tvOrderDetailID.setText(orderDetail.getId());
        holder.tvOrderDetailDate.setText(orderDetail.getOrderDate());
        holder.tvOrderDetailReceiverName.setText(receiver.getName());
        holder.tvOrderDetailReceiverEmail.setText(receiver.getEmail());
        holder.tvOrderDetailReceiverPhone.setText(receiver.getPhoneNumber());
        holder.tvOrderDetailReceiverAddress.setText(receiver.getAddress());
        holder.tvOrderDetailTotalContent.setText(order.getTotalMoney());


        OrderDetailPublisherAdapter adapter = new OrderDetailPublisherAdapter(publisherTransactions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        holder.rvOrderDetailPublisher.setAdapter(adapter);
        holder.rvOrderDetailPublisher.setLayoutManager(linearLayoutManager);
    }

    @Override
    public int getItemCount() {
        return order != null? 1 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrderDetailID;
        TextView tvOrderDetailDate;
        TextView tvOrderDetailReceiverName;
        TextView tvOrderDetailReceiverEmail;
        TextView tvOrderDetailReceiverPhone;
        TextView tvOrderDetailReceiverAddress;
        RecyclerView rvOrderDetailPublisher;
        TextView tvOrderDetailTotalContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderDetailID = itemView.findViewById(R.id.tvOrderDetailID);
            tvOrderDetailDate = itemView.findViewById(R.id.tvOrderDetailDate);
            tvOrderDetailReceiverName = itemView.findViewById(R.id.tvOrderDetailReceiverName);
            tvOrderDetailReceiverEmail = itemView.findViewById(R.id.tvOrderDetailReceiverEmail);
            tvOrderDetailReceiverPhone = itemView.findViewById(R.id.tvOrderDetailReceiverPhone);
            tvOrderDetailReceiverAddress = itemView.findViewById(R.id.tvOrderDetailReceiverAddress);
            rvOrderDetailPublisher = itemView.findViewById(R.id.rvOrderDetailPublisher);
            tvOrderDetailTotalContent = itemView.findViewById(R.id.tvOrderDetailTotalContent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<PublisherTransaction> getPublisherTransaction(OrderDetail orderDetail){
        List<PublisherTransaction> publisher = new ArrayList<>();
        List<Publisher> publisherTemp = new ArrayList<>();
        final AtomicBoolean[] check = {new AtomicBoolean(false)};

        orderDetail.getBookDetailList().forEach(book -> {
            if (publisherTemp.isEmpty()){
                publisherTemp.add(book.getPublisher());
            } else {
                Optional<Publisher> temp = publisherTemp.stream().findAny().filter(publisher1 -> publisher1.getName().equals(book.getPublisher().getName()));
                if (!temp.isPresent()){
                    publisherTemp.add(book.getPublisher());
                }
            }
        });

        if (publisherTemp.size() == 1){
            publisher.add(new PublisherTransaction(publisherTemp.get(0), orderDetail.getBookDetailList()));
        } else {
            publisherTemp.forEach(p -> {
                List<Book> bookList = orderDetail.getBookDetailList().stream().filter(book
                        -> book.getPublisher().getName().equals(p.getName())).collect(Collectors.toList());
                publisher.add(new PublisherTransaction(p, bookList));
            });
        }
        return publisher;
    }
}
