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
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.ReceiverDetailRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverDetailsService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReceiverActivity extends AppCompatActivity {

    private ImageView ivEditReceiverBack;
    private EditText etFullNameReceiverEdit;
    private EditText etAddressReceiverEdit;
    private EditText etPhoneReceiverEdit;
    private EditText etEmailReceiverEdit;
    private Button btnEditReceiverEdit;
    private User userProfile;
    private FirebaseUser user;
    private ReceiverDetail receiverDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_receiver);
        getSupportActionBar().hide();
        setData();
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, this);
        setEnabledData(isSignedIn);
        if (isSignedIn && receiverDetail != null){
            setDataFromReceiverDetail();
        }
    }

    private void setData(){
        ivEditReceiverBack = findViewById(R.id.ivEditReceiverBack);
        ivEditReceiverBack.setOnClickListener(view -> {
            Intent intent = new Intent(EditReceiverActivity.this, ReceiverDetailActivity.class);
            intent.putExtra(getString(R.string.userReceiverDetail), (Serializable) receiverDetail);
            startActivity(intent);
        });
        etEmailReceiverEdit = findViewById(R.id.etEmailReceiverEdit);
        etFullNameReceiverEdit = findViewById(R.id.etFullNameReceiverEdit);
        etAddressReceiverEdit = findViewById(R.id.etAddressReceiverEdit);
        etPhoneReceiverEdit = findViewById(R.id.etPhoneReceiverEdit);
        btnEditReceiverEdit = findViewById(R.id.btnEditReceiverEdit);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
        Intent intent = getIntent();
        receiverDetail = (ReceiverDetail)intent.getSerializableExtra(getString(R.string.userReceiverDetail));
    }

    private void setEnabledData(boolean isEnabled){
        etEmailReceiverEdit.setEnabled(isEnabled);
        etFullNameReceiverEdit.setEnabled(isEnabled);
        etAddressReceiverEdit.setEnabled(isEnabled);
        etPhoneReceiverEdit.setEnabled(isEnabled);
        btnEditReceiverEdit.setEnabled(isEnabled);
    }

    private void setDataFromReceiverDetail(){
        etAddressReceiverEdit.setText(receiverDetail.getAddress());
        etFullNameReceiverEdit.setText(receiverDetail.getName());
        etEmailReceiverEdit.setText(receiverDetail.getEmail());
        etPhoneReceiverEdit.setText(receiverDetail.getPhone());
        btnEditReceiverEdit.setOnClickListener(view -> {
            if (!checkInfo()){
                ReceiverDetail temp = new ReceiverDetail(
                        receiverDetail.getId(),
                        receiverDetail.getReceiverId(),
                        etFullNameReceiverEdit.getText().toString().trim(),
                        etPhoneReceiverEdit.getText().toString().trim(),
                        etAddressReceiverEdit.getText().toString().trim(),
                        etEmailReceiverEdit.getText().toString().trim()
                );
                editReceiverDetail(temp, new CallBackData<ReceiverDetail, Response<ReceiverDetail>>() {
                    @Override
                    public void onGetMapData(Response<ReceiverDetail> receiverDetailResponse) {
                        if (receiverDetailResponse != null && receiverDetailResponse.isSuccessful()){
                            receiverDetail = temp;
                            startReceiverActity();
                        } else {
                            Toast.makeText(EditReceiverActivity.this,"Cannot add receiver detail\n" + receiverDetailResponse.errorBody().toString() ,Toast.LENGTH_LONG).show();
                            Log.d("ReDetail Failed", "Cannot add receiver detail\n" + receiverDetailResponse.errorBody().toString());
                        }
                    }
                    @Override
                    public void onError() {

                    }
                });
            } else {
                Toast.makeText(this,"Please fill out fields" ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startReceiverActity(){
        Intent intent = new Intent(EditReceiverActivity.this, ReceiverDetailActivity.class);
        intent.putExtra(getString(R.string.userReceiverDetail), (Serializable) receiverDetail);
        startActivity(intent);
    }

    private void editReceiverDetail(ReceiverDetail temp, CallBackData getDataCallBack){
        ReceiverDetailsService receiverDetailsService = ReceiverDetailRepository.getReceiverDetailsService();
        try{
            Call<ReceiverDetail> call = receiverDetailsService.updateRecevier(receiverDetail.getId(),temp);
            call.enqueue(new Callback<ReceiverDetail>() {
                @Override
                public void onResponse(Call<ReceiverDetail> call, Response<ReceiverDetail> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        Log.d("ReDetail Edit Failed", response.errorBody().toString());
                        getDataCallBack.onGetMapData(null);
                    }
                }
                @Override
                public void onFailure(Call<ReceiverDetail> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(EditReceiverActivity.this, "ReDetail Edit Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Edit ReDetail Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("ReDetail Edit", e.getMessage());
        }
    }

    private boolean checkInfo(){
        return TextUtils.isEmpty(etEmailReceiverEdit.getText().toString())
                || TextUtils.isEmpty(etFullNameReceiverEdit.getText().toString())
                || TextUtils.isEmpty(etAddressReceiverEdit.getText().toString())
                || TextUtils.isEmpty(etPhoneReceiverEdit.getText().toString());
    }
}