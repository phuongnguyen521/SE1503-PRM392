package com.example.se1503_ichinsan_bookapplication.ui.user.receiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.ReceiverAdapter;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.ReceiverRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiverActivity extends AppCompatActivity {

    private ImageView ivReceiverBack;
    private RecyclerView rvReceiverList;
    private FirebaseUser user;
    private User userProfile;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        setData();
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, this);
        if (isSignedIn) {
            getReceiverDetailList(userProfile, new CallBackData<Receiver, Response<Receiver>>() {
                @Override
                public void onGetMapData(Response<Receiver> receiverResponse) {
                    if (receiverResponse != null && receiverResponse.isSuccessful()){
                        receiver = receiverResponse.body();
                        List<ReceiverDetail> receiverDetails = receiver.getDetail();
                        if (receiverDetails.isEmpty() || receiverDetails == null){
                            Toast.makeText(ReceiverActivity.this,"There is no receiver in your address" ,Toast.LENGTH_LONG).show();
                            Log.d("ReceiverDetail", "There is no receiver in your address");
                        } else {
                            ReceiverAdapter receiverAdapter = new ReceiverAdapter(receiverDetails, ReceiverActivity.this, false);
                            rvReceiverList.setAdapter(receiverAdapter);
                            rvReceiverList.setLayoutManager(new LinearLayoutManager(ReceiverActivity.this));
                        }
                    } else {
                        Log.d("Receiver Failed", receiverResponse.errorBody() != null ? receiverResponse.errorBody().toString() : "Failed");
                    }
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_receiver_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuReceiverAdd && user != null && userProfile != null){
            Intent intent = new Intent(ReceiverActivity.this, AddReceiverActivity.class);
            intent.putExtra(getString(R.string.userReceiverId), receiver.getId());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
        ivReceiverBack = findViewById(R.id.ivReceiverPreviewBack);
        rvReceiverList = findViewById(R.id.rvReceiverPreviewList);
        ivReceiverBack.setOnClickListener(view -> {
            Intent intent = new Intent(ReceiverActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
            UserPreferenceUtils.addToPreferences(userProfile, getString(R.string.PreferenceUserProfile), ReceiverActivity.this);
            startActivity(intent);
            finish();
        });
    }

    private void getReceiverDetailList(User userProfile, final CallBackData getDataCallBack){
        ReceiverService receiverService = ReceiverRepository.getReceiverService();
        try{
            Call<Receiver> call = receiverService.getAllReceiver(userProfile.getUserId());
            call.enqueue(new Callback<Receiver>() {
                @Override
                public void onResponse(Call<Receiver> call, Response<Receiver> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        getDataCallBack.onGetMapData(null);
                        receiver = null;
                    }
                }
                @Override
                public void onFailure(Call<Receiver> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(ReceiverActivity.this, "Get Recevier Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Response Rec Failed", t.getMessage());
                    receiver = null;
                }
            });
        } catch (Exception e){
            Log.d("Get Receiver", e.getMessage());
        }
    }
}