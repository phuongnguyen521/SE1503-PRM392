package com.example.se1503_ichinsan_bookapplication.ui.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Cart;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.signup.SignUpActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.CartRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.UserRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartService;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.UserService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ImageView ivBackSignIn;
    private LinearLayout google_sign_in_button;
    private GoogleSignInClient client;
    private User userProfile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        google_sign_in_button = findViewById(R.id.google_sign_in_button);
        ivBackSignIn = findViewById(R.id.ivBackSignIn);

        ivBackSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
            startActivity(intent);
        });

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
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        checkExistUser(user, new CallBackData<User, Response<User>>() {
                            @Override
                            public void onGetMapData(Response<User> userResponse) {
                                Intent intent;
                                if (userResponse != null && userResponse.isSuccessful()){
                                    //Response<User> temp = userResponse;
                                    userProfile = userResponse.body();
                                    intent = new Intent(SignInActivity.this, MainActivity.class);
                                    intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
                                    UserPreferenceUtils.addToPreferences(userProfile, getString(R.string.PreferenceUserProfile), SignInActivity.this);
                                } else {
                                    intent = new Intent(SignInActivity.this, SignUpActivity.class);
                                }
                                startActivity(intent);
                            }

                            @Override
                            public void onError() {

                            }
                        });
                    } else {
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }
    private void checkExistUser(FirebaseUser user, final CallBackData getDataCallBack){
        UserService userService = UserRepository.getUserService();
        User loginUser = new User(user.getEmail(), user.getUid());
        Log.d("login user", loginUser.getEmail() + " - " + loginUser.getUid());
        try{
            Call<User> call = userService.SignIn(loginUser);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        getDataCallBack.onGetMapData(null);
                        userProfile = null;
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(SignInActivity.this, "Login Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Response Login Failed", t.getMessage());
                    userProfile = null;
                }
            });
        } catch (Exception e){
            Log.d("Sign In", e.getMessage());
        }
    }
}