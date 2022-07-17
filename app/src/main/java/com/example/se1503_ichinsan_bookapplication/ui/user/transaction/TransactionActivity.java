package com.example.se1503_ichinsan_bookapplication.ui.user.transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.TransactionAdapter;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.OrderRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.OrderService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {

    private ImageView ivTransactionBack;
    private RecyclerView rvOrderList;
    private List<Order> orderList;
    private FirebaseUser user;
    private User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        getSupportActionBar().hide();
        setData();

        ivTransactionBack.setOnClickListener(view -> {
            Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
            intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_user));
            startActivity(intent);
            finish();
        });
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, this.getApplicationContext());
        if (isSignedIn){
            getOrderByUserId(new CallBackListData () {
                @Override
                public void onGetMapData(Response<List<Order>> response) {
                    if (response != null && response.isSuccessful()){
                        orderList = response.body();
                        TransactionAdapter adapter = new TransactionAdapter(orderList, TransactionActivity.this);
                        rvOrderList.setAdapter(adapter);
                        rvOrderList.setLayoutManager(new LinearLayoutManager(TransactionActivity.this));
                    }
                }
                @Override
                public void onError(Response<List<Order>> response) {
                    Log.d("Transaction Failed", response.body().toString());
                    Toast.makeText(TransactionActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void setData(){
        ivTransactionBack = findViewById(R.id.ivTransactionBack);
        rvOrderList = findViewById(R.id.rvOrderList);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
    }

    private void getOrderByUserId(CallBackListData getDataCallBack){
        OrderService orderService = OrderRepository.getOrderService();
        try{
            Call<List<Order>> call = orderService.getOrderListByUserId(userProfile.getUserId());
            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        Log.d("Transaction Failed", response.errorBody().toString());
                        getDataCallBack.onError(response);
                    }
                }
                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(TransactionActivity.this, "Transaction Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Transaction Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Transaction Failed", e.getMessage());
        }
    }

    public interface CallBackListData {
        void onGetMapData(Response<List<Order>> response);
        void onError(Response<List<Order>> response);
    }
}