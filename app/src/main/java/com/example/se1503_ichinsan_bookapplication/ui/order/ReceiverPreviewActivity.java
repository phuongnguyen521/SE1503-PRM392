package com.example.se1503_ichinsan_bookapplication.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.PreviewOrderDto;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.ReceiverAdapter;
import com.example.se1503_ichinsan_bookapplication.ui.cart.CartActivity;
import com.example.se1503_ichinsan_bookapplication.ui.user.receiver.AddReceiverActivity;
import com.example.se1503_ichinsan_bookapplication.ui.user.receiver.ReceiverActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.ReceiverRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.ReceiverService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiverPreviewActivity extends AppCompatActivity {

    private ImageView ivReceiverPreviewBack;
    private RecyclerView rvReceiverPreviewList;
    private FirebaseUser user;
    private User userProfile;
    private PreviewOrderDto previewOrderDto;
    private Button btnReceiverNext;
    private Receiver receiver;
    private ReceiverAdapter receiverAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_preview);
        getSupportActionBar().hide();
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
                            Toast.makeText(ReceiverPreviewActivity.this,"There is no receiver in your address" ,Toast.LENGTH_LONG).show();
                            Log.d("ReceiverDetail", "There is no receiver in your address");
                        } else {
                            receiverAdapter = new ReceiverAdapter(
                                    receiverDetails,
                                    ReceiverPreviewActivity.this,
                                    true);
                            rvReceiverPreviewList.setAdapter(receiverAdapter);
                            rvReceiverPreviewList.setLayoutManager(new LinearLayoutManager(ReceiverPreviewActivity.this));
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

    private void setData(){
        Intent intent = getIntent();
        previewOrderDto = (PreviewOrderDto) intent.getSerializableExtra(getString(R.string.cartToOrder));
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
        ivReceiverPreviewBack = findViewById(R.id.ivReceiverPreviewBack);
        btnReceiverNext = findViewById(R.id.btnReceiverNext);
        btnReceiverNext.setOnClickListener(view -> {
            Intent intent1 = new Intent(ReceiverPreviewActivity.this, OrderDetailPreviewActivity.class);
            ReceiverDetail receiverDetail = receiverAdapter.getSelectedReceiver();
            if (receiverDetail != null){
                previewOrderDto.setReceiverDetail(receiverDetail);
                intent1.putExtra(getString(R.string.cartToOrder), (Serializable) previewOrderDto);
                startActivity(intent1);
            }
        });
        ivReceiverPreviewBack.setOnClickListener(view -> {
            Intent intent1 = new Intent(ReceiverPreviewActivity.this, CartActivity.class);
            intent1.putExtra(getString(R.string.getBookDetail), (Serializable) previewOrderDto.getBook());
            intent1.putExtra(getString(R.string.previousActivity), (Serializable)previewOrderDto.getPreviousActivity());
            startActivity(intent1);
            finish();
        });
        rvReceiverPreviewList = findViewById(R.id.rvReceiverPreviewList);
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
                    Toast.makeText(ReceiverPreviewActivity.this, "Get Recevier Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Response Rec Failed", t.getMessage());
                    receiver = null;
                }
            });
        } catch (Exception e){
            Log.d("Get Receiver", e.getMessage());
        }
    }
}