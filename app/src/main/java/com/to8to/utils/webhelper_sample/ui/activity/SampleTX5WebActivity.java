package com.to8to.utils.webhelper_sample.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebView;
import com.to8to.housekeeper.R;
import com.to8to.utils.webhelper.support.ui.activity.TX5WebActivity;
import com.to8to.utils.webhelper.support.ui.adapter.DfActionbarListAdapter;
import com.to8to.utils.webhelper.support.ui.widget.X5WebView;
import com.to8to.utils.webhelper.support.ui.widget.actionbar.TToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by same.li on 2018/1/24.
 */

public class SampleTX5WebActivity extends TX5WebActivity<DfActionbarListAdapter.Item> {


    @Override
    public void onInitData(Bundle savedInstanceState) {

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

    @Override
    public void onMoreItemClike(int position, DfActionbarListAdapter.Item data) {
        show("onMoreItemClike:"+position+"，");
    }



    public DfActionbarListAdapter createShareAndFollowAdapter(Context context)
    {

        final DfActionbarListAdapter dfActionbarAdapter = new DfActionbarListAdapter(context);
        List<DfActionbarListAdapter.Item> items = new ArrayList<>();
        items.add(getTest1Item());
        items.add(getTest2Item());
        dfActionbarAdapter.setData(items);
        return dfActionbarAdapter;
    }

    public  DfActionbarListAdapter.Item getTest1Item()
    {
        DfActionbarListAdapter.Item item =new DfActionbarListAdapter.Item();
        item.title = "test1";
        item.icon = R.mipmap.ic_launcher;
        return item;
    }


    public  DfActionbarListAdapter.Item getTest2Item()
    {
        DfActionbarListAdapter.Item item =new DfActionbarListAdapter.Item();
        item.title = "test2";
        item.icon = R.mipmap.ic_launcher;
        return item;
    }

    @Override
    protected void onInitViews(View parent, X5WebView x5WebView) {
        updateLeftItemIcon(R.mipmap.ic_star_white);
        //刷新右侧中间图标
        updateMiddleItemIcon(R.mipmap.icon_share);
        //刷新右侧最后一个图标
        updateMoreItemIcon(R.mipmap.ic_more);
        TToolbar tToolbar =  getTToolbar();

        ///设置window 适配器,必须要在initWindow之前设置。
        tToolbar.setAdapter(createShareAndFollowAdapter(this));
        //初始化Window
        tToolbar.initWindow();
        setLogo(R.mipmap.ic_launcher);
        x5WebView.loadUrl("http://www.qq.com");

        setTitle("腾讯x5 WebActivity");
    }


}
