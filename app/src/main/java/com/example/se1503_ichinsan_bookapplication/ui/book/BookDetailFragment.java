package com.example.se1503_ichinsan_bookapplication.ui.book;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Book book;
    private TextView tvAuthorContent;
    private TextView tvBookDesContentDetail;
    private TextView tvSupplierBookContent;
    private TextView tvPublisherBookContent;
    private TextView tvReleaseYearBookContent;
    private TextView tvSizeBookContent;
    private TextView tvLanguageBookContent;
    private TextView tvPageBookContent;
    private Button btnBookDesViewMore;

    private String mParam1;
    private String mParam2;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookDetailFragment.
     */
    public static BookDetailFragment newInstance(String param1, String param2) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setData();
        setBookDetail();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setData(){
        tvAuthorContent = (TextView) getView().findViewById(R.id.tvAuthorContent);
        tvBookDesContentDetail = (TextView) getView().findViewById(R.id.tvBookDesContentDetail);
        btnBookDesViewMore = (Button) getView().findViewById(R.id.btnBookDesViewMore);
        tvSupplierBookContent = (TextView) getView().findViewById(R.id.tvSupplierBookContent);
        tvPublisherBookContent = (TextView) getView().findViewById(R.id.tvPublisherBookContent);
        tvReleaseYearBookContent =  (TextView) getView().findViewById(R.id.tvReleaseYearBookContent);
        tvSizeBookContent = (TextView) getView().findViewById(R.id.tvSizeBookContent);
        tvLanguageBookContent = (TextView) getView().findViewById(R.id.tvLanguageBookContent);
        tvPageBookContent = (TextView) getView().findViewById(R.id.tvPageBookContent);
        book = UserPreferenceUtils.getFromPreferences(getString(R.string.preferenceBookDetail), getContext(),Book.class);
    }

    private void setBookDetail(){
        if (book != null){
            btnBookDesViewMore.setEnabled(true);
            btnBookDesViewMore.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), BookDescriptionActivity.class);
                startActivity(intent);
            });
            int index = book.getDescription().indexOf(".");
            tvBookDesContentDetail.setText(book.getDescription().substring(0, index) + "...");
            tvAuthorContent.setText(book.getAuthorName());
            tvSupplierBookContent.setText(book.getSupplier());
            tvPublisherBookContent.setText(book.getPublisher().getName());
            tvReleaseYearBookContent.setText(String.valueOf(book.getReleaseYear()));
            tvSizeBookContent.setText(book.getSize());
            tvLanguageBookContent.setText(book.getLanguage());
            tvPageBookContent.setText(String.valueOf(book.getPage()));
        } else {
            btnBookDesViewMore.setEnabled(false);
        }
    }
}