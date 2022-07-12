package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookService {
    //Get list
    @GET(APIConstant.BOOK_API)
    Call<List<Book>> getBookList(@Query("name") String name, @Query("CategoryId") String CategoryId);

    //Get detail
    @GET(APIConstant.BOOK_API + "/{id}")
    Call<Book> getBookDetail(@Path("id") Object id);

    //Update book, omly quantity
    @PUT(APIConstant.BOOK_API + "/{id}")
    Call<Book> updateBookDetail (@Path("id") Object id);
}
