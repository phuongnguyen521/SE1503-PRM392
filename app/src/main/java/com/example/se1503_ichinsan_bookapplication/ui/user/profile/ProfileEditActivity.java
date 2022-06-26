package com.example.se1503_ichinsan_bookapplication.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.User;

public class ProfileEditActivity extends AppCompatActivity {

    private ImageView ivEditProfileBack;
    private Button btnSaveProfile;
    private EditText etNameProfileEdit;
    private EditText etPhoneEditProfile;
    private EditText etAddressEditProfile;

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

        Intent i = getIntent();
        User profile = (User) i.getSerializableExtra(getString(R.string.getUserProfile));
        if (profile == null){
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
            //etNameProfileEdit.setText(profile.getName());
            etNameProfileEdit.setEnabled(true);
            //etPhoneEditProfile.setText(profile.getPhone());
            etPhoneEditProfile.setEnabled(true);
            //etAddressEditProfile.setText(profile.getAddress());
            etAddressEditProfile.setEnabled(true);
            btnSaveProfile.setOnClickListener(view -> {
                Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
                startActivity(intent);
                //TO-DO: Add extra here by object
                finish();
            });
        }

    }

    private void setData(){
        ivEditProfileBack = findViewById(R.id.ivEditProfileBack);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        etNameProfileEdit = findViewById(R.id.etNameProfileEdit);
        etPhoneEditProfile = findViewById(R.id.etPhoneEditProfile);
        etAddressEditProfile = findViewById(R.id.etAddressEditProfile);
    }
}