package com.to8to.utils.webhelper_sample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebView;
import com.to8to.housekeeper.R;
import com.to8to.utils.webhelper.core.TWebHelper;
import com.to8to.utils.webhelper.support.webviewadapter.TbsWebViewAdapter;
import com.to8to.utils.webhelper_sample.ui.view.ITestView;
import com.to8to.utils.webhelper_sample.web.action.UserAction;

/**
 * Created by same.li on 2018/1/23.
 */

public class Test2Activity extends AppCompatActivity implements ITestView {



    @Override
    public void toast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        setTitle("test2");
        WebView  x5WebView = (WebView) findViewById(R.id.webview);
        TWebHelper.addAction(UserAction.class, this,
                new TbsWebViewAdapter(x5WebView));
        x5WebView.loadUrl("file:///android_asset/login.html");

    }
}
