package com.example.se1503_prm392.lab0502;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class Lab0502OSAdapter extends RecyclerView.Adapter<Lab0502OSAdapter.ViewHolder> {

    private ArrayList<Module> moduleList;

    public Lab0502OSAdapter(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View view = layoutInflater.inflate(R.layout.lab0502_item_operatingsystem, parent, false);

        //Return the a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Module module = moduleList.get(position);

        //Set item views based on view and data model
        holder.ivOSImage.setImageResource(module.getImageModule());
        holder.tvOSTitle.setText(module.getTitle());
        holder.tvOSDescription.setText(module.getDescription());
        holder.tvOSType.setText(module.getOperationSystem());
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivOSImage;
        TextView tvOSTitle;
        TextView tvOSDescription;
        TextView tvOSType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivOSImage = itemView.findViewById(R.id.ivOSImage);
            tvOSTitle = itemView.findViewById(R.id.tvOSTitle);
            tvOSDescription = itemView.findViewById(R.id.tvOSDescription);
            tvOSType = itemView.findViewById(R.id.tvOSType);
        }
    }
}
