package com.example.se1503_ichinsan_bookapplication.ui.order;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.se1503_ichinsan_bookapplication.MainActivity;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.BookDetail;
import com.example.se1503_ichinsan_bookapplication.dto.CartItem;
import com.example.se1503_ichinsan_bookapplication.dto.Order;
import com.example.se1503_ichinsan_bookapplication.dto.OrderDetail;
import com.example.se1503_ichinsan_bookapplication.dto.PreviewOrderDto;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.example.se1503_ichinsan_bookapplication.ui.adapter.OrderDetailAdapter;
import com.example.se1503_ichinsan_bookapplication.ui.user.transaction.TransactionActivity;
import com.example.se1503_ichinsan_bookapplication.utils.CallBackData;
import com.example.se1503_ichinsan_bookapplication.utils.api.repository.OrderRepository;
import com.example.se1503_ichinsan_bookapplication.utils.api.service.OrderService;
import com.example.se1503_ichinsan_bookapplication.utils.dto.UserPreferenceUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPreviewActivity extends AppCompatActivity {

    private FirebaseUser user;
    private User userProfile;
    private PreviewOrderDto previewOrderDto;
    private ImageView ivOrderDetailPreviewBack;
    private RecyclerView rvOrderDetailPreview;
    private Button btnCheckOut;
    private Order order;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_preview);
        setData();
        setAdapter();
        btnCheckOut.setOnClickListener(view -> {
            createOrder(order, new CallBackData<Order, Response<Order>>() {
                @Override
                public void onGetMapData(Response<Order> response) {
                    if (response.isSuccessful() && response != null){
                        Toast.makeText(getApplicationContext(), "Create Order Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(OrderDetailPreviewActivity.this, MainActivity.class);
                        intent.putExtra(getString(R.string.getSpecificFragment), getString(R.string.title_home));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Create Order Failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onError() {

                }
            });
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        btnCheckOut = findViewById(R.id.btnCheckOut);
        userProfile = UserPreferenceUtils.getFromPreferences(getString(R.string.PreferenceUserProfile), getApplicationContext(), User.class);
        Intent intent = getIntent();
        previewOrderDto = (PreviewOrderDto) intent.getSerializableExtra(getString(R.string.cartToOrder));
        ivOrderDetailPreviewBack = findViewById(R.id.ivOrderDetailPreviewBack);
        ivOrderDetailPreviewBack.setOnClickListener(view -> {
            Intent intent1 = new Intent(OrderDetailPreviewActivity.this, ReceiverPreviewActivity.class);
            intent1.putExtra(getString(R.string.cartToOrder), (Serializable) previewOrderDto);
            startActivity(intent1);
        });
        rvOrderDetailPreview = findViewById(R.id.rvOrderDetailPreview);
        if (previewOrderDto != null){
            String orderId = UUID.randomUUID().toString();
            String userId = userProfile.getUserId();
            int totalMoney = 0;
            int quantity = 0;
            List<BookDetail> bookDetails = new ArrayList<>();
            for (CartItem cart: previewOrderDto.getCartItemList()) {
                quantity += Integer.valueOf(cart.getQuantity());
                totalMoney += Integer.valueOf(cart.getQuantity()) * Integer.valueOf(cart.getPrice());
                BookDetail bookDetail = new BookDetail(
                        cart.getBookId(),
                        cart.getBookName(),
                        cart.getImage(),
                        Long.parseLong(cart.getQuantity()),
                        Long.parseLong(cart.getPrice()),
                        cart.getPublisher());
                bookDetails.add(bookDetail);
            }
            long orderDetailId = 0;
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String orderDate = myDateObj.format(myFormatObj);

            OrderDetail orderDetail = new OrderDetail(
                    orderDetailId,
                    orderId,
                    orderDate,
                    previewOrderDto.getReceiverDetail(),
                    bookDetails);
            order = new Order(
                    orderId,
                    userId,
                    String.valueOf(totalMoney),
                    String.valueOf(quantity),
                    orderDetail);
            }
    }

    private void setAdapter(){
        if (order != null){
            OrderDetailAdapter adapter = new OrderDetailAdapter(order);
            rvOrderDetailPreview.setHasFixedSize(true);
            rvOrderDetailPreview.setAdapter(adapter);
            rvOrderDetailPreview.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void createOrder(Order order, CallBackData<Order, Response<Order>> getDataCallBack){
        OrderService orderService = OrderRepository.getOrderService();
        try{
            Call<Order> call = orderService.createOrder(order);
            call.enqueue(new Callback<Order>() {
                @Override
                public void onResponse(Call<Order> call, Response<Order> response) {
                    if (response.isSuccessful()){
                        getDataCallBack.onGetMapData(response);
                    } else {
                        Log.d("Create Order Failed", response.body().toString());
                        getDataCallBack.onGetMapData(response);
                    }
                }

                @Override
                public void onFailure(Call<Order> call, Throwable t) {
                    getDataCallBack.onGetMapData(null);
                    Toast.makeText(OrderDetailPreviewActivity.this, "Create Order Failed:\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Create Order Failed", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Create Order", e.getMessage());
        }
    }
}
