package com.felixk.ccdetector;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Felix K on 22/08/2017.
 */

public class VolumeMonitorService extends Service {
    GetVolumeLevels gvl;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {

        gvl = new GetVolumeLevels(this, new Handler());
        getApplicationContext().getContentResolver()
                .registerContentObserver(android.provider.Settings.System.CONTENT_URI, true,
                        gvl);
    }

    public void onDestroy(){
        super.onDestroy();
        getApplication().getContentResolver().unregisterContentObserver(gvl);
    }

}
