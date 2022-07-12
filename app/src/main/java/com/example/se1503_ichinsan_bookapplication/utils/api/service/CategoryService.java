package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.CategoryDto;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    //Get list
    @GET(APIConstant.CATEGORY_API)
    Call<List<CategoryDto>> getCategoryList();

    //Get Category Detail
    @GET(APIConstant.CATEGORY_API + "/{id}")
    Call<CategoryDto> getCategoryDetail(@Path("id") Object id);
}
