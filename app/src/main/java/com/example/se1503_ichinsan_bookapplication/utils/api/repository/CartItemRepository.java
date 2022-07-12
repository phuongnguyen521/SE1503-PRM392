package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.CartAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartItemsService;

public class CartItemRepository {
    public static CartItemsService getCartItemsService(){
        return CartAPIClient.CartClient().create(CartItemsService.class);
    }
}
