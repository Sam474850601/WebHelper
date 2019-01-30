package com.to8to.utils.webhelper.support.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.to8to.utils.webhelper.support.X5Loader;

/**
 * Created by same.li on 2018/1/25.
 */

public class X5InitService extends Service {

    public static void init(Context context)
    {
        context.startService(new Intent(context, X5InitService.class));
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("X5InitService", "onCreate");
        X5Loader.initX5Lbs(getApplicationContext(), new X5Loader.OnLoadListener() {
            @Override
            public void onCompleted(X5Loader x5Loader, boolean isSuccess, long tryTime) {
                Log.i("X5InitService", "initX5Lbs:"+isSuccess+", tryTime:"+tryTime);
                if(isSuccess)
                {
                    stopSelf();
                }
                else if( tryTime> 3 )
                {//超过3次失败，就取消下一次加载
                    x5Loader.cancel();
                }
            }

            @Override
            public void onCancel(long tryTime) {
                stopSelf();
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //冷启动
        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(getApplicationContext(), null);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
