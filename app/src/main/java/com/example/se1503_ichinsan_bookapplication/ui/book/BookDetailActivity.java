package com.example.se1503_ichinsan_bookapplication.ui.book;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.Cart;
import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.NewCartItem;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.cart.CartActivity;
import com.example.se1503_ichinsan_bookapplication.ui.home.SearchActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.CartItemRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.CartRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartItemsService;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView ivBookDetailBack;
    private ImageView ivBookDetailImage;
    private ImageView ivBookDetailCart;
    private TextView tvBookDetailBookName;
    private TextView tvBookDetailBookPrice;
    private Button btnAddToCart;
    private Book book;
    private Cart cart;
    private String previousPage;
    private String searchKeywords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        setData();
        setDataFromBookDetail();
    }

    private void setData(){
        ivBookDetailBack = findViewById(R.id.ivBookDetailBack);
        ivBookDetailImage = findViewById(R.id.ivBookDetailImage);
        ivBookDetailCart = findViewById(R.id.ivBookDetailCart);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        tvBookDetailBookName = findViewById(R.id.tvBookDetailBookName);
        tvBookDetailBookPrice = findViewById(R.id.tvBookDetailBookPrice);
        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra(getString(R.string.getBookDetail));
        previousPage = intent.getStringExtra(getString(R.string.previousActivity));
        if (TextUtils.isEmpty(previousPage)){
            previousPage = getString(R.string.title_home);
        }
        searchKeywords = intent.getStringExtra(getString(R.string.search_keyword));
    }

    private void setDataFromBookDetail(){
        ivBookDetailBack.setOnClickListener(view -> {
            boolean isHome = previousPage.equals(getString(R.string.title_home));
            Intent intent = new Intent(BookDetailActivity.this, isHome? MainActivity.class : SearchActivity.class);
            if (isHome) {
                intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_home));
            } else {
                intent.putExtra(getString(R.string.search_keyword), searchKeywords);
            }
            startActivity(intent);
            finish();
        });
        ivBookDetailCart.setOnClickListener(view -> {
            Intent intent = new Intent(BookDetailActivity.this, CartActivity.class);
            intent.putExtra(getString(R.string.previousActivity), getString(R.string.bookDetailActivity));
            intent.putExtra(getString(R.string.getBookDetail), (Serializable) book);
            startActivity(intent);
            finish();
        });
        if (book != null){
            btnAddToCart.setEnabled(book.getQuantity() > 0);
            btnAddToCart.setOnClickListener(view -> {
                addBookToCart();
            });
            CommonUtils.returnRectangleAvatar(ivBookDetailImage, this.getApplicationContext(), book.getImage());
            tvBookDetailBookName.setText(book.getName());
            String price = CommonUtils.GetCurrencyFormat(String.valueOf(book.getPrice())) + " " + getString(R.string.currency_vnd);
            tvBookDetailBookPrice.setText(price);
        } else {
            btnAddToCart.setEnabled(false);
        }
    }

    private void addBookToCart(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        User userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), getApplicationContext(), User.class);
        boolean isSignedIn = CommonUtils.isSignedInYet(user, userProfile, BookDetailActivity.this);
        if (isSignedIn){
            getCartByUserId(userProfile.getUserId(), new CallBackData<Cart, Response<Cart>>() {
                @Override
                public void onGetMapData(Response<Cart> response) {
                    if (response.isSuccessful()&& response != null){
                        cart = response.body();
                        int index = isExistedBookInCart();
                        if (index > -1){
                            updateCartItem(cart.getCartItems().get(index));
                        } else {
                            NewCartItem cartItem = new NewCartItem(
                                    cart.getID(),
                                    book.getID(),
                                    book.getImage(),
                                    String.valueOf(book.getQuantity()),
                                    String.valueOf(book.getPrice()),
                                    book.getStatus(),
                                    book.getPublisher()
                            );
                            addCartItem(cartItem);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Cart does not exist", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    private void getCartByUserId(String userId, CallBackData<Cart, Response<Cart>> getDataCallBack){
        CartService cartService = CartRepository.getCartService();
        try{
            Call<Cart> call = cartService.getCartListByUserId(userId);
            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        Log.d("Book List Cart Failed", String.valueOf(response.body()));
                        getDataCallBack.onGetMapData(response);
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(getApplicationContext(), "Book List Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Book List Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Book Detail Cart", e.getMessage());
        }
    }

    private int isExistedBookInCart(){
        int result = -1;
        if (cart != null && !cart.getCartItems().isEmpty()){
            List<CartItem> cartItemList = cart.getCartItems();
            for(int number = 0; number < cartItemList.size(); number++){
                if (cartItemList.get(number).getBookId().equals(book.getID())){
                    result = number;
                    break;
                }
            }
        }
        return result;
    }

    private void updateCartItem(CartItem cartItem){
        int quantity = Integer.valueOf(cartItem.getQuantity()) + 1;
        cartItem.setQuantity(String.valueOf(quantity));
        updateCartByUserId(cartItem, new CallBackData<CartItem, Response<CartItem>>() {
            @Override
            public void onGetMapData(Response<CartItem> response) {
                if (response.isSuccessful() && response != null){
                    sendMessage("Add Successfully");
                    Log.d("Update Cart",String.valueOf(response.body()));
                } else {
                    sendMessage("Add Unsuccessfully");
                    Log.d("Update Cart Failed",String.valueOf(response.body()));
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void updateCartByUserId(CartItem cartItem, CallBackData getCallBackData){
        CartItemsService cartItemsService = CartItemRepository.getCartItemsService();
        try{
            Call<CartItem> call = cartItemsService.updateCart(cartItem.getCartId(), cartItem);
            call.enqueue(new Callback<CartItem>() {
                @Override
                public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                    if (response.isSuccessful() && response != null){
                        Log.d("Cart Item", String.valueOf(response.body()));
                        getCallBackData.onGetMapData(response);
                    } else {
                        Log.d("Cart Item Failed", String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<CartItem> call, Throwable t) {
                    getCallBackData.onGetMapData(null);
                    Toast.makeText(getApplicationContext(), "Cart Item Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Cart Item Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Cart Item Failed", e.getMessage());
        }
    }

    private void addCartItem(NewCartItem cartItem){
        cartItem.setQuantity("1");
        addNewCartItem(cartItem, new CallBackData<NewCartItem, Response<NewCartItem>>() {
            @Override
            public void onGetMapData(Response<NewCartItem> response) {
                if (response.isSuccessful() && response != null){
                    sendMessage("Add Successfully");
                    Log.d("Add Cart",String.valueOf(response.body()));
                } else {
                    sendMessage("Add Unsuccessfully");
                    Log.d("Add Cart Failed",String.valueOf(response.body()));
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void addNewCartItem(NewCartItem cartItem, CallBackData<NewCartItem, Response<NewCartItem>> getCallBackData){
        CartItemsService cartItemsService = CartItemRepository.getCartItemsService();
        try{
            Call<NewCartItem> call = cartItemsService.createCart(cartItem);
            call.enqueue(new Callback<NewCartItem>() {
                @Override
                public void onResponse(Call<NewCartItem> call, Response<NewCartItem> response) {
                    if (response.isSuccessful()){
                        Log.d("Add Cart Item", String.valueOf(response.body()));
                        getCallBackData.onGetMapData(response);
                    } else {
                        Log.d("Add Cart Failed", String.valueOf(response.body()));
                    }

                }

                @Override
                public void onFailure(Call<NewCartItem> call, Throwable t) {
                    getCallBackData.onGetMapData(null);
                    Toast.makeText(getApplicationContext(), "Cart Item Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Cart Item Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Add New Cart Failed", e.getMessage());
        }
    }

    private void sendMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}