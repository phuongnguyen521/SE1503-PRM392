package com.example.se1503_ichinsan_bookapplication.ui.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.ui.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity {

    private LinearLayout google_sign_in_button;
    private GoogleSignInClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        google_sign_in_button = findViewById(R.id.google_sign_in_button);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id_string))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
        google_sign_in_button.setOnClickListener(view -> {
            Intent intent = client.getSignInIntent();
            startActivityForResult(intent, 1234);
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task1 -> {
                    if (task.isSuccessful()){
                        Intent intent;
                        if (isExisted()){
                            intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
                        } else {
                            intent = new Intent(SignInActivity.this, SignUpActivity.class);
                        }
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

    //TO-DO: check existed user by API - Huy
    private boolean isExisted(){
        return true;
    }
}