package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.ui.book.BookDetailActivity;
import com.example.se1503_ichinsan_bookapplication.ui.home.HomeFragment;
import com.example.se1503_ichinsan_bookapplication.ui.home.SearchActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.android.gms.common.internal.service.Common;

import java.io.Serializable;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<Book> bookList;
    private Context mcontext;
    private String previousPage;
    private String searchKeywords;

    public BookAdapter(List<Book> bookList, Context mcontext, String previousPage, String searchKeywords) {
        this.bookList = bookList;
        this.mcontext = mcontext;
        this.previousPage = previousPage;
        this.searchKeywords = searchKeywords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);

        CommonUtils.returnRectangleAvatar(holder.ivBookImage, mcontext, book.getImage());
        holder.tvBookName.setText(book.getName());
        holder.tvBookStatus.setText(book.getStatus());
        holder.tvBookStatus.setTextColor(book.getStatus().equals("In Stock") ? Color.GREEN : Color.RED);
        String price = CommonUtils.GetCurrencyFormat(String.valueOf(book.getPrice())) + " " + mcontext.getString(R.string.currency_vnd);
        holder.tvBookPrice.setText(price);

        holder.constraintBookItem.setOnClickListener(view -> {
            boolean isHome = previousPage.equals(mcontext.getString(R.string.title_home));
            Intent intent = new Intent(mcontext, BookDetailActivity.class);
            intent.putExtra(mcontext.getString(R.string.getBookDetail), (Serializable) book);
            intent.putExtra(mcontext.getString(R.string.previousActivity),
                    isHome? mcontext.getString(R.string.title_home) :
                            mcontext.getString(R.string.search_page));
            if (!isHome){
                intent.putExtra(mcontext.getString(R.string.search_keyword), searchKeywords);
            }
            UserPreferenceUtils.addToPreferences(book, mcontext.getString(R.string.preferenceBookDetail), mcontext);
            mcontext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivBookImage;
        TextView tvBookStatus;
        TextView tvBookName;
        TextView tvBookPrice;
        ConstraintLayout constraintBookItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBookImage = itemView.findViewById(R.id.ivBookImage);
            tvBookStatus = itemView.findViewById(R.id.tvBookStatus);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            constraintBookItem = itemView.findViewById(R.id.constraintBookItem);
        }
    }
}
