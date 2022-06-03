package com.example.se1503_prm392.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {

    private ListView lvDrink;
    private Button btnBookingDrink;
    private int positionDrink = -1;
    private String currentFood;
    private ArrayList<Food> drinkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        setReceivedDataFromBookingFood();
        setData();

        CustomBookingFoodAdapter drinkAdapter = new CustomBookingFoodAdapter(
                DrinkActivity.this,
                drinkList,
                positionDrink);
        lvDrink.setAdapter(drinkAdapter);

        btnBookingDrink.setOnClickListener(view -> {
            Food food = drinkAdapter.getSelectedItem();
            Intent intent = new Intent(DrinkActivity.this, BookingFoodActivity.class);
            intent.putExtra(BookingFoodConst.IS_CHANGED, true);
            intent.putExtra(BookingFoodConst.SELECTED_DRINK,food.getName());
            intent.putExtra(BookingFoodConst.POSITION_DRINK, drinkList.indexOf(food));
            intent.putExtra(BookingFoodConst.CURRENT_FOOD, currentFood);
            startActivity(intent);
            finish();
        });
    }

    //Check if user has chosen food before
    private void setReceivedDataFromBookingFood(){
        Intent intent = getIntent();
        currentFood = intent.getStringExtra(BookingFoodConst.CURRENT_FOOD);
        positionDrink = intent.getIntExtra(BookingFoodConst.POSITION_DRINK, -1);
    }

    //Set Data for item in activity view
    private void setData(){
        lvDrink = findViewById(R.id.lvDrink);
        btnBookingDrink = findViewById(R.id.btnBookingDrink);
        drinkList = new ArrayList<>();
        drinkList.add(new Food(R.drawable.pepsi, "Pepsi"));
        drinkList.add(new Food(R.drawable.heineken1, "Heineken"));
        drinkList.add(new Food(R.drawable.tiger1, "Tiger"));
        drinkList.add(new Food(R.drawable.saigondo, "Sài gòn Đỏ "));
    }
}