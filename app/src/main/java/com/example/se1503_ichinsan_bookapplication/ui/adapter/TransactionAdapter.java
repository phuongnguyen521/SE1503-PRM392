package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.BookDetail;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.ui.order.OrderDetailActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    private List<Order> orderList;
    private Context mContext;

    public TransactionAdapter(List<Order> orderList, Context mContext) {
        this.orderList = orderList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //Inflate the custom layout
        View view = inflater.inflate(R.layout.transaction_order_item, parent, false);

        //Return a holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setOrder(holder, orderList.get(position));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivBookOrder;
        TextView tvOrderId;
        TextView tvOrderName;
        TextView tvOrderQuantity;
        TextView tvOrderTotalMoney;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivBookOrder = itemView.findViewById(R.id.ivBookOrder);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvOrderQuantity = itemView.findViewById(R.id.tvOrderQuantity);
            tvOrderTotalMoney = itemView.findViewById(R.id.tvOrderTotalMoney);
            constraintLayout = itemView.findViewById(R.id.transactionOrderItemconstraint);
        }
    }

    private void setOrder(ViewHolder holder, Order order){
        String orderId = order.getOrderId();
        if (orderId.length() > 1){
            orderId = order.getOrderId().substring(0, 10) + "...";
        }
        holder.tvOrderId.setText(holder.itemView.getContext().getString(R.string.order_id) + ": " + orderId);
        holder.tvOrderQuantity.setText(order.getQuantity());
        String totalMoney = CommonUtils.GetCurrencyFormat(order.getTotalMoney())
                + " " + holder.itemView.getContext().getString(R.string.currency_vnd);
        holder.tvOrderTotalMoney.setText(totalMoney);

        List<BookDetail> bookDetailList = order.getOrderDetail().getBookDetails();
        CommonUtils.returnRectangleAvatar(holder.ivBookOrder, mContext, bookDetailList.get(0).getImage());
        int size = bookDetailList.size();
        String title = bookDetailList.get(0).getName() + (size > 1 ? "\nand other books" : "");
        holder.tvOrderName.setText(title);

        holder.constraintLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, OrderDetailActivity.class);
            intent.putExtra(mContext.getString(R.string.getUserOrder), (Serializable) order);
            mContext.startActivity(intent);
        });
    }
}
