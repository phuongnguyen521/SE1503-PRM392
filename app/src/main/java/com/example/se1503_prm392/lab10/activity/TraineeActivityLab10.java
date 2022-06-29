package com.example.se1503_prm392.lab10.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se1503_prm392.R;
import com.example.se1503_prm392.lab10.api.TraineeRepository;
import com.example.se1503_prm392.lab10.api.TraineeService;
import com.example.se1503_prm392.lab10.model.Trainee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeActivityLab10 extends AppCompatActivity implements View.OnClickListener{

    private TraineeService traineeService;
    private EditText etTraineeName, etTraineeEmail, etTraineePhone, etTraineeGender;
    private Button btnTraineeSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainee_lab10);
        etTraineeName = findViewById(R.id.etTraineeName);
        etTraineeEmail = findViewById(R.id.etTraineeEmail);
        etTraineePhone = findViewById(R.id.etTraineePhone);
        etTraineeGender = findViewById(R.id.etTraineeGender);
        btnTraineeSave = findViewById(R.id.btnTraineeSave);
        btnTraineeSave.setOnClickListener(this);
        traineeService = TraineeRepository.getTraineeService();
    }

    private void save(){
        String name = etTraineeName.getText().toString();
        String email = etTraineeEmail.getText().toString();
        String phone = etTraineePhone.getText().toString();
        String gender = etTraineeGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        try{
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null){
                        Toast.makeText(TraineeActivityLab10.this, "Save Successfully", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(TraineeActivityLab10.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        save();
    }
}