package com.example.se1503_ichinsan_bookapplication.utils.api.client;

import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceiverDetailsAPIClient {
    private static Retrofit retrofit;

    public static Retrofit ReceiverDetailsClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }
}
