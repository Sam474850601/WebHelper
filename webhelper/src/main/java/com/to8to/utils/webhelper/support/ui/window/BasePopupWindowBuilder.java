package com.to8to.utils.webhelper.support.ui.window;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;

/**
 * Created by Same.li on 2017/1/8
 * 窗口构造器
 */
public abstract class BasePopupWindowBuilder {

    private     PopupWindow popupWindow;
    private Context context;
    private View windowView;
    private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int height= ViewGroup.LayoutParams.WRAP_CONTENT;

    protected abstract void init(View windowView, PopupWindow popupWindow);


    protected  abstract  int getLayoutResource();



    public void setWidth(int width) {
        this.width = width;
    }



    public void setHeight(int height) {
        this.height = height;
    }

    public BasePopupWindowBuilder(Context context)
    {
        this.context = context;
    }


    public Context getWindowContext()
    {
        return context;
    }

    public PopupWindow build()
    {
        final int res = getLayoutResource();
        windowView = LayoutInflater.from(getWindowContext() ).inflate(res, null);

        popupWindow = new PopupWindow(windowView, width,
                height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        init(windowView, popupWindow);
        return  popupWindow;
    }


}
