package com.example.se1503_ichinsan_bookapplication.utils.dto;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class UserPreferenceUtils<T> {

    public UserPreferenceUtils() {
    }

    public static <T> void addToPreferences (T object, @NotNull String key, @NotNull Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preEditor = preferences.edit();
        //Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(object);
        //save that string in preferences
        boolean result = preEditor.putString(key, json).commit();
        if (!result){
            Log.d("UserPreference - Add", "Cannot add object into SharedPreferences");
        }
    }

    public static <T> T getFromPreferences (@NotNull String key, @NotNull Context context, Class<T> className){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //read json string from references
        String json = preferences.getString(key, null);
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        Gson gson = new Gson();
        T object = gson.fromJson(json, className);
//        if (object == null) {
//            object = null;
//        }
        return object;
    }

    public static <T> void editObjectOfPreferences (T object, @NotNull String key, @NotNull Context context){
        boolean isDeleted = removeObjectFromPreference(key, context);
        if (isDeleted){
            addToPreferences(object, key, context);
        } else{
            Log.d("UserPreference - EditRe", "Cannot Remove when edit object of preferences");
        }
    }

    public static boolean removeObjectFromPreference(@NotNull String key, @NotNull Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preEditor = preferences.edit();
        preEditor.remove(key);
        boolean result = preEditor.commit();
        return result;
    }
}
