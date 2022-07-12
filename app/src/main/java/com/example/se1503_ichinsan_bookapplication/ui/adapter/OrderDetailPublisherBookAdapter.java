package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.BookDetail;
import com.example.se1503_ichinsan_bookapplication.ui.book.BookDetailActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;

import java.io.Serializable;
import java.util.List;

public class OrderDetailPublisherBookAdapter extends RecyclerView.Adapter<OrderDetailPublisherBookAdapter.ViewHolder>{
    private List<BookDetail> bookList;

    public OrderDetailPublisherBookAdapter(List<BookDetail> bookList) {
        this.bookList = bookList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetail_publisher_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookDetail book = bookList.get(position);

        CommonUtils.returnRectangleAvatar(holder.ivOrderDetailBookImage, holder.itemView.getContext(), book.getImage());
        holder.tvOrderDetailBookName.setText(book.getName());
        holder.tvOrderDetailBookQuantity.setText(String.valueOf(book.getQuantity()));
        String price = CommonUtils.GetCurrencyFormat(String.valueOf(book.getPrice()))
                + " " + holder.itemView.getContext().getString(R.string.currency_vnd);
        holder.tvOrderDetailBookPrice.setText(price);
        holder.orderDetailPublisherBookItemConstraint.setOnClickListener(view ->{
            Intent intent = new Intent(holder.itemView.getContext(), BookDetailActivity.class);
            intent.putExtra(holder.itemView.getContext().getString(R.string.getBookDetail), (Serializable) book);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout orderDetailPublisherBookItemConstraint;
        ImageView ivOrderDetailBookImage;
        TextView tvOrderDetailBookName;
        TextView tvOrderDetailBookQuantity;
        TextView tvOrderDetailBookPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderDetailPublisherBookItemConstraint = itemView.findViewById(R.id.orderDetailPublisherBookItemConstraint);
            ivOrderDetailBookImage = itemView.findViewById(R.id.ivOrderDetailBookImage);
            tvOrderDetailBookName = itemView.findViewById(R.id.tvOrderDetailBookName);
            tvOrderDetailBookQuantity = itemView.findViewById(R.id.tvOrderDetailBookQuantity);
            tvOrderDetailBookPrice = itemView.findViewById(R.id.tvOrderDetailBookPrice);

        }
    }
}
