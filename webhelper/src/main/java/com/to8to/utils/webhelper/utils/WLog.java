package com.to8to.utils.webhelper.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by same.li on 2018/2/7.
 */

public final  class WLog {

    public static final String TAG = "t8twlog";
    public  static boolean isDebug = false;
    private static boolean hasInited = false;


    public static void init(Context context)
    {
        if(!hasInited)
        {
            isDebug = isDebugMode(context);
            hasInited = true;
        }
    }



    /**
     * 打印日志
     */
    public  static void log(Object... logData)
    {
        if(isDebug)
        {
            StringBuilder stringBuilder  = new StringBuilder();
            final int len = logData.length;
            for(int i  = 0; i < len; i ++)
            {
                Object data = logData[i];
                stringBuilder.append("["+i+":")
                        .append(data+"],");
            }
            final int currentLen = stringBuilder.length();
            if(currentLen>1)
            {
                stringBuilder.deleteCharAt(currentLen-1);
            }
            Log.e(TAG, getTag()+"'\n\t>>>"+stringBuilder.toString());
        }


    }


    private static final int index = 4;

    private static String getTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = stackTrace[index].getFileName();
        String className = stackTrace[index].getClassName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        return "at "+className+"."+methodName+"("+fileName+":"+lineNumber+"): ";
    }

    /**
     * app是否是调试模式
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
    public static boolean isDebugMode(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




}
