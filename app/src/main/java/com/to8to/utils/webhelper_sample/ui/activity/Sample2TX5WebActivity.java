package com.to8to.utils.webhelper_sample.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.ui.activity.TX5WebActivity;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;

/**
 * Created by same.li on 2018/1/24.
 */

public class Sample2TX5WebActivity  extends TX5WebActivity{



    @Override
    public void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitViews(View parent, X5WebView x5WebView) {
        updateLeftItemIcon(R.mipmap.ic_star_white);
        //刷新右侧中间图标
        updateMiddleItemIcon(R.mipmap.icon_share);

        x5WebView.loadUrl("file:///android_asset/actionbartest.html");

        setTitle("默认的Action");
    }



    @Override
    public void onLeftItemClike() {
        show("onLeftItemClike");
    }

    void show(String value)
    {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMidleItemClike() {
        show("onMidleItemClike");
    }


}
