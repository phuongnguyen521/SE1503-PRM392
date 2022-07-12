package com.example.se1503_ichinsan_bookapplication.utils;

import java.util.List;

import retrofit2.Response;

public interface CallBackListData<T, E extends List<T>, U extends Response<E>> {
    void onGetMapData(U response);
    void onError(U response);
}