package com.example.se1503_prm392.lab10.api;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
