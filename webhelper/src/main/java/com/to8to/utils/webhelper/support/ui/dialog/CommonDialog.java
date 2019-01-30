package com.to8to.utils.webhelper.support.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by same.li on 2018/2/8.
 */

public abstract class CommonDialog  {

    private AlertDialog alertDialog ;
    View parentView;
    int width,  height, layoutRes;
    boolean isLoad = false;

    Context mContext;

    public Context getContext() {
        return mContext;
    }

    public CommonDialog(int layoutRes, Context context)
    {
       this(layoutRes, context, ViewGroup.LayoutParams.MATCH_PARENT ,
               ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public Dialog getDialog()
    {
        return alertDialog;
    }

    protected int getLayout()
    {
        return -1;
    }

    public CommonDialog(int layoutRes, Context context, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.layoutRes = layoutRes;
        this.mContext = context;
        alertDialog =  new AlertDialog.Builder(context).create();
    }


    public <T extends View> T findViewById(int viewId)
    {

        return (T) parentView.findViewById(viewId);
    }

    public  abstract void onInit(Window window, View view);



    public void show()
    {
        alertDialog.show();
        if(!isLoad)
        {
            isLoad = true;
            parentView =  LayoutInflater.from(mContext).inflate(layoutRes, null);
            alertDialog.setContentView(parentView);
            Window window  =  alertDialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0x00000000));
            onInit(window,parentView);
        }

    }

    public void dismiss()
    {
        alertDialog.dismiss();
    }


    public View getParentView() {
        return parentView;
    }
}
