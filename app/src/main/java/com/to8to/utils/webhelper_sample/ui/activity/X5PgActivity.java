package com.to8to.utils.webhelper_sample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.to8to.utils.webhelper.support.ui.widget.X5PbWebView;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.housekeeper.R;

/**
 * Created by same.li on 2018/2/1.
 */

public class X5PgActivity extends AppCompatActivity {
    final String url = "http://www.qq.com";
    private X5PbWebView x5PbWebView;
    private X5WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5pgdemo);

        setTitle("带进度条的webview");
        x5PbWebView = (X5PbWebView) findViewById(R.id.wv_x5pg);
        //ProgressBar progressBar =  x5PgWebView.getProgressBar();
        //  progressBar.setProgress(0);
        webView = x5PbWebView.getWebView();
        x5PbWebView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
//                //WebViewClient设置覆盖默认的后，调用这个方法恢复进度条工作
//                x5PbWebView.onPageStarted(webView, s, bitmap);
//            }
//
//
//            @Override
//            public void onPageFinished(WebView webView, String s) {
//                //WebViewClient设置覆盖默认的后，调用这个方法恢复进度条工作
//                x5PbWebView.onPageFinished(webView, s);
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient()
//
//        {
//            @Override
//            public void onProgressChanged(WebView webView, int i) {
                 //WebViewClient设置覆盖默认的后，调用这个方法恢复进度条工作
//                x5PbWebView.onProgressChanged(webView, i);
//            }
//
//        });


    }


    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        x5PbWebView.destroy();
    }

}
