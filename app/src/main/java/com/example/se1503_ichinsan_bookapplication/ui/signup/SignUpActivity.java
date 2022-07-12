package com.example.se1503_ichinsan_bookapplication.ui.signup;

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
import com.example.se1503_ichinsan_bookapplication.ui.signin.SignInActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.UserRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.UserService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ImageView ivSignUpBack;
    private Button btnSaveSignUp;
    private EditText etNameSignUp;
    private EditText etPhoneSignUp;
    private EditText etAddressSignUp;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private User userProfile = null;

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
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            btnSaveSignUp.setOnClickListener(view -> {
                if (!checkInfo()){
                    User userDetail = new User(etNameSignUp.getText().toString(),
                            user.getEmail(),
                            user.getUid() ,
                            etPhoneSignUp.getText().toString(),
                            etAddressSignUp.getText().toString(),
                            formatter.format(date),
                            null,
                            "Customer");
                    SignUpNewUser(userDetail, new CallBackData<User, Response<User>>() {
                        @Override
                        public void onGetMapData(Response<User> userResponse) {
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            if (userResponse != null && userResponse.isSuccessful()){
                                userProfile = userResponse.body();
                                intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
                                UserPreferenceUtils.addToPreferences(userProfile, getString(R.string.PreferenceUserProfile), SignUpActivity.this);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.d("Sign Up Failed", userResponse.errorBody() != null ? userResponse.errorBody().toString() : "Failed");
                            }
//                            startActivity(intent);
//                            finish();
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
        ivSignUpBack = findViewById(R.id.ivSignUpBack);
        btnSaveSignUp = findViewById(R.id.btnSaveSignUp);
        etNameSignUp = findViewById(R.id.etNameSignUp);
        etNameSignUp.setText(user.getDisplayName());
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

    private boolean checkInfo(){
        return TextUtils.isEmpty(etAddressSignUp.getText().toString())
                || TextUtils.isEmpty(etNameSignUp.getText().toString())
                || TextUtils.isEmpty(etPhoneSignUp.getText().toString());
    }

    private void SignUpNewUser(User userDetail, CallBackData getDataCallBack){
        UserService userService = UserRepository.getUserService();
        try{
            Call<User> call = userService.SignUp(userDetail);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        userProfile = null;
                        getDataCallBack.onGetMapData(null);
                        Log.d("SignUp Response Failed", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(SignUpActivity.this, "Sign Up Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Response Sign Up Failed", t.getMessage());
                    userProfile = null;
                }
            });
        } catch (Exception e){
            Log.d("Sign Up", e.getMessage());
        }
    }
}