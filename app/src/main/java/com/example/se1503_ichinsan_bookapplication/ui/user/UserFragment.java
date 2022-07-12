package com.example.se1503_ichinsan_bookapplication.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Cart;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.signin.SignInActivity;
import com.example.se1503_ichinsan_bookapplication.databinding.FragmentUserBinding;
import com.example.se1503_ichinsan_bookapplication.ui.user.profile.ProfileActivity;
import com.example.se1503_ichinsan_bookapplication.ui.user.receiver.ReceiverActivity;
import com.example.se1503_ichinsan_bookapplication.ui.user.transaction.TransactionActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private LinearLayout google_sign_in_button;
    private GoogleSignInClient client;
    private Button btnProfile;
    private Button btnTransaction;
    private Button btnNotification  ;
    private Button btnAlternative;
    private Button btnAddress;
    private TextView tvAccountFullName;
    private ImageView ivAccountAvatar;
    private User userProfile;

    private boolean isUserNotNull() {
        return userProfile != null;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), getContext(), User.class);
        if (user != null){
            btnAlternative.setText(getString(R.string.sign_out));
            String urlImage = user.getPhotoUrl().toString().isEmpty() ? null : user.getPhotoUrl().toString();
            CommonUtils.returnCircleAvatar(ivAccountAvatar, getContext(), urlImage);
            if (isUserNotNull()){
                tvAccountFullName.setText(userProfile.getName());
            } else {
                Log.d("CGUP-UF","Cannot get user profile - User Fragment");
                tvAccountFullName.setText(getString(R.string.unknown));
            }
        } else {
            btnAlternative.setText(getString(R.string.sign_in));
            String urlImage = null;
            CommonUtils.returnCircleAvatar(ivAccountAvatar, getContext(), urlImage);
            tvAccountFullName.setText(getString(R.string.unknown));
        }
        btnProfile.setEnabled(isUserNotNull());
        btnAddress.setEnabled(isUserNotNull());
        btnNotification.setEnabled(isUserNotNull());
        btnTransaction.setEnabled(isUserNotNull());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        google_sign_in_button = root.findViewById(R.id.google_sign_in_button);
        btnProfile = root.findViewById(R.id.btnProfile);
        btnTransaction = root.findViewById(R.id.btnTransaction);
        btnNotification = root.findViewById(R.id.btnNotification);
        btnAlternative = root.findViewById(R.id.btnAlternative);
        btnAddress = root.findViewById(R.id.btnAddress);
        tvAccountFullName = root.findViewById(R.id.tvAccountFullName);
        ivAccountAvatar = root.findViewById(R.id.ivAccountAvatar);

        onStart();

        btnProfile.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        });
        btnTransaction.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TransactionActivity.class);
            startActivity(intent);
        });
        btnNotification.setOnClickListener(view ->{

        });
        btnAddress.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ReceiverActivity.class);
            startActivity(intent);
        });
        btnAlternative.setOnClickListener(view -> {
            if (btnAlternative.getText().equals(getString(R.string.sign_in))){
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            } else {
                GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id_string))
                        .requestEmail()
                        .build();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), options);
                mAuth.signOut();
                mGoogleSignInClient.signOut();
                boolean temp = UserPreferenceUtils.removeObjectFromPreference(getString(R.string.PreferenceUserProfile), getContext());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}