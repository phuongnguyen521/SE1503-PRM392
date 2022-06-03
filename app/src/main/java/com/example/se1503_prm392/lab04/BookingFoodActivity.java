package com.example.se1503_prm392.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.se1503_prm392.R;

public class BookingFoodActivity extends AppCompatActivity {

    private Button btnFood;
    private Button btnDrink;
    private Button btnExit;
    private TextView tvDisplay;
    private int positionFood = -1;
    private int positionDrink = -1;
    private String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_food);

        setData();
        isChangedData();

        btnFood.setOnClickListener(view -> {
            isResultNull();
            setIntent(FoodActivity.class, false, BookingFoodConst.POSITION_FOOD, positionFood, BookingFoodConst.CURRENT_DRINK, result[1]);
        });

        btnDrink.setOnClickListener(view -> {
            isResultNull();
            setIntent(DrinkActivity.class, false, BookingFoodConst.POSITION_DRINK, positionDrink, BookingFoodConst.CURRENT_FOOD, result[0]);
        });

        btnExit.setOnClickListener(view -> {
            isResultNull();
            setIntent(BookingFoodActivity.class, true, "",  -1, "","");
        });
    }

    //Set Data for item in activity view
    private void setData(){
        btnFood = findViewById(R.id.btnFood);
        btnDrink = findViewById(R.id.btnDrink);
        btnExit = findViewById(R.id.btnExit);
        tvDisplay = findViewById(R.id.tvDisplay);
    }

    //Check if user choose food or drink
    private void isChangedData(){
        Intent intent = getIntent();
        boolean isChanged = intent.getBooleanExtra(BookingFoodConst.IS_CHANGED, false);
        if (isChanged){
            TransferData(intent);
        }
    }

    //Transfer Data from user's selection
    private void TransferData(Intent intent){
        String food = intent.getStringExtra(BookingFoodConst.SELECTED_FOOD);
        String drink = intent.getStringExtra(BookingFoodConst.SELECTED_DRINK);
        String currentFood = intent.getStringExtra(BookingFoodConst.CURRENT_FOOD);
        String currentDrink = intent.getStringExtra(BookingFoodConst.CURRENT_DRINK);
        if (TextUtils.isEmpty(food) && TextUtils.isEmpty(drink)){
            tvDisplay.setText(BookingFoodConst.CHOOSE_FOOD + " - " + BookingFoodConst.CHOOSE_DRINK);
        } else if (!TextUtils.isEmpty(food)){
            tvDisplay.setText(food + " - " + currentDrink);
            positionFood = intent.getIntExtra(BookingFoodConst.POSITION_FOOD,-1);
        } else {
            tvDisplay.setText(currentFood + " - " + drink);
            positionDrink = intent.getIntExtra(BookingFoodConst.POSITION_DRINK,-1);
        }
        result = tvDisplay.getText().toString().split(" - ");
    }

    private void setIntent(Class<?> cls, boolean isExit, String positionName, int position,
                           String currentFood, String valueCurrentFood){
        if (!isExit){
            Intent intent = new Intent(BookingFoodActivity.this, cls);
            intent.putExtra(positionName, position);
            intent.putExtra(currentFood, valueCurrentFood);
            startActivity(intent);
        }
        finish();
    }

    private void isResultNull(){
        if (result == null){
            result = new String[2];
            result[0] = BookingFoodConst.CHOOSE_FOOD;
            result[1] = BookingFoodConst.CHOOSE_DRINK;
        }
    }
}