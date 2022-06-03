package com.example.se1503_prm392.lab04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class CustomBookingFoodAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Food> foodList;
    private final LayoutInflater inflater;
    private RadioButton rbFood;
    private ImageView ivFoodImage;
    private TextView tvFoodName;
    private int selectedFood = -1;

    public CustomBookingFoodAdapter(Context context, ArrayList<Food> foodList, int selectedFood) {
        this.context = context;
        this.foodList = foodList;
        if (selectedFood > -1){
            this.selectedFood = selectedFood;
        }
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_bookingfood_item, null);

        setData(position, view);

        rbFood.setOnClickListener(this::itemCheckChanged);

        ivFoodImage.setOnClickListener(this::itemCheckChanged);

        tvFoodName.setOnClickListener(this::itemCheckChanged);

        return view;
    }

    //Set Data for item in activity view
    private void setData(int position, View view){
        rbFood = view.findViewById(R.id.rbFood);
        ivFoodImage = view.findViewById(R.id.ivFoodImage);
        tvFoodName = view.findViewById(R.id.tvFoodName);

        Food food = foodList.get(position);
        ivFoodImage.setImageResource(food.getImage());
        tvFoodName.setText(food.getName());

        rbFood.setChecked(false);
        if (this.selectedFood > -1){
            rbFood.setChecked(position == selectedFood);
        }
        rbFood.setTag(position);
    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    private void itemCheckChanged(View v) {
        selectedFood = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    //https://github.com/sonusurender/List_RadioButton_Demo/blob/83de0c0936b105264132816a11524bdb762af412/app/src/main/java/com/listradiobutton_demo/adapter/GridListAdapter.java#L20

    public Food getSelectedItem() {
        if (selectedFood != -1) {
            return foodList.get(selectedFood);
        }
        return null;
    }
}
