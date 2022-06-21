package com.example.se1503_ichinsan_bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class SignOutActivity extends AppCompatActivity {

    private Button btnSignOut;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_string))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, options);
        mAuth = FirebaseAuth.getInstance();

        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(view -> {
            mAuth.signOut();
            mGoogleSignInClient.signOut();
            Intent intent = new Intent(SignOutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}