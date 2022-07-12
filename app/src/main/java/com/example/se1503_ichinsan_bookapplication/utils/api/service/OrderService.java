package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {
    //Get list
    @GET(APIConstant.ORDER_API)
    Call<List<Order>> getOrderListByUserId(@Query("userId") String userId);

    //Get detail
    @GET(APIConstant.ORDER_API + "/{id}")
    Call<Order> getSpecificOrderByOrderId (@Path("id") Object id);

    //Create an order
    @POST(APIConstant.ORDER_API)
    Call<Order> createOrder (@Body Order order);
}
