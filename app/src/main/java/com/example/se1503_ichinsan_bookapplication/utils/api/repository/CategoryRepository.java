package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.CategoryAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CategoryService;

public class CategoryRepository {
    public static CategoryService getCategoryService(){
        return CategoryAPIClient.CategoryClient().create(CategoryService.class);
    }
}
