package com.example.se1503_prm392.Slot10CustomizedAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.se1503_prm392.R;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TraiCay> traiCayList;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }

    @Override
    public int getCount() {//Tra ve so hien thi tren listView
        return traiCayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    //Tra ve view moi khi goi adapter
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        //Anh xa view
        TextView tvTenTraiCay = (TextView) view.findViewById(R.id.tvTenTraiCay);
        TextView tvMoTaTraiCay = (TextView) view.findViewById(R.id.tvMoTaTraiCay);
        ImageView ivHinhTraiCay = (ImageView) view.findViewById(R.id.ivHinhTraiCay);

        //Gan gia tri
        TraiCay traiCay = traiCayList.get(position);
        tvTenTraiCay.setText(traiCay.getTen());
        tvMoTaTraiCay.setText(traiCay.getMota());
        ivHinhTraiCay.setImageResource(traiCay.getHinh());

        return view;
    }
}
