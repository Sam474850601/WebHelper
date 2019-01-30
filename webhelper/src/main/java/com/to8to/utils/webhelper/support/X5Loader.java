package com.to8to.utils.webhelper.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by same.li on 2018/1/12.
 * 加载x5内核.
 */

public final class X5Loader {


    private static final String TABLE_CACHE_TBS = "tbs";

    private static final String PARAM_SUCCESS = "success";

    //尝试次数
    private int tryTime = 0;

    //是否还在加载
    private  volatile boolean isRunning = false;

    //取消加载
    private volatile boolean isCancel = false;

    private OnLoadListener onLoadListener;
    private Context appContext;

    public  X5Loader() {

    }




    SharedPreferences sharedPreferences;

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 加载x5内核， 递归加载。加载时，需要网络获取当前使用哪个内核.可以使用cancel取消失败后不再进行加载。
     * @param context
     */
    public void load(Context context) {
        Log.d("loadx5", "load");
        this.appContext = context;
        sharedPreferences = appContext.getSharedPreferences(TABLE_CACHE_TBS, 0);
        isRunning = true;
        boolean hasLoaded = getCoreInitState();
        Log.d("loadx5", "hasLoaded="+hasLoaded);
        if (!hasLoaded) {
            QbSdk.setDownloadWithoutWifi(true);//
            final QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

                @Override
                public void onViewInitFinished(final boolean isSuccess) {
                    Log.d("loadx5", "isSuccess：" + isSuccess);
                    Log.d("loadx5", "tryTimes：" + tryTime);
                    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                    saveCoreInitState(isSuccess);
                    if (isCancel)
                        return;

                    if (!isSuccess) {
                        isRunning = true;
                        tryTime++;
                        QbSdk.clear(appContext);
                        Log.d("loadx5", "trye again：");
                        QbSdk.initX5Environment(appContext, this);
                        if (null != onLoadListener)
                            onLoadListener.onCompleted(X5Loader.this, false, tryTime);
                    } else {
                        Log.d("loadx5", "Success");
                        isRunning = false;
                        isCancel = true;
                        if (null != onLoadListener)
                            onLoadListener.onCompleted(X5Loader.this, true, tryTime);

                    }
                }

                @Override
                public void onCoreInitFinished() {
//                            Log.e("core init", " onCoreInitFinished");
                }
            };
            //x5内核初始化接口
            QbSdk.initX5Environment(appContext, cb);
        } else {
            if (null != onLoadListener)
                onLoadListener.onCompleted(this, true, 0);
        }
    }

    private void saveCoreInitState(boolean isSuccess) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PARAM_SUCCESS, isSuccess);
        editor.commit();
    }

    private boolean getCoreInitState() {
        return sharedPreferences.getBoolean(PARAM_SUCCESS, false);
    }


    /**
     * 取消下一次加载
     */
    public void cancel() {
        //防止取消2次
        if (isCancel)
            return;
        isRunning = false;
        isCancel = true;
        if (null != onLoadListener)
            onLoadListener.onCancel(tryTime);

    }

    public void setOnCoreInitListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }


    /**
     * 加载回调
     */
    public interface OnLoadListener {

        /**
         * 尝试加载结束
         *
         * @param isSuccess 是否初始化成功
         * @param tryTime   尝试次数
         */
        void onCompleted(X5Loader x5Loader, boolean isSuccess, long tryTime);

        /**
         * 取消时候回调
         *
         * @param tryTime
         */
        void onCancel(long tryTime);
    }


    /**
     * 初始化X5浏览器
     *
     * @param applicationContext
     */
    public static void initX5Lbs(Context applicationContext, X5Loader.OnLoadListener onLoadListener) {
        X5Loader x5Loader = new X5Loader();
        x5Loader.setOnCoreInitListener(onLoadListener);
        x5Loader.load(applicationContext);
    }


    public static X5Loader createLoader()
    {
        return new X5Loader();
    }
}
