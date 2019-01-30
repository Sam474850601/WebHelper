package com.to8to.utils.webhelper.support.ui.widget.actionbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;

import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.ui.window.BasePopupWindowBuilder;

/**
 * Created by same.li on 2018/1/17.
 */

public class TToolbar extends Toolbar implements Toolbar.OnMenuItemClickListener{

    private OnLeftItemClikeListener onLeftItemClikeListener;

    private OnMiddleItemClikeListener onMiddleItemClikeListener;


    private int windowResId;

    private BaseAdapter adapter;
    private MoreItemWindow moreItemWindow;
    private PopupWindow popupWindow;
    private int windowWith = 0;
    private int windowHeight = 0;
    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public TToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOnShowProxy(new DefaultShowProxy());
        setWindowLayout(R.layout.window_moreitem, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setOnMenuItemClickListener(this);
        moreItemWindow  =new MoreItemWindow(getContext());
    }


    public TToolbar(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init(context, attrs);
    }


    public void initWindow()
    {
        moreItemWindow.setWindowResId(windowResId);
        moreItemWindow.setWidth(windowWith);
        moreItemWindow.setHeight(windowHeight);
        popupWindow = moreItemWindow.build();
        moreItemWindow.listView.setAdapter(adapter);

    }


    public void setWindowLayout(int resId, int with, int height )
    {
        this.windowResId = resId;
        this.windowWith = with;
        this.windowHeight = height;
    }


    public void setOnMoreItemClikeListener(OnMoreItemClikeListener onMoreItemClikeListener) {
        this.moreItemWindow.onMoreItemClikeListener = onMoreItemClikeListener;
    }

    public void showWindowAsDropDown(View view)
    {
        getWindow().showAsDropDown(view);
    }

    public void showWindowAsDropDown(View view, int x, int y)
    {
        getWindow().showAsDropDown(view, x, y);

    }



    public void showWindowAtLocation(View view, int graxy, int x, int y)
    {
        getWindow().showAtLocation(view,graxy,  x, y);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showWindow()
    {
        getWindow().showAsDropDown(this, 0, 0, Gravity.RIGHT);
    }

    public void closeWindow()
    {
        getWindow().dismiss();
    }



    public PopupWindow getWindow()
    {
        if(null == popupWindow)
        {
            throw  new RuntimeException("you must  commit TActionBar , before you get window");
        }
        return popupWindow;
    }

    private OnShowProxy onShowProxy;

    public void setOnShowProxy(OnShowProxy onShowProxy) {
        this.onShowProxy = onShowProxy;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
       final  int id = item.getItemId();
        if (id == R.id.action_left && null != onLeftItemClikeListener) {
            onLeftItemClikeListener.onLeftItemClike();
            return true;
        }
        else if(id == R.id.action_middle && null != onMiddleItemClikeListener)
        {
            onMiddleItemClikeListener.onMidleItemClike();
            return true;
        }
        else if(id == R.id.action_more )
        {
           onShowProxy.onShow(this);
            return false;
        }
        return false;
    }

    public interface OnShowProxy
    {
        void onShow(TToolbar window);
    }


    final  class DefaultShowProxy implements OnShowProxy
    {
        @Override
        public void onShow(TToolbar window) {
           window.showWindow();
        }
    }

    static  class MoreItemWindow extends BasePopupWindowBuilder
    {
        AbsListView listView;
        private int windowResId;
        private OnMoreItemClikeListener onMoreItemClikeListener;

        public MoreItemWindow(Context context) {
            super(context);
        }

        public void setWindowResId(int windowResId) {
            this.windowResId = windowResId;
        }

        @Override
        protected void init(View windowView, final PopupWindow popupWindow) {
            listView = (AbsListView) windowView.findViewById(R.id.lv_moreitems);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(null != onMoreItemClikeListener)
                    {
                        onMoreItemClikeListener.onMoreItemClike(i, adapterView.getItemAtPosition(i));
                        popupWindow.dismiss();
                    }
                }
            });
        }

        @Override
        protected int getLayoutResource() {
            return windowResId;
        }

    }






    public void setOnLeftItemClikeListener(OnLeftItemClikeListener onLeftItemClikeListener) {
        this.onLeftItemClikeListener = onLeftItemClikeListener;
    }

    public void setOnMiddleItemClikeListener(OnMiddleItemClikeListener onMiddleItemClikeListener) {
        this.onMiddleItemClikeListener = onMiddleItemClikeListener;
    }

    public interface  OnLeftItemClikeListener {
        void onLeftItemClike();
    }


    public interface  OnMiddleItemClikeListener {
        void onMidleItemClike();
    }

    public interface  OnMoreItemClikeListener<T>
    {
        void onMoreItemClike(int position, T data);
    }


}
