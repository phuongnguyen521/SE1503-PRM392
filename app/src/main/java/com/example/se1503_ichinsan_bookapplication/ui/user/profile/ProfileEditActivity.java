package com.example.se1503_ichinsan_bookapplication.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.signup.SignUpActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.UserRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.UserService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends AppCompatActivity {

    private ImageView ivEditProfileBack;
    private Button btnSaveProfile;
    private EditText etNameProfileEdit;
    private EditText etPhoneEditProfile;
    private EditText etAddressEditProfile;
    private User userProfile;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        setData();
        ivEditProfileBack.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
        if (userProfile == null || user == null){
            btnSaveProfile.setEnabled(false);
            String defaultString = getString(R.string.unknown);
            etNameProfileEdit.setText(defaultString);
            etNameProfileEdit.setEnabled(false);
            etPhoneEditProfile.setText(defaultString);
            etPhoneEditProfile.setEnabled(false);
            etAddressEditProfile.setText(defaultString);
            etAddressEditProfile.setEnabled(false);
        } else {
            btnSaveProfile.setEnabled(true);
            etNameProfileEdit.setText(userProfile.getName());
            etNameProfileEdit.setEnabled(true);
            etPhoneEditProfile.setText(userProfile.getPhone());
            etPhoneEditProfile.setEnabled(true);
            etAddressEditProfile.setText(userProfile.getAddress());
            etAddressEditProfile.setEnabled(true);
            btnSaveProfile.setOnClickListener(view -> {
                if (!checkInfo()){
                    User userDetail = new User(
                            userProfile.getUserId(),
                            etNameProfileEdit.getText().toString(),
                            userProfile.getEmail(),
                            userProfile.getUid() ,
                            etPhoneEditProfile.getText().toString(),
                            etAddressEditProfile.getText().toString(),
                            userProfile.getCreateDate() == null ? "" : userProfile.getCreateDate().toString(),
                            userProfile.getStatusID() == null ? "" : userProfile.getStatusID().toString(),
                            userProfile.getRole());
                    UpdateUser(userDetail, new CallBackData <User, Response<User>>() {
                        @Override
                        public void onGetMapData(Response<User> userResponse) {
                            Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
                            if (userResponse != null && userResponse.isSuccessful()){
                                userProfile = userDetail;
                                UserPreferenceUtils.editObjectOfPreferences(userProfile, getString(R.string.PreferenceUserProfile), ProfileEditActivity.this);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.d("ProE Failed", userResponse.errorBody() != null ? userResponse.errorBody().toString() : "Failed");
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

    }

    private void setData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
        ivEditProfileBack = findViewById(R.id.ivEditProfileBack);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        etNameProfileEdit = findViewById(R.id.etNameProfileEdit);
        etPhoneEditProfile = findViewById(R.id.etPhoneEditProfile);
        etAddressEditProfile = findViewById(R.id.etAddressEditProfile);
    }

    private boolean checkInfo(){
        return TextUtils.isEmpty(etAddressEditProfile.getText().toString())
                || TextUtils.isEmpty(etNameProfileEdit.getText().toString())
                || TextUtils.isEmpty(etPhoneEditProfile.getText().toString());
    }

    private void UpdateUser(User userDetail, CallBackData getDataCallBack){
        UserService userService = UserRepository.getUserService();
        try{
            Call<User> call = userService.updateUser(userDetail.getUserId(), userDetail);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        userProfile = null;
                        getDataCallBack.onGetMapData(null);
                        Log.d("ProE Response Failed", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(ProfileEditActivity.this, "Sign Up Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Response ProE Failed", t.getMessage());
                    userProfile = null;
                }
            });
        } catch (Exception e){
            Log.d("ProE", e.getMessage());
        }
    }
}