package com.example.se1503_prm392.lab0301;

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

import java.util.List;

public class CustomSoccersAdapter extends ArrayAdapter<Soccer> {
    public CustomSoccersAdapter(@NonNull Context context, int resource, @NonNull List<Soccer> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_soccer, parent, false);
        }

        // Get the data item for this position
        Soccer soccer = getItem(position);

        // Lookup view for data population
        ImageView ivAvatarSoccer = (ImageView) convertView.findViewById(R.id.ivAvatarSoccer);
        ImageView ivCountry = (ImageView) convertView.findViewById(R.id.ivCountry);
        TextView tvNameSoccer = (TextView) convertView.findViewById(R.id.tvNameSoccer);
        TextView tvDobAndAge = (TextView) convertView.findViewById(R.id.tvDobAndAge);
        // Populate the data into the template view using the data object
        ivAvatarSoccer.setImageDrawable(getContext().getResources().getDrawable(soccer.getAvatar()));
        ivCountry.setImageDrawable(getContext().getResources().getDrawable(soccer.getCountryImage()));
        tvNameSoccer.setText(soccer.getName());
        tvDobAndAge.setText(soccer.getDob() + ", " + soccer.getAge());
        // Return the completed view to render on screen
        return convertView;
    }
}
