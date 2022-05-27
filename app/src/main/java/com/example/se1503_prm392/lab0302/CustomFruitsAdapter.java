package com.example.se1503_prm392.lab0302;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.se1503_prm392.R;
import com.example.se1503_prm392.lab0301.Soccer;

import java.util.List;

public class CustomFruitsAdapter extends ArrayAdapter<Fruit> {
    public CustomFruitsAdapter(@NonNull Context context, int resource,@NonNull List<Fruit> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_fruit, parent, false);
        }

        // Get the data item for this position
        Fruit fruit = getItem(position);

        // Lookup view for data population
        ImageView ivImageFruit = (ImageView) convertView.findViewById(R.id.ivImageFruit);
        TextView tvNameFruit = (TextView) convertView.findViewById(R.id.tvNameFruit);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        // Populate the data into the template view using the data object
        ivImageFruit.setImageDrawable(getContext().getResources().getDrawable(fruit.getAvatar()));
        tvNameFruit.setText(fruit.getName());
        tvDescription.setText(fruit.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}
