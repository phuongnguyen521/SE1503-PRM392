package com.example.se1503_ichinsan_bookapplication.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.se1503_ichinsan_bookapplication.R;

public class CommonUtils {
    public static void returnCircleAvatar(ImageView imageView, Context context, String urlImage){
        if (TextUtils.isEmpty(urlImage) || urlImage == null){
            urlImage = context.getString(R.string.default_avatar);
        }
        Glide.with(context)
                .load(urlImage)
                .fitCenter()
                .circleCrop()
                .into(imageView);
    }

    public static void returnRectangleAvatar (ImageView imageView, Context context, String urlImage){
        Glide.with(context)
                .load(urlImage)
                .fitCenter()
                .into(imageView);
    }
}
