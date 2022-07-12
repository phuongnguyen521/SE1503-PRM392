package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.NewCartItem;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartItemsService {
    //Add book
    @POST(APIConstant.CART_ITEM_API)
    Call<NewCartItem> createCart(@Body NewCartItem cart);

    //Edit book
    @PUT(APIConstant.CART_ITEM_API + "/{id}")
    Call<CartItem> updateCart(@Path("id") Object id, @Body CartItem cart);

    //Remove book
    @HTTP(method = "DELETE", path = APIConstant.CART_ITEM_API, hasBody = true)
    Call<CartItem> deleteCart(@Body CartItem cart);
}
