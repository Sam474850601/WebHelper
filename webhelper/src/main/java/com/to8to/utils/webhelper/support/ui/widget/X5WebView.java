package com.to8to.utils.webhelper.support.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.core.TWebHelper;
import com.to8to.utils.webhelper.support.webviewadapter.TbsWebViewAdapter;
import com.to8to.utils.webhelper.utils.WLog;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by same.li on 2018/1/12.
 */

public class X5WebView extends WebView {
    public static String TAG  = X5WebView.class.getSimpleName();


    public X5WebView(Context arg0) {
        super(arg0);
        initWebViewSettings();
    }


    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        initWebViewSettings();
    }



    private final WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };



    private void initWebViewSettings() {
        WLog.init(getContext());
        this.setWebViewClient(client);
        this.getView().setClickable(true);
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        //去掉滚动条
        IX5WebViewExtension  extension =  this.getX5WebViewExtension();
        if(null != extension)
        {
            extension.setScrollBarFadingEnabled(false);
        }
        setScreenFunc(false,true,1);
    }


    /**
     * 设置播放模式, 需要配置如下：
     * android:configChanges="orientation|screenSize|keyboardHidden"
     * android:screenOrientation="portrait"
     * @param standardFullScreen // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
     * @param supportLiteWnd     // false：关闭小窗；true：开启小窗；不设置默认true，
     * @param DefaultVideoScreen // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
     */
    public void setScreenFunc(boolean standardFullScreen, boolean supportLiteWnd, int DefaultVideoScreen) {

        IX5WebViewExtension extension =  this.getX5WebViewExtension();
        if (null != extension) {

            Bundle data = new Bundle();

            data.putBoolean("standardFullScreen", standardFullScreen);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，

            data.putBoolean("supportLiteWnd", supportLiteWnd);// false：关闭小窗；true：开启小窗；不设置默认true，

            data.putInt("DefaultVideoScreen", DefaultVideoScreen);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1

            extension.invokeMiscMethod("setVideoParams", data);
        }
    }



    public void addActions(Class<? extends TBaseAction>...actionClasses)
    {
        TWebHelper.addActions(new TbsWebViewAdapter(this),  getContext(), actionClasses);
    }

    public void addAction(Class<? extends TBaseAction> actionClass)
    {
        addActions(actionClass);
    }


}
