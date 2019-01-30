package com.to8to.utils.webhelper.support.webviewadapter;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.to8to.utils.webhelper.core.IWebviewAdapter;

/**
 * Created by same.li on 2018/1/12.
 * android自带的webview部分功能适配代理
 */

public class NormolWebViewAdapter implements IWebviewAdapter {


    WebView webView;
    WebSettings webSettings;
    public NormolWebViewAdapter(android.webkit.WebView webView)
    {
        this.webView = webView;
        webSettings = webView.getSettings();
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavascriptInterface(Object actionObj, String actionField) {
        if(null == webView)
            return;
        webView.addJavascriptInterface(actionObj, actionField);
    }

    @Override
    public void loadUrl(String url) {
        if(null == webView)
            return;
        webView.loadUrl(url);
    }

    @Override
    public void setJavaScriptEnabled(boolean enabled) {
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean getJavaScriptEnabled() {
        return webSettings.getJavaScriptEnabled();
    }


}
