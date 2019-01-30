package com.to8to.utils.webhelper_sample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.to8to.housekeeper.R;
import com.to8to.utils.webhelper.support.ui.dialog.CommonDialog;
import com.to8to.utils.webhelper.support.ui.dialog.ShareDialog;
import com.to8to.utils.webhelper.support.ui.view.IShareView;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.utils.webhelper.support.webservice.action.WebhelperAction;
import com.to8to.utils.webhelper.utils.WLog;

/**
 * Created by same.li on 2018/2/8.
 */

public class DialogSampleActivity extends AppCompatActivity implements IShareView
{


    X5WebView x5WebView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        setTitle("创建dialog");

        x5WebView   = (X5WebView) findViewById(R.id.webview);
        x5WebView.addAction(WebhelperAction.class);
        x5WebView.loadUrl("file:///android_asset/share.html");

    }


    public void onFastCliked(View view)
    {
        CommonDialog commonDialog  = new CommonDialog(R.layout.dialog_fast,
                this) {
            @Override
            public void onInit(Window window, View view) {
                TextView textView =  findViewById(R.id.tv_message);
                textView.setText("hello world");
            }
        };
        commonDialog.show();
    }


    ShareDialog shareDialog;

    @Override
    public void onShareDialogPrepare(ShareDialog dialog) {
        shareDialog = dialog;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shareDialog.onActivityResult(requestCode, resultCode, data);
        WLog.log("requestCode="+1, "resultCode="+1,"data="+data);
    }
}
