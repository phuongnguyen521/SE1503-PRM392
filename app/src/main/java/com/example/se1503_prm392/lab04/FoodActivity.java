package com.example.se1503_prm392.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    private ListView lvFood;
    private Button btnBookingFood;
    private int positionFood = -1;
    private String currentDrink;
    private ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        setReceivedDataFromBookingFood();
        setData();

        CustomBookingFoodAdapter foodAdapter = new CustomBookingFoodAdapter(
                FoodActivity.this,
                foodList,
                positionFood);
        lvFood.setAdapter(foodAdapter);

        btnBookingFood.setOnClickListener(view -> {
            Food food = foodAdapter.getSelectedItem();
            Intent intent = new Intent(FoodActivity.this, BookingFoodActivity.class);
            intent.putExtra(BookingFoodConst.IS_CHANGED, true);
            intent.putExtra(BookingFoodConst.SELECTED_FOOD,food.getName());
            intent.putExtra(BookingFoodConst.POSITION_FOOD, foodList.indexOf(food));
            intent.putExtra(BookingFoodConst.CURRENT_DRINK, currentDrink);
            startActivity(intent);
            finish();
        });
    }

    //Check if user has chosen food before
    private void setReceivedDataFromBookingFood(){
        Intent intent = getIntent();
        currentDrink = intent.getStringExtra(BookingFoodConst.CURRENT_DRINK);
        positionFood = intent.getIntExtra(BookingFoodConst.POSITION_FOOD, -1);
    }

    //Set Data for item in activity view
    private void setData(){
        lvFood = findViewById(R.id.lvFood);
        btnBookingFood = findViewById(R.id.btnBookingFood);
        foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.pho, "Phở Hà Nội"));
        foodList.add(new Food(R.drawable.bunbohue, "Bún Bò Huế"));
        foodList.add(new Food(R.drawable.miquang, "Mì Quảng"));
        foodList.add(new Food(R.drawable.hutieu, "Hủ Tíu Sài Gòn"));
    }
}