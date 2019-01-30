package com.to8to.utils.webhelper.support.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.to8to.utils.webhelper.support.bean.ToolbarBuilder;
import com.to8to.utils.webhelper.support.bean.ToolbarAttr;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;

/**
 * Created by same.li on 2018/2/1.
 */

public class TDefaultWebActivity extends TX5WebActivity {


    public static void jumpX5Browser(Context context, String url, String title) {
        jumpX5Browser(context, url, new ToolbarBuilder().setTitle(title).build());

    }

    public static void jumpX5Browser(Context context, String url, String title, String subtitle) {
        jumpX5Browser(context, url, new ToolbarBuilder().setTitle(title).setSubTitle(subtitle).build());
    }

    public static void jumpX5Browser(Context context, String url, String title, int navigationIcon) {
        jumpX5Browser(context, url, new ToolbarBuilder().setTitle(title).setNavigationIcon(navigationIcon).build());
    }


    public static void jumpX5Browser(Context context, String url, ToolbarAttr attr) {
        Intent intent = new Intent(context, TDefaultWebActivity.class);
        intent.putExtra(TX5WebActivity.PARAM_URL, url);
        intent.putExtra(TDefaultWebActivity.PARAM_TOOBARDATA, attr);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onInitData(Bundle savedInstanceState) {
        super.onInitData(savedInstanceState);
        final String url = getIntent().getStringExtra(PARAM_URL);
        if (!TextUtils.isEmpty(url)) {
            x5WebView.loadUrl(url);
        }
    }

    @Override
    protected void onInitViews(View parent, X5WebView x5WebView) {
    }
}
