package com.felixk.ccdetector;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.CheckBox;

/**
 * Created by Felix K on 22/08/2017.
 */
public class GetVolumeLevels extends ContentObserver {
    Context context;
    CheckBox ckbx;
    int values[] = new int[4];

    private static final int MY_NOTIFICATION_ID = 1;
    NotificationManager notificationManager;
    Notification myNotification;

    public GetVolumeLevels(Context c, Handler handler) {
        super(handler);
        context = c;
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();

    }


    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Alert();
        //final AudioManager aM = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void Alert() {
        Intent intent = new Intent();

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Covert Channel!")
                .setContentText("Covert Channel Communicaton Detected")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }

}

