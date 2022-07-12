package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.OrderAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.OrderService;

public class OrderRepository {
    public static OrderService getOrderService(){
        return OrderAPIClient.getOrderClient().create(OrderService.class);
    }
}
