package com.example.sadowsm3.mynotificationreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyNotificationReceiver extends BroadcastReceiver {

    public static final String MY_NOTIFICATION_CHANNEL_ID = "myChannleId";
    public static final String MY_NOTIFICATION_CHANNEL_NAME = "myChannel";
    private static final String TAG = "MyNotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedMessage = intent.getStringExtra("notificationMessage");
        Log.d(TAG, "Message received: " + receivedMessage);
        displayNotification(context, receivedMessage);
    }

    private void displayNotification(Context context, String notificationText) {
        Log.d(TAG, "about to display notification: " + notificationText);
        PackageManager pm = context.getPackageManager();
        Intent LaunchIntent = null;
        String apppack = "com.example.sadowsm3.mybroadcastapplication";
        String name = "";
        try {
            if (pm != null) {
                ApplicationInfo app = context.getPackageManager().getApplicationInfo(apppack, 0);
                name = (String) pm.getApplicationLabel(app);
                LaunchIntent = pm.getLaunchIntentForPackage(apppack);
            }
            Toast.makeText(context,"Found it:" + name,Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = LaunchIntent; // new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder noti = new NotificationCompat.Builder(context ,MY_NOTIFICATION_CHANNEL_ID)
                .setContentTitle("My Notification")
                .setContentText(notificationText)
                .setSmallIcon(R.drawable.ic_stat_adb)
                .setContentIntent(pIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti.build());
    }
}
