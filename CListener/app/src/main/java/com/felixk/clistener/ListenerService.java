package com.felixk.clistener;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Felix K on 16/08/2017.
 */

public class ListenerService extends Service {
    VolumeLevels vl;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	//starts service
    @Override
    public void onCreate() {
        super.onCreate();
		
	//registers content observer from VolumeLevels.java to observe system settings
        vl = new VolumeLevels(this, new Handler());
        getApplicationContext().getContentResolver()
                .registerContentObserver(android.provider.Settings.System.CONTENT_URI, true,
                        vl);
						
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
    }
	//stops service
    @Override
    public void onDestroy() {
        super.onDestroy();
		getApplicationContext().getContentResolver().unregisterContentObserver(vl);
		
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }

}