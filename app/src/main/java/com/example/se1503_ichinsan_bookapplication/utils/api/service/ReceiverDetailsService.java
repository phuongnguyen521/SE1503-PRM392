package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReceiverDetailsService {
    //Get receiver detail by userId
    @GET(APIConstant.RECEIVER_DETAIL_API + "/{id}")
    Call<ReceiverDetail> getSpecificReceiverDetailByUserId(@Path("id") Object id);

    //Create a receiver
    @POST(APIConstant.RECEIVER_DETAIL_API)
    Call<ReceiverDetail> createRecevier(@Body ReceiverDetail receiverDetail);

    //Update a receiver by userId
    @PUT(APIConstant.RECEIVER_DETAIL_API + "/{id}")
    Call<ReceiverDetail> updateRecevier(@Path("id") Object id, @Body ReceiverDetail receiver);

    //Delete a receiver
    @DELETE(APIConstant.RECEIVER_DETAIL_API + "/{id}")
    Call<ReceiverDetail> deleteRecevier(@Path("id") Object id);
}
