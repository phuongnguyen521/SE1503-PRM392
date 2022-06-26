package com.example.se1503_ichinsan_bookapplication.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.signin.SignInActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private ImageView ivSignUpBack;
    private Button btnSaveSignUp;
    private EditText etNameSignUp;
    private EditText etPhoneSignUp;
    private EditText etAddressSignUp;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setData();

        ivSignUpBack.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            mAuth.signOut();
            mGoogleSignInClient.signOut();
            startActivity(intent);
            finish();
        });

        if (user == null){
            setElementEnabled(false);
        } else {
            setElementEnabled(true);
            btnSaveSignUp.setOnClickListener(view -> {
                if (!checkInfor()){
                    User profile = new User("", etNameSignUp.getText().toString(),
                            user.getEmail().toString(), "" ,
                            etPhoneSignUp.getText().toString(),
                            etAddressSignUp.getText().toString());
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this,"Please fill out fields" ,Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void setData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        ivSignUpBack = findViewById(R.id.ivSignUpBack);
        btnSaveSignUp = findViewById(R.id.btnSaveSignUp);
        etNameSignUp = findViewById(R.id.etNameSignUp);
        etPhoneSignUp = findViewById(R.id.etPhoneSignUp);
        etAddressSignUp = findViewById(R.id.etAddressSignUp);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_string))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(SignUpActivity.this, options);
    }

    private void setElementEnabled(boolean isEnabled){
        btnSaveSignUp.setEnabled(isEnabled);
        etNameSignUp.setEnabled(isEnabled);
        etAddressSignUp.setEnabled(isEnabled);
        etPhoneSignUp.setEnabled(isEnabled);
    }

    private boolean checkInfor(){
        return TextUtils.isEmpty(etAddressSignUp.getText().toString())
                || TextUtils.isEmpty(etNameSignUp.getText().toString())
                || TextUtils.isEmpty(etPhoneSignUp.getText().toString());
    }
}