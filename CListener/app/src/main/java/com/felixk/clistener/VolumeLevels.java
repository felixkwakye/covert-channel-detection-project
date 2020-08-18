package com.felixk.clistener;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;

/**
 * Created by Felix K on 16/08/2017.
 */

public class VolumeLevels extends ContentObserver {
    Context context;

    public VolumeLevels(Context c, Handler handler) {
        super(handler);
        context = c;
        AudioManager am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        int vol = am.getStreamVolume(AudioManager.STREAM_RING);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_RING);

        //Prints 0 if volume levels is between 0 and 7
        if (currentVolume >= 0 && currentVolume <4) {
            System.out.println("0");

        }
        //Prints 1 if volume levels is between 5 and 7
        if (currentVolume >=5 && currentVolume <= 7) {
            System.out.println("1");
        }
    }
}