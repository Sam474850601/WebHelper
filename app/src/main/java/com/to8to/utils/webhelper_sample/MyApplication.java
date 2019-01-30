package com.to8to.utils.webhelper_sample;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.to8to.utils.webhelper.support.service.X5InitService;
import com.to8to.utils.webhelper.utils.WLog;

/**
 * Created by same.li on 2018/1/12.
 */

public class MyApplication extends Application  {




    @Override
    public void onCreate() {
        super.onCreate();
        WLog.init(getApplicationContext());
        //初始化x5内核
        X5InitService.init(getApplicationContext());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
