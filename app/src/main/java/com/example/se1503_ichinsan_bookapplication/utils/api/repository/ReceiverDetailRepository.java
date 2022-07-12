package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.ReceiverDetailsAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverDetailsService;

public class ReceiverDetailRepository {
    public static ReceiverDetailsService getReceiverDetailsService(){
        return ReceiverDetailsAPIClient.ReceiverDetailsClient().create(ReceiverDetailsService.class);
    }
}
