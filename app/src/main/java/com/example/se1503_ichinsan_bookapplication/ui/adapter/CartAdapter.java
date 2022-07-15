package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Book;
import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.PreviewOrderDto;
import com.example.se1503_ichinsan_bookapplication.ui.cart.CartActivity;
import com.example.se1503_ichinsan_bookapplication.ui.order.ReceiverPreviewActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.CommonUtils;
import com.example.se1503_ichinsan_bookapplication.utils.InputFilterMinMax;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.CartItemRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.CartItemsService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private List<CartItem> cartItemList;
    private List<CartItem> selectedCartItemList;
    private TextView tvCartTotalMoney;
    private Context context;
    private String previousActivity;
    private Book book;

    public CartAdapter(List<CartItem> cartItemList, Context context, TextView tvCartTotalMoney, String previousActivity, Book book) {
        this.selectedCartItemList = new ArrayList<>();
        this.cartItemList = cartItemList;
        this.tvCartTotalMoney = tvCartTotalMoney;
        this.context = context;
        this.previousActivity = previousActivity;
        this.book = book;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cart = cartItemList.get(position);
        CommonUtils.returnRectangleAvatar(holder.ivBookImageCart, context, cart.getImage());
        holder.tvBookNameCart.setText(cart.getBookName());
        String price = CommonUtils.GetCurrencyFormat(cart.getPrice()) + " " + context.getString(R.string.currency_vnd);
        holder.cbCartItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                holder.cbCartItem.setChecked(isChecked);
                if (isChecked){
                    selectedCartItemList.add(cart);
                    tvCartTotalMoney.setText(calculateTotalFromSelectedList());
                } else {
                    selectedCartItemList.remove(cart);
                    if (selectedCartItemList.isEmpty()){
                        tvCartTotalMoney.setText(context.getString(R.string.cart_info));
                    } else {
                        tvCartTotalMoney.setText(calculateTotalFromSelectedList());
                    }
                }
                CartActivity cartActivity = new CartActivity();
                notifyDataSetChanged();
            }
        });
        holder.tvBookPriceCart.setText(price);
        holder.etQuantityCart.setText(cart.getQuantity());
        holder.etQuantityCart.setFilters(new InputFilter[] {new InputFilterMinMax("1", cart.getBookTotalQuantity())});
        holder.etQuantityCart.setEnabled(false);
        holder.ivAddMoreBook.setOnClickListener(view -> {
            updateCartItem(cart, holder.etQuantityCart, Integer.valueOf(cart.getBookTotalQuantity()), true);
        });
        holder.ivCartRemove.setOnClickListener(view -> {
            updateCartItem(cart, holder.etQuantityCart, Integer.valueOf(cart.getBookTotalQuantity()), false);
        });
        holder.ivRemoveCartItem.setOnClickListener(view -> {
            deleteCartItem(cart);
        });
    }

    private void deleteCartItem(CartItem cartItem){
        deleteCartByUserId(cartItem, new CallBackData<CartItem, Response<CartItem>>() {
            @Override
            public void onGetMapData(Response<CartItem> response) {
                if (response.isSuccessful() && response != null){
                    Log.d("Delete Cart",String.valueOf(response.body()));
                    Intent intent = new Intent(context, CartActivity.class);
                    intent.putExtra(context.getString(R.string.previousActivity), previousActivity);
                    if (book != null){
                        intent.putExtra(context.getString(R.string.getBookDetail), (Serializable) book);
                    }
                    context.startActivity(intent);
                } else {
                    Log.d("Delete Cart Failed",String.valueOf(response.body()));
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void updateCartItem(CartItem cartItem, EditText etQuantityCart, int total, boolean isAdded){
        int quantity = isAdded ?
                Integer.valueOf(etQuantityCart.getText().toString()) + 1 :
                Integer.valueOf(etQuantityCart.getText().toString()) - 1;
        if (total >= quantity && quantity > 0){
            cartItem.setQuantity(String.valueOf(quantity));
            updateCartByUserId(cartItem, new CallBackData<CartItem, Response<CartItem>>() {
                @Override
                public void onGetMapData(Response<CartItem> response) {
                    if (response.isSuccessful() && response != null){
                        Log.d("Update Cart",String.valueOf(response.body()));
                        etQuantityCart.setText(String.valueOf(quantity));
                        if (!selectedCartItemList.isEmpty()){
                            int index = getPositionInSelectedList(cartItem.getBookId());
                            if (index > -1){
                                selectedCartItemList.set(index, cartItem);
                                tvCartTotalMoney.setText(calculateTotalFromSelectedList());
                            }
                        }
                    } else {
                        Log.d("Update Cart Failed",String.valueOf(response.body()));
                    }
                }

                @Override
                public void onError() {

                }
            });
        } else {
            Toast.makeText(context, "The minimum is 1 and the max of this book is " + total, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout constraintCartItem;
        CheckBox cbCartItem;
        ImageView ivBookImageCart;
        ImageView ivAddMoreBook;
        ImageView ivCartRemove;
        TextView tvBookNameCart;
        TextView tvBookPriceCart;
        ImageView ivRemoveCartItem;
        EditText etQuantityCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintCartItem = itemView.findViewById(R.id.constraintCartItem);
            cbCartItem = itemView.findViewById(R.id.cbCartItem);
            ivBookImageCart = itemView.findViewById(R.id.ivBookImageCart);
            ivAddMoreBook = itemView.findViewById(R.id.ivAddMoreBook);
            ivCartRemove = itemView.findViewById(R.id.ivCartRemove);
            tvBookNameCart = itemView.findViewById(R.id.tvBookNameCart);
            tvBookPriceCart = itemView.findViewById(R.id.tvBookPriceCart);
            ivRemoveCartItem = itemView.findViewById(R.id.ivRemoveCartItem);
            etQuantityCart = itemView.findViewById(R.id.etQuantityCart);
        }
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
                    Toast.makeText(context, "Cart Item Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Cart Item Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Cart Item Failed", e.getMessage());
        }
    }

    private void deleteCartByUserId(CartItem cartItem, CallBackData getCallBackData){
        CartItemsService cartItemsService = CartItemRepository.getCartItemsService();
        try{
            Call<CartItem> call = cartItemsService.deleteCart(cartItem);
            call.enqueue(new Callback<CartItem>() {
                @Override
                public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                    if (response.isSuccessful() && response != null){
                        Log.d("Cart Item", String.valueOf(response.body()));
                        getCallBackData.onGetMapData(response);
                    } else {
                        Log.d("Cart Item Failed", String.valueOf(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<CartItem> call, Throwable t) {
                    getCallBackData.onGetMapData(null);
                    Toast.makeText(context, "Cart Item Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Cart Item Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Cart Item Failed", e.getMessage());
        }
    }

    public List<CartItem> getSelectedCartItemList(){
        return selectedCartItemList;
    }

    private String calculateTotalFromSelectedList(){
        int total = 0;
        if (!selectedCartItemList.isEmpty()){
            for (CartItem cartItem: selectedCartItemList) {
                total += Integer.valueOf(cartItem.getPrice()) * Integer.valueOf(cartItem.getQuantity());
            }
        }
        return CommonUtils.GetCurrencyFormat(String.valueOf(total)) + " " + context.getString(R.string.currency_vnd);
    }

    private int getPositionInSelectedList(String bookId){
        int index = -1;
        for (int number = 0; number < selectedCartItemList.size(); number++){
            if (selectedCartItemList.get(number).getBookId().equals(bookId)){
                index = number;
            }
        }
        return index;
    }
}
