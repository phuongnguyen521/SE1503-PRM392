package com.example.se1503_ichinsan_bookapplication.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.Cart;
import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.PreviewOrderDto;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.CartAdapter;
import com.example.se1503_ichinsan_bookapplication.ui.book.BookDetailActivity;
import com.example.se1503_ichinsan_bookapplication.ui.order.ReceiverPreviewActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.CartRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCartList;
    private Cart cart;
    private TextView tvCartTotal;
    private TextView tvCartTotalMoney;
    private ImageView ivCartClose;
    private Button btnPurchase;
    private FirebaseUser user;
    private User userProfile;
    private CartAdapter cartAdapter;
    private PreviewOrderDto previewOrderDto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setData();
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, this.getApplicationContext());
        btnPurchase.setEnabled(isSignedIn);
        if (isSignedIn){
            setCartByUserId();
        }
        btnPurchase.setOnClickListener(view -> {
            if (cartAdapter != null){
                List<CartItem> cartItemList = cartAdapter.getSelectedCartItemList();
                if (cartItemList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Pleaes choose at least one book!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(CartActivity.this, ReceiverPreviewActivity.class);
                    previewOrderDto.setCartItemList(cartItemList);
                    intent.putExtra(getString(R.string.cartToOrder), (Serializable) previewOrderDto);
                    startActivity(intent);
                }
            }
        });
    }

    private void setData(){
        ivCartClose = findViewById(R.id.ivCartClose);
        Intent intent = getIntent();
        String previousActivity = intent.getStringExtra(getString(R.string.previousActivity));
        Book book = (Book) intent.getSerializableExtra(getString(R.string.getBookDetail));
        previewOrderDto = new PreviewOrderDto(null, previousActivity, book, null);
        ivCartClose.setOnClickListener(view -> {
            Intent intent1;
            boolean isHome = previewOrderDto.getPreviousActivity().equals(getString(R.string.title_home));
            if (isHome){
                intent1 = new Intent(CartActivity.this,MainActivity.class);
                intent1.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_home));
            } else {
                intent1 = new Intent(CartActivity.this,BookDetailActivity.class);
                intent1.putExtra(getString(R.string.getBookDetail), (Serializable) book);
            }
            startActivity(intent1);
        });
        rvCartList = findViewById(R.id.rvCartList);
        tvCartTotal = findViewById(R.id.tvCartTotal);
        tvCartTotalMoney = findViewById(R.id.tvCartTotalMoney);
        btnPurchase = findViewById(R.id.btnPurchase);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), this.getApplicationContext(), User.class);
    }
    private void setCartByUserId(){
        getCartByUserId(new CallBackData<Cart, Response<Cart>>() {
            @Override
            public void onGetMapData(Response<Cart> response) {
                if (response.isSuccessful() && response != null){
                    Cart cart = response.body();
                    cartAdapter = new CartAdapter(cart.getCartItems(), CartActivity.this, tvCartTotalMoney, previewOrderDto.getPreviousActivity(), previewOrderDto.getBook());
                    rvCartList.setAdapter(cartAdapter);
                    rvCartList.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                } else {
                    Log.d("Cart Failed", response.body().toString());
                    Toast.makeText(CartActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getCartByUserId(CallBackData getCallBackData){
        CartService cartService = CartRepository.getCartService();
        try{
            Call<Cart> call = cartService.getCartListByUserId(userProfile.getUserId());
            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    if (response.isSuccessful()){
                        getCallBackData.onGetMapData(response);
                    } else {
                        Log.d("Cart Failed", response.errorBody().toString());
                        getCallBackData.onGetMapData(response);
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    getCallBackData.onGetMapData(null);
                    Toast.makeText(CartActivity.this, "Cart Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Cart Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Cart Failed", e.getMessage());
        }
    }
}