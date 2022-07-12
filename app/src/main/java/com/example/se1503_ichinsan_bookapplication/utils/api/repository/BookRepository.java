package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.BookAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.BookService;

public class BookRepository {
    public static BookService getBookService(){
        return BookAPIClient.BookClient().create(BookService.class);
    }
}
