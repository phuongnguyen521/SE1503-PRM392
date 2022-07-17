package com.example.se1503_ichinsan_bookapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.databinding.FragmentHomeBinding;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.BookAdapter;
import com.example.se1503_ichinsan_bookapplication.ui.cart.CartActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackListData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.BookRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.BookService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView ivLogo;
    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView rvHomeBookList;
    private List<Book> bookList;
    private ImageView ivHomeCart;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setData(root);
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setData(View root){
        ivLogo = root.findViewById(R.id.ivLogo);
        if (ivLogo != null){
            Glide.with(getContext())
                    .load(R.drawable.prm_logo)
                    .fitCenter()
                    .circleCrop()
                    .into(ivLogo);
        }
        ivHomeCart = root.findViewById(R.id.ivHomeCart);
        ivHomeCart.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CartActivity.class);
            intent.putExtra(getString(R.string.previousActivity), getString(R.string.title_home));
            startActivity(intent);
        });
        etSearch = root.findViewById(R.id.etSearch);
        btnSearch = root.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(getString(R.string.search_keyword), etSearch.getText().toString().trim().toLowerCase());
            startActivity(intent);
        });
        rvHomeBookList = root.findViewById(R.id.rvHomeBookList);
        getOrderByUserId(new CallBackListData<Book, List<Book>, Response<List<Book>>>() {
            @Override
            public void onGetMapData(Response<List<Book>> response) {
                if (response != null && response.isSuccessful()){
                    bookList = response.body();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    BookAdapter bookAdapter = new BookAdapter(bookList, getContext(), getString(R.string.title_home), null);
                    rvHomeBookList.setAdapter(bookAdapter);
                    rvHomeBookList.setLayoutManager(gridLayoutManager);
                }
            }

            @Override
            public void onError(Response response) {
                Log.d("Book List Failed", response.body().toString());
                Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getOrderByUserId(CallBackListData getDataCallBack){
        BookService orderService = BookRepository.getBookService();
        try{
            Call<List<Book>> call = orderService.getBookList("", "");
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
                    Toast.makeText(getContext(), "Book List Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Book List Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Book List Failed", e.getMessage());
        }
    }
}