package com.linli.consumer.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/10/13.
 */
public class UpdateAppService extends Service {
    Intent intent;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
