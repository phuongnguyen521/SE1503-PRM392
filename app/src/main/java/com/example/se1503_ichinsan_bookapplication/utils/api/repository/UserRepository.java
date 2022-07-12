package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.UserAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.UserService;

public class UserRepository {
    public static UserService getUserService(){
        return UserAPIClient.getUserClient().create(UserService.class);
    }
}
