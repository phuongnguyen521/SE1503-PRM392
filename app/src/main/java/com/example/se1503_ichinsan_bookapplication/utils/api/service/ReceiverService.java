package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReceiverService {
    //Get list by userId
    @GET(APIConstant.RECEIVER_API + "/{userId}")
    Call<Receiver> getAllReceiver(@Path("userId") Object uid);
}
