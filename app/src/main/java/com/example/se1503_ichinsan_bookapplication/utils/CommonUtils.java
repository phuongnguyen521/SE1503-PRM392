package com.example.se1503_ichinsan_bookapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.se1503_ichinsan_bookapplication.R;
import com.example.se1503_ichinsan_bookapplication.dto.User;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;
import java.util.Date;

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

    public static boolean isSignedInYet(FirebaseUser user, User userProfile, Context context){
        boolean result = user != null && userProfile != null;
        if (!result){
            Toast.makeText(context,"Please Sign In" ,Toast.LENGTH_LONG).show();
        }
        return result;
    }

    public static String GetCurrencyFormat(String amount){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(Double.parseDouble(amount));
    }

    public static int getNotifcationId(){
        return (int) new Date().getTime();
    }
}
