package com.example.se1503_ichinsan_bookapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.Receiver;
import com.example.se1503_ichinsan_bookapplication.dto.ReceiverDetail;
import com.example.se1503_ichinsan_bookapplication.ui.user.receiver.ReceiverDetailActivity;

import java.io.Serializable;
import java.util.List;

public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverAdapter.ViewHolder>{

    private List<ReceiverDetail> receiverDetails;
    private Context mContext;
    private boolean isPreview;
    private ReceiverDetail selectedReceiver;

    public ReceiverAdapter(List<ReceiverDetail> receiverDetails, Context mContext, boolean isPreview) {
        this.receiverDetails = receiverDetails;
        this.mContext = mContext;
        this.isPreview = isPreview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_receiver_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReceiverDetail receiverDetail = receiverDetails.get(position);
        holder.tvReceiverName.setText(receiverDetail.getName());
        holder.tvReceiverAddress.setText(receiverDetail.getAddress());
        holder.tvReceiverPhone.setText(receiverDetail.getPhone());
        holder.tvReceiverEmail.setText(receiverDetail.getEmail());

        if (isPreview) {
            holder.constraintReceiverItem.setOnClickListener(view -> {
                holder.constraintReceiverItem.setBackgroundColor(Color.parseColor("#A402ABF8"));
                this.selectedReceiver = receiverDetail;
            });
        } else {
            holder.constraintReceiverItem.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, ReceiverDetailActivity.class);
                intent.putExtra(mContext.getString(R.string.userReceiverDetail), (Serializable) receiverDetail);
                mContext.startActivity(intent);
            });
        }
    }

    public ReceiverDetail getSelectedReceiver(){
        return selectedReceiver;
    }

    @Override
    public int getItemCount() {
        return receiverDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvReceiverName;
        TextView tvReceiverAddress;
        TextView tvReceiverPhone;
        TextView tvReceiverEmail;
        ConstraintLayout constraintReceiverItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvReceiverName = itemView.findViewById(R.id.tvReceiverName);
            tvReceiverAddress = itemView.findViewById(R.id.tvReceiverAddress);
            tvReceiverPhone = itemView.findViewById(R.id.tvReceiverPhone);
            tvReceiverEmail = itemView.findViewById(R.id.tvReceiverEmail);
            constraintReceiverItem = itemView.findViewById(R.id.constraintReceiverItem);
        }
    }
}
