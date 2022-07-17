package com.example.se1503_ichinsan_bookapplication.ui.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;

import java.io.Serializable;

public class BookDescriptionActivity extends AppCompatActivity {

    private ImageView ivBookDetailDescClose;
    private ImageView ivBookImageDescription;
    private TextView tvBookNameDescription;
    private TextView tvBookDesDescription;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);
        getSupportActionBar().hide();
        setData();
        setDataFromBookDetail();
    }

    private void setData(){
        ivBookDetailDescClose = findViewById(R.id.ivBookDetailDescClose);
        ivBookImageDescription = findViewById(R.id.ivBookImageDescription);
        tvBookNameDescription = findViewById(R.id.tvBookNameDescription);
        tvBookDesDescription = findViewById(R.id.tvBookDesDescription);
        book = UserPreferenceUtils.getFromPreferences(getString(R.string.preferenceBookDetail), this.getApplicationContext(),Book.class);
    }

    private void setDataFromBookDetail(){
        ivBookDetailDescClose.setOnClickListener(view -> {
            Intent intent = new Intent(BookDescriptionActivity.this, BookDetailActivity.class);
            intent.putExtra(getString(R.string.getBookDetail), (Serializable) book);
            startActivity(intent);
            finish();
        });
        if (book != null){
            CommonUtils.returnRectangleAvatar(ivBookImageDescription, this.getApplicationContext(), book.getImage());
            tvBookNameDescription.setText(book.getName());
            tvBookDesDescription.setText(book.getDescription());
        }
    }
}