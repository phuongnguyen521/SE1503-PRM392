package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.Cart;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {
    //Get list by userId
    @GET(APIConstant.CART_API + "/{userID}")
    Call<Cart> getCartListByUserId(@Path("userID") Object userId);
}
