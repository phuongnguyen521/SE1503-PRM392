package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.CartAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartService;

public class CartRepository {
    public static CartService getCartService(){
        return CartAPIClient.CartClient().create(CartService.class);
    }
}
