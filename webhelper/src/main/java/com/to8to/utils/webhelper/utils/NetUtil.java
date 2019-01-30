package com.to8to.utils.webhelper.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by same.li on 2018/2/9.
 */

public final class NetUtil {


    public enum Status
    {
        NO_NETWORK ,
        NETWORK_WIFI,
        NETWORK_MOBILE
    }

    /**
     * 检查网络连接
     *
     * @param context
     * @return 0为无网络状态，1为wifi连接状态，2为移动网络连接状态
     */
    public static Status checkNetwork(Context context) {
        if (context == null) {
            return Status.NETWORK_WIFI;
        }
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifi != null && wifi.isConnected()) {
            return Status.NETWORK_WIFI;
        }
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobile != null && mobile.isConnected()) {
            return Status.NETWORK_MOBILE;
        }
        return Status.NO_NETWORK;
    }

}
