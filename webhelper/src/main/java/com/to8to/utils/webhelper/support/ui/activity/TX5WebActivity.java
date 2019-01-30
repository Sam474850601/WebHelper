package com.to8to.utils.webhelper.support.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.support.bean.ToolbarAttr;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.utils.webhelper.support.webservice.action.WebhelperAction;

import java.lang.ref.WeakReference;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by same.li on 2018/1/18.
 */

public  abstract  class TX5WebActivity<T> extends TToolbarActivity<T>
{
    public static final String PARAM_TOOBARDATA = "toolbardata";
    X5WebView x5WebView;
    ProgressBar progressBar;

    final  DefaultChromeClient defaultChromeClient = new DefaultChromeClient();
    final  DefaultWebViewClient defaultWebViewClient = new DefaultWebViewClient();

    public static final String PARAM_URL  = "url";

    protected  abstract  void onInitViews(View parent, X5WebView x5WebView);


    final  static  class  DefaultWebViewClient extends WebViewClient
    {
        WeakReference<TX5WebActivity> activityWeakReference;

        public void setTX5WebActivity(TX5WebActivity tx5WebActivity) {
            this.activityWeakReference = new WeakReference<TX5WebActivity>(tx5WebActivity);
        }


        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                webView.loadUrl(url);
                return true;
            }
            return false;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);

            TX5WebActivity activity =  activityWeakReference.get();
            if(null == activity)
                return;
            if(activity.isFinishing())
            {
                activityWeakReference.clear();
                return;
            }

            if (GONE !=  activity.progressBar.getVisibility() )
                activity.progressBar.setVisibility(GONE);
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);


            TX5WebActivity activity =  activityWeakReference.get();
            if(null == activity)
                return;
            if(activity.isFinishing())
            {
                activityWeakReference.clear();
                return;
            }
            if (VISIBLE !=  activity.progressBar.getVisibility() )
                activity.progressBar.setVisibility(VISIBLE);

        }
    }


     final static  class DefaultChromeClient extends WebChromeClient
    {

        WeakReference<TX5WebActivity> activityWeakReference;

        public void setTX5WebActivity(TX5WebActivity tx5WebActivity) {
            this.activityWeakReference = new WeakReference<TX5WebActivity>(tx5WebActivity);
        }


        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            TX5WebActivity activity =  activityWeakReference.get();
            if(null == activity)
                return;
            if(activity.isFinishing())
            {
                activityWeakReference.clear();
                return;
            }
            activity.progressBar.setProgress(newProgress);

            super.onProgressChanged(webView, newProgress);
        }
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        final ToolbarAttr toolbarAttr = (ToolbarAttr) getIntent().getSerializableExtra(PARAM_TOOBARDATA);
        if (null != toolbarAttr) {
            final String title = toolbarAttr.getTitle();
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            final String subtitle = toolbarAttr.getSubTitle();
            if (!TextUtils.isEmpty(subtitle)) {
                setSubtitle(title);
            }

            final int navigationIcon = toolbarAttr.getNavigationIcon();
            if ( 0 != navigationIcon) {
                setNavigationIcon(navigationIcon);
            }

            final int logo = toolbarAttr.getLogo();
            if (0 != logo ) {
                setLogo(logo);
            }

            final int titleColor = toolbarAttr.getTitleColor();
            if (0 != titleColor ) {
                setTitleColor(titleColor);
            }
            final int subTitleColor = toolbarAttr.getSubTitleColor();
            if (0 != subTitleColor ) {
                setSubtitleTextColor(subTitleColor);
            }

            final int background = toolbarAttr.getBackground();
            if (0 != background ) {
                setToolbarBackgroundColor(background);
            }

        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_x5web;
    }

    @Override
    public final void onInitViews(View parent) {
        x5WebView = (X5WebView) parentView.findViewById(R.id.wv_x5);
        defaultWebViewClient.setTX5WebActivity(this);
        x5WebView.setWebViewClient(defaultWebViewClient);
        setNavigationIcon(R.mipmap.icon_close_white);
        setSubtitleTextColor(Color.WHITE);
        setTitleColor(Color.WHITE);
        progressBar = (ProgressBar) parentView.findViewById(R.id.pb_web);
        defaultChromeClient.setTX5WebActivity(this);
        x5WebView.setWebChromeClient(defaultChromeClient);
        addAction(WebhelperAction.class);
        onInitViews(parentView, x5WebView);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (x5WebView != null && x5WebView.canGoBack()) {
                x5WebView.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {

        if (this.x5WebView != null) {
            ViewParent x5ParentView = x5WebView.getParent();
            if(x5ParentView instanceof ViewGroup)
            {
                ViewGroup parent =  ((ViewGroup)x5ParentView);
                parent.removeView(x5WebView);
            }
            x5WebView.destroy();
        }
        super.onDestroy();
    }





    public void addAction(Class<? extends TBaseAction> actionClass)
    {
        x5WebView.addActions(actionClass);
    }


    public void addActions(Class<? extends TBaseAction>...actionClass)
    {
        x5WebView.addActions(actionClass);
    }

}
