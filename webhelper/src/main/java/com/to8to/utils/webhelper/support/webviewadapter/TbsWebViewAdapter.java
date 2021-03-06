package com.to8to.utils.webhelper.support.webviewadapter;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.to8to.utils.webhelper.core.IWebviewAdapter;

/**
 * Created by same.li on 2018/1/12.
 * 腾讯 x5 webview部分功能适配代理
 */

public class TbsWebViewAdapter  implements IWebviewAdapter {


    WebView webView;
    WebSettings webSettings;
    public TbsWebViewAdapter(com.tencent.smtt.sdk.WebView webView)
    {
        this.webView = webView;
        webSettings = webView.getSettings();
    }

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
