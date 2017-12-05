package com.example.sadowsm3.mynotificationreceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyNotificationService extends Service {

    MyNotificationReceiver receiver;

    public MyNotificationService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new MyNotificationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.sadowsm3.MY_ACTION");
        registerReceiver(receiver, intentFilter,
                "com.example.sadowsm3.my_permissions.MY_PERMISSION", null);
        initNotificationChannels(this);
    }

    private void initNotificationChannels(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = MyNotificationReceiver.MY_NOTIFICATION_CHANNEL_ID;
        CharSequence channelName = MyNotificationReceiver.MY_NOTIFICATION_CHANNEL_NAME;
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId,
                channelName, importance);
        notificationManager.createNotificationChannel(notificationChannel);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
