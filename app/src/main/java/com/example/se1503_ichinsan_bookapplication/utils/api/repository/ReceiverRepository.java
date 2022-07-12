package com.example.se1503_ichinsan_bookapplication.utils.api.repository;

import com.example.se1503_ichinsan_bookapplication.utils.api.client.ReceiverAPIClient;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverService;

public class ReceiverRepository {
    public static ReceiverService getReceiverService(){
        return ReceiverAPIClient.getReceiverClient().create(ReceiverService.class);
    }
}
