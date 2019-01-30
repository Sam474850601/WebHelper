package com.to8to.utils.webhelper.core;

import android.content.Context;
import android.os.Handler;

import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.core.bean.Request;
import com.to8to.utils.webhelper.utils.JsonUtil;

import java.lang.ref.WeakReference;

/**
 * Created by same.li on 2018/1/10.
 */

public abstract  class TBaseComponent
{

    protected IWebviewAdapter adapter;

    private Handler handler = null;

    protected void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    protected     void onCreate(Context context)
    {

    }

    WeakReference<Context> contextWeakReference;

    public void setWebviewAdapter(IWebviewAdapter IWebviewAdapter) {
        this.adapter = IWebviewAdapter;
    }


    /**
     * 获得Context，系统回收后为空.
     * @return
     */
    public Context  getContext()
    {
        if(null != contextWeakReference)
        {
            return contextWeakReference.get();
        }
        return null;
    }

    public void setContext(Context  context) {
        contextWeakReference = new WeakReference<Context>(context);
    }

    public void init(Context context, IWebviewAdapter jsInterfaceProxy)
    {
        setContext(context);
        setWebviewAdapter(jsInterfaceProxy);
        onCreate(context);
    }

    public void runOnUiThread(Runnable runnable)
    {
        if(null == handler)
            return;
        handler.post(runnable);
    }


    public void invokeJs(String jsMethod, Object ...parems)
    {
        TWebHelper.invokeJs(adapter, jsMethod, parems);
    }


    public void invokeJs(Request request, Object ...parems)
    {

        TWebHelper.invokeJs(adapter, request.getCallback(), parems);
    }







}
