package com.example.se1503_ichinsan_bookapplication.ui.user.receiver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class ReceiverDetailActivity extends AppCompatActivity {

    private ImageView ivReceiverDetailBack;
    private TextView tvReceiverFullName;
    private TextView tvReceiverAddress;
    private TextView tvReceiverPhone;
    private TextView tvReceiverEmail;
    private Button btnEditReceiverDetail;
    private Button btnDeleteReceiverDetail;
    private ReceiverDetail receiverDetail;
    private User userProfile;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_detail);
        getSupportActionBar().hide();
        setData();
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, this);
        isEnabledData(isSignedIn);
        if (isSignedIn && receiverDetail != null){
            setDataFromReceiverDetail();
            btnEditReceiverDetail.setOnClickListener(view -> {
                Intent intent = new Intent(ReceiverDetailActivity.this, EditReceiverActivity.class);
                intent.putExtra(getString(R.string.userReceiverDetail), (Serializable) receiverDetail);
                startActivity(intent);
            });
            btnDeleteReceiverDetail.setOnClickListener(view -> {
                DialogDeleteReceiverDetail(receiverDetail.getName());
            });
        }
    }

    private void setData(){
        ivReceiverDetailBack = findViewById(R.id.ivReceiverDetailBack);
        ivReceiverDetailBack.setOnClickListener(view -> {
            Intent intent = new Intent(ReceiverDetailActivity.this, ReceiverActivity.class);
            startActivity(intent);
        });
        Intent intent = getIntent();
        receiverDetail = (ReceiverDetail) intent.getSerializableExtra(getString(R.string.userReceiverDetail));
        tvReceiverFullName = findViewById(R.id.tvReceiverFullName);
        tvReceiverAddress = findViewById(R.id.tvReceiverAddress);
        tvReceiverPhone = findViewById(R.id.tvReceiverPhone);
        tvReceiverEmail = findViewById(R.id.tvReceiverEmail);
        btnEditReceiverDetail = findViewById(R.id.btnEditReceiverDetail);
        btnDeleteReceiverDetail = findViewById(R.id.btnDeleteReceiverDetail);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
    }
    private void isEnabledData (boolean isEnabled){
        btnDeleteReceiverDetail.setEnabled(isEnabled);
        btnEditReceiverDetail.setEnabled(isEnabled);
    }

    private void setDataFromReceiverDetail(){
        tvReceiverAddress.setText(receiverDetail.getAddress());
        tvReceiverFullName.setText(receiverDetail.getName());
        tvReceiverPhone.setText(receiverDetail.getPhone());
        tvReceiverEmail.setText(receiverDetail.getEmail());
    }

    private void DialogDeleteReceiverDetail(String name){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to delete this " + name + "?");
        dialog.setPositiveButton("Yes", ((dialogInterface, i) -> {
            DeleteReceiverDetail(new CallBackData<ReceiverDetail, Response<ReceiverDetail>>() {
                @Override
                public void onGetMapData(Response<ReceiverDetail> userResponse) {
                    if (userResponse != null && userResponse.isSuccessful()){
                        Toast.makeText(ReceiverDetailActivity.this, "Delete successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ReceiverDetailActivity.this, ReceiverActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ReceiverDetailActivity.this, "Delete unsuccessfully", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError() {}
            });
        }));
        dialog.setNegativeButton("No", ((dialogInterface, i) -> {}));
        dialog.show();
    }

    private void DeleteReceiverDetail(CallBackData getDataCallBack){
        ReceiverDetailsService receiverDetailsService = ReceiverDetailRepository.getReceiverDetailsService();
        try{
            Call<ReceiverDetail> call = receiverDetailsService.deleteRecevier(receiverDetail.getId());
            call.enqueue(new Callback<ReceiverDetail>() {
                @Override
                public void onResponse(Call<ReceiverDetail> call, Response<ReceiverDetail> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        getDataCallBack.onGetMapData(null);
                    }
                }

                @Override
                public void onFailure(Call<ReceiverDetail> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(ReceiverDetailActivity.this, "Receive Detail Delete Failed\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("ReDetailDelete Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("ReDetailDelete", e.getMessage());
        }
    }
}