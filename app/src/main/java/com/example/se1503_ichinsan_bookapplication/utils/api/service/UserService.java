package com.example.se1503_ichinsan_bookapplication.utils.api.service;

import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.utils.api.constant.APIConstant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST(APIConstant.USER_API + "/signin")
    Call<User> SignIn(@Body User user);

    //Sign Up
    @POST(APIConstant.USER_API + "/signup")
    Call<User> SignUp(@Body User userDetail);

    //Get Profile
    @GET(APIConstant.USER_API + "/{id}")
    Call<User> GetProfile (@Path("id") Object id);

    //Update Profile
    @PUT(APIConstant.USER_API + "/{id}")
    Call<User> updateUser(@Path("id") Object uid, @Body User user);
}
