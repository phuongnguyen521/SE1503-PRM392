package com.example.se1503_ichinsan_bookapplication.utils.channel;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.se1503_ichinsan_bookapplication.R;

public class MyApplication extends Application {

    public static final String CHANNEL_ID = "CHANNEL_1";
    private String notificationName;
    private String notificationContent;

    public MyApplication() {
    }

    public MyApplication(String notificationName, String notificationContent) {
        this.notificationName = notificationName;
        this.notificationContent = notificationContent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = notificationName == null ? "name" : notificationName;
            String description = notificationContent == null ? "content" : notificationContent;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
