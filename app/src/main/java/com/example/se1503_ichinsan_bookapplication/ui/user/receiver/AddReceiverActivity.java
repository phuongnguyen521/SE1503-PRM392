package com.example.se1503_ichinsan_bookapplication.ui.user.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.signup.SignUpActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.ReceiverDetailRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.UserRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverDetailsService;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.UserService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReceiverActivity extends AppCompatActivity {

    private ImageView ivAddReceiverBack;
    private EditText etFullNameReceiverAdd;
    private EditText etAddressReceiverAdd;
    private EditText etPhoneReceiverAdd;
    private EditText etEmailReceiverAdd;
    private Button btnEditReceiverAdd;
    private User userProfile;
    private FirebaseUser user;
    private long receiverID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receiver);
        setData();
        boolean isEnabled = CommonUtils.isSignedInYet(user, userProfile, this);
        if (isEnabled){
            setEnabledData(isEnabled);
            btnEditReceiverAdd.setOnClickListener(view -> {
                if (!checkInfo()){
                    ReceiverDetail receiverDetail = new ReceiverDetail(
                            0,
                            receiverID,
                            etFullNameReceiverAdd.getText().toString(),
                            etPhoneReceiverAdd.getText().toString(),
                            etAddressReceiverAdd.getText().toString(),
                            etEmailReceiverAdd.getText().toString());
                    SignUpNewUser(receiverDetail, new CallBackData<ReceiverDetail, Response<ReceiverDetail>>() {
                        @Override
                        public void onGetMapData(Response<ReceiverDetail> receiverDetailResponse) {
                            if (!receiverDetailResponse.isSuccessful()){
                                Log.d("ReDetail Failed", "Cannot add receiver detail\n" + receiverDetailResponse.errorBody().toString());
                            }
                            Intent intent = new Intent(AddReceiverActivity.this, ReceiverActivity.class);
                            startActivity(intent);
                        }
                        @Override
                        public void onError() {

                        }
                    });
                } else {
                    Toast.makeText(this,"Please fill out fields" ,Toast.LENGTH_LONG).show();
                }
            });
        } else {
            setEnabledData(isEnabled);
        }
    }

    private void setData(){
        ivAddReceiverBack = findViewById(R.id.ivAddReceiverBack);
        ivAddReceiverBack.setOnClickListener(view -> {
            Intent intent = new Intent(AddReceiverActivity.this, ReceiverActivity.class);
            startActivity(intent);
        });
        etFullNameReceiverAdd = findViewById(R.id.etFullNameReceiverAdd);
        etAddressReceiverAdd = findViewById(R.id.etAddressReceiverAdd);
        etPhoneReceiverAdd = findViewById(R.id.etPhoneReceiverAdd);
        etEmailReceiverAdd = findViewById(R.id.etEmailReceiverAdd);
        btnEditReceiverAdd = findViewById(R.id.btnEditReceiverAdd);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
        Intent intent = getIntent();
        receiverID = intent.getLongExtra(getString(R.string.userReceiverId), 0);
    }

    private void setEnabledData(boolean isEnabled){
        etAddressReceiverAdd.setEnabled(isEnabled);
        etFullNameReceiverAdd.setEnabled(isEnabled);
        etPhoneReceiverAdd.setEnabled(isEnabled);
        etEmailReceiverAdd.setEnabled(isEnabled);
        btnEditReceiverAdd.setEnabled(isEnabled);
    }

    private boolean checkInfo(){
        return TextUtils.isEmpty(etAddressReceiverAdd.getText().toString())
                || TextUtils.isEmpty(etFullNameReceiverAdd.getText().toString())
                || TextUtils.isEmpty(etPhoneReceiverAdd.getText().toString())
                || TextUtils.isEmpty(etEmailReceiverAdd.getText().toString());
    }

    private void SignUpNewUser(ReceiverDetail receiverDetail, CallBackData getDataCallBack){
        ReceiverDetailsService receiverDetailsService = ReceiverDetailRepository.getReceiverDetailsService();
        try{
            Call<ReceiverDetail> call = receiverDetailsService.createRecevier(receiverDetail);
            call.enqueue(new Callback<ReceiverDetail>() {
                @Override
                public void onResponse(Call<ReceiverDetail> call, Response<ReceiverDetail> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        getDataCallBack.onGetMapData(null);
                        Log.d("ReDetail Res Failed", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<ReceiverDetail> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(AddReceiverActivity.this, "ReDetail Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Res ReDetail Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("ReDetail", e.getMessage());
        }
    }
}