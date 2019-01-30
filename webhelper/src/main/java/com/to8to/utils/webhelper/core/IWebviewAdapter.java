package com.to8to.utils.webhelper.core;

/**
 * Created by same.li on 2018/1/11.
 * 适配各种webview的部分功能适配代理
 */

public interface IWebviewAdapter
{
     void addJavascriptInterface(Object actionObj, String actionField);

     void loadUrl(String url);

     void setJavaScriptEnabled(boolean enabled);

     boolean getJavaScriptEnabled();

}
