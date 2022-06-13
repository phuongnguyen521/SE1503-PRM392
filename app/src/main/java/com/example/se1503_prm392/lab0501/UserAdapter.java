package com.example.se1503_prm392.lab0501;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> userList;
    private Context mContext;

    public UserAdapter(ArrayList<User> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //Inflate the custom layout
        View view = inflater.inflate(R.layout.item_user, parent, false);

        //Return a holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvUsername.setText(user.getUsername());
        holder.tvFullname.setText(user.getFullname());
        holder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        TextView tvFullname;
        TextView tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
