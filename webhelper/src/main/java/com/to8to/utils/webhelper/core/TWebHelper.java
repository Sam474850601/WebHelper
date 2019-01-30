package com.to8to.utils.webhelper.core;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.to8to.utils.webhelper.core.ann.Action;
import com.to8to.utils.webhelper.utils.ClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by same.li on 2018/1/11.
 * 核心组件辅助类
 */

public final  class TWebHelper {

    public static  final ExecutorService threadPool = Executors.newFixedThreadPool(3);


    /**
     * 添加Action
     * @param context
     * @param actionClass
     * @param webviewAdapter
     */
    public static void addAction(final Class<? extends TBaseAction> actionClass, final Context context,
                                 final IWebviewAdapter webviewAdapter)
    {
        TWebHelper.addActions(webviewAdapter,  context, actionClass);
    }


    /**
     * 添加Action
     * @param context
     * @param webviewAdapter
     */
    public static void addActions( IWebviewAdapter webviewAdapter,   Context context,   Class<? extends TBaseAction> ...actionClasses)
    {
        if(null == actionClasses || 0 ==actionClasses.length || null == webviewAdapter)
            return;
        if(!webviewAdapter.getJavaScriptEnabled())
            webviewAdapter.setJavaScriptEnabled(true);
        AddActionRunnable runnable = new AddActionRunnable();
        runnable.webviewAdapter = webviewAdapter;
        runnable.context = context;
        runnable.actionClasses = actionClasses;
        threadPool.execute(runnable);
    }

    private  static    class AddActionRunnable implements Runnable
    {
        IWebviewAdapter webviewAdapter;
        Context context;
        Class<? extends TBaseAction> [] actionClasses;
        @Override
        public void run() {
            Handler handler = new Handler(context.getMainLooper());
            final long preTime =  System.currentTimeMillis();
            for(Class<? extends TBaseAction> actionClass: actionClasses )
            {
                if(null == actionClass)
                    return;
                try {
                    final TBaseAction baseAction = (TBaseAction) ClassUtil.newObjectByContructorWithoutParams(actionClass);
                    Action action = actionClass.getAnnotation(Action.class);
                    final  String actionName = action.value();
                    handler.post(new Runnable() {
                        final IWebviewAdapter mAdapter = webviewAdapter;
                        @Override
                        public void run() {
                            mAdapter.addJavascriptInterface(baseAction, actionName);
                        }
                    });
                    baseAction.init(context,  webviewAdapter);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            context = null;
            webviewAdapter = null;
            Log.i("TWebHelper", "addAction consuming time:"+(System.currentTimeMillis() - preTime));
        }
    }



    public static  void invokeJs(IWebviewAdapter proxy, String method, Object... params)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if(null != params)
        {
            for(Object obj : params)
            {
                if(null != obj)
                {
                    String callbackResult = "'";
                    if (!TBaseAction.isJsonBean(obj)) {
                        callbackResult += String.valueOf(obj);
                    } else {
                        Gson gson = new Gson();
                        try {
                            callbackResult += gson.toJson(obj);
                        } catch (Exception ex) {
                            callbackResult += obj.toString();
                        }
                    }
                    callbackResult += "'";
                    stringBuilder.append(callbackResult).append(",");
                }

            }
            final int len = stringBuilder.length();
            if(len>0)
            {
                stringBuilder.deleteCharAt(len-1);
            }
        }
         String paramStr = stringBuilder.toString();
        final String invokeJsString = "javascript:"+method+"("+paramStr+")";
        Log.e("invokeJsString", invokeJsString);
        proxy.loadUrl(invokeJsString);
    }





}
