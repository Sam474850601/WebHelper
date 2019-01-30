package com.to8to.utils.webhelper.support.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.ui.widget.actionbar.TToolbar;


/**
 * Created by same.li on 2018/1/15.
 */

public abstract class TToolbarActivity<T> extends BaseToolbarActvity implements
        TToolbar.OnMoreItemClikeListener<T>,

        TToolbar.OnLeftItemClikeListener, TToolbar.OnMiddleItemClikeListener
{

    protected LinearLayout parentView;

    private Bundle savedInstanceState;
    private TToolbar toolbar;


    protected TToolbar getTToolbar()
    {
        return toolbar;
    }

    protected   abstract int  getLayoutResId();

    protected  abstract void onInitViews(View parent);

    protected abstract void onInitData(Bundle savedInstanceState);

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        parentView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_container, null);
        getLayoutInflater().inflate(getLayoutResId(), parentView);
        setContentView(parentView);
        toolbar = (TToolbar) parentView.findViewById(R.id.tb_webview);
        onInitViews(parentView);
        onInitData(savedInstanceState);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(toolbar);
        toolbar.setOnLeftItemClikeListener(this);
        toolbar.setOnMiddleItemClikeListener(this);
        toolbar.setOnMoreItemClikeListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationViewClike();
            }
        });
    }



    public void setMenuAdapter(BaseAdapter adapter)
    {
        toolbar.setAdapter(adapter);
    }

    public void setShowMoreItemsProxy(TToolbar.OnShowProxy showProxy)
    {
        toolbar.setOnShowProxy(showProxy);
    }


    @Override
    public void onNavigationViewClike() {
        finish();
    }



    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public int getMenuRes() {
        return R.menu.menu_default;
    }




    @Override
    public void onLeftItemClike() {

    }

    @Override
    public void onMidleItemClike() {

    }



    @Override
    public void onMoreItemClike(int position, T data) {

    }
}
