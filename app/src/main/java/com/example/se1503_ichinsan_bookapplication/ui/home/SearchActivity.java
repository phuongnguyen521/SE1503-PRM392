package com.example.se1503_ichinsan_bookapplication.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.BookAdapter;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackListData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.BookRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.BookService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ImageView ivSearchBack;
    private EditText etSearchBar;
    private Button btnSearchBook;
    private RecyclerView rvSearchBookList;
    private List<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setData();
    }

    private void setData(){
        ivSearchBack = findViewById(R.id.ivSearchBack);
        ivSearchBack.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_home));
            startActivity(intent);
        });
        etSearchBar = findViewById(R.id.etSearchBar);
        btnSearchBook = findViewById(R.id.btnSearchBook);
        btnSearchBook.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
            intent.putExtra(getString(R.string.search_keyword), etSearchBar.getText().toString().trim().toLowerCase());
            startActivity(intent);
        });
        rvSearchBookList = findViewById(R.id.rvSearchBookList);
        Intent intent = getIntent();
        String searchKeywords = intent.getStringExtra(getString(R.string.search_keyword));
        if (!TextUtils.isEmpty(searchKeywords)){
            etSearchBar.setText(searchKeywords);
        }
        getBookList(searchKeywords, new CallBackListData<Book, List<Book>, Response<List<Book>>>() {
            @Override
            public void onGetMapData(Response<List<Book>> response) {
                if (response != null && response.isSuccessful()){
                    bookList = response.body();
                    BookAdapter bookAdapter = new BookAdapter(bookList, SearchActivity.this, getString(R.string.search_page), searchKeywords);
                    rvSearchBookList.setAdapter(bookAdapter);
                    rvSearchBookList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }

            @Override
            public void onError(Response<List<Book>> response) {

            }
        });
    }

    private void getBookList(String searchKeywords, CallBackListData<Book, List<Book>, Response<List<Book>>> getDataCallBack){
        BookService orderService = BookRepository.getBookService();
        try{
            Call<List<Book>> call = orderService.getBookList(searchKeywords, "");
            call.enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        Log.d("Transaction Failed", response.errorBody().toString());
                        getDataCallBack.onError(response);
                    }
                }
                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(getApplicationContext(), "Book List Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Book List Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Book List Failed", e.getMessage());
        }
    }
}