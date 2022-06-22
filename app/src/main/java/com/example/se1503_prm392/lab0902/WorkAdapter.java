package com.example.se1503_prm392.lab0902;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.se1503_prm392.R;

import java.util.ArrayList;

public class WorkAdapter extends BaseAdapter {
    private WorkActivityLab0902 context;
    private int layout;
    private ArrayList<Work> workList;

    public WorkAdapter(WorkActivityLab0902 context, int layout, ArrayList<Work> workList) {
        this.context = context;
        this.layout = layout;
        this.workList = workList;
    }

    @Override
    public int getCount() {
        return workList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvWorkName;
        ImageView ivDelete, ivEdit;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvWorkName = view.findViewById(R.id.tvWorkName);
            holder.ivDelete = view.findViewById(R.id.ivDelete);
            holder.ivEdit = view.findViewById(R.id.ivEdit);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        Work work = workList.get(position);
        holder.tvWorkName.setText(work.getName());

        //Edit
        holder.ivEdit.setOnClickListener(view1 ->{
            context.DialogEditWork(work.getName(), work.getId());
        });

        holder.ivDelete.setOnClickListener(view1 -> {
            context.DialogDeleteWork(work.getName(), work.getId());
        });
        return view;
    }
}
