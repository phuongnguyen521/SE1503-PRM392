package com.example.se1503_ichinsan_bookapplication.utils;

import retrofit2.Response;

public interface CallBackData <T,U extends Response<T>>{
    void onGetMapData(U userResponse);
    void onError();
}
