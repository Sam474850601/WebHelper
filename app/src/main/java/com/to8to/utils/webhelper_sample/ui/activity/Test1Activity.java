package com.to8to.utils.webhelper_sample.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.to8to.housekeeper.R;
import com.to8to.utils.webhelper.core.TWebHelper;
import com.to8to.utils.webhelper.support.webviewadapter.NormolWebViewAdapter;
import com.to8to.utils.webhelper_sample.ui.view.ITestView;
import com.to8to.utils.webhelper_sample.web.action.UserAction;

/**
 * Created by same.li on 2018/1/23.
 */

public class Test1Activity extends AppCompatActivity implements ITestView {



    @Override
    public void toast(String c) {
        Toast.makeText(this, ""+c, Toast.LENGTH_SHORT).show();
    }



    android.webkit.WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        setTitle("需求1");
        webView = (android.webkit.WebView) findViewById(R.id.webview);
        //注入依赖
        TWebHelper.addAction(UserAction.class, this,
                new NormolWebViewAdapter(webView));
        webView.loadUrl("file:///android_asset/userInfo.html");

    }
}
