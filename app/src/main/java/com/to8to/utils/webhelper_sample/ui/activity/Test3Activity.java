package com.to8to.utils.webhelper_sample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.housekeeper.R;
import com.to8to.utils.webhelper_sample.ui.view.ITest3View;
import com.to8to.utils.webhelper_sample.web.action.Test2Action;
import com.to8to.utils.webhelper_sample.web.action.TestAction;

/**
 * Created by same.li on 2018/1/29.
 */

public class Test3Activity   extends AppCompatActivity implements ITest3View {


    @Override
    public void setMessage(String message) {
        textView.setText(message);
    }


    @Override
    public void toast(String value) {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }

    TextView textView;

    X5WebView x5WebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        setTitle("test3");
        textView = (TextView) findViewById(R.id.tv_message);
        x5WebView = (X5WebView) findViewById(
                R.id.webview);
        x5WebView.addActions(TestAction.class, Test2Action.class);
        x5WebView.loadUrl("file:///android_asset/test3.html");
    }


}
