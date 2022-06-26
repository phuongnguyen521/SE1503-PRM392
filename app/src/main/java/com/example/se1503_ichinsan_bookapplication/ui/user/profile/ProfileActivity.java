package com.example.se1503_ichinsan_bookapplication.ui.user.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivProfileBack;
    private Button btnEditProfile;
    private ImageView ivProfileAvatar;
    private TextView tvEmailContent;
    private TextView tvPhoneContent;
    private TextView tvAddressContent;
    private TextView tvFullnameProfile;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setData();

        if (user == null){
            btnEditProfile.setEnabled(false);
            User profile = User.getUnknownUser(ProfileActivity.this);
            CommonUtils.returnCircleAvatar(ivProfileAvatar, ProfileActivity.this, null);
            String defaultString = getString(R.string.unknown);
            tvFullnameProfile.setText(defaultString);
            tvEmailContent.setText(defaultString);
            tvPhoneContent.setText(defaultString);
            tvAddressContent.setText(defaultString);
        } else {
            btnEditProfile.setEnabled(true);
            CommonUtils.returnCircleAvatar(ivProfileAvatar, ProfileActivity.this, user.getPhotoUrl().toString());
            tvFullnameProfile.setText(user.getDisplayName());
            btnEditProfile.setOnClickListener(view -> {
                User user = User.getUnknownUser(ProfileActivity.this);
                Intent intent = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                intent.putExtra(getString(R.string.getUserProfile), (Serializable) user);
                startActivity(intent);
            });
        }

        ivProfileBack.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
            startActivity(intent);
            finish();
        });

    }

    private void setData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        ivProfileBack = findViewById(R.id.ivProfileBack);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        ivProfileAvatar = findViewById(R.id.ivProfileAvatar);
        tvEmailContent = findViewById(R.id.tvEmailContent);
        tvPhoneContent = findViewById(R.id.tvPhoneContent);
        tvAddressContent = findViewById(R.id.tvAddressContent);
        tvFullnameProfile = findViewById(R.id.tvFullnameProfile);
    }
}