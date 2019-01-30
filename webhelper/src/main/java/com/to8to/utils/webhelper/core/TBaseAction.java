package com.to8to.utils.webhelper.core;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.core.ann.Component;
import com.to8to.utils.webhelper.core.bean.Request;
import com.to8to.utils.webhelper.support.bean.ShareData;
import com.to8to.utils.webhelper.support.ui.view.IView;
import com.to8to.utils.webhelper.utils.ClassUtil;
import com.to8to.utils.webhelper.utils.JsonUtil;
import com.to8to.utils.webhelper.utils.WLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by same.li on 2018/1/10.
 */
public abstract class TBaseAction {

    protected IWebviewAdapter adapter;
    private Handler handler = null;

    private final Map<String, List<CacheMethod>> cacheMethods = new ConcurrentHashMap();

    private RequestFilter requestFilter;


    WeakReference<Context> contextWeakReference;

    //创建时候回调
    protected void onCreate(Context context) {

    }

    //如果没找到对应moudle 和 method， 那么这个方法会回调
    protected void unknownRequest(String data) {

    }


    protected RequestFilter getFileter() {
        if (null == requestFilter) {
            requestFilter = DefaultRequestFilter.newInstance();
        }
        return requestFilter;
    }


    public void setAdapter(IWebviewAdapter adapter) {
        this.adapter = adapter;
    }

    public Context getContext() {
        if (null != contextWeakReference) {
            return contextWeakReference.get();
        }
        return null;
    }

    private void setContext(Context context) {
        contextWeakReference = new WeakReference<Context>(context);
    }


    public void init(Context context, IWebviewAdapter webviewAdapter) {
        WLog.init(context);
        handler = new Handler(context.getMainLooper());
        setContext(context);
        setAdapter(webviewAdapter);
        Method[] methods = getClass().getDeclaredMethods();
        if (null == methods)
            return;
        final int len = methods.length;
        if (0 == len)
            return;
        for (int i = 0; i < len; i++) {
            final Method method = methods[i];
            if (method.isAnnotationPresent(Component.class)) {
                Component component = method.getAnnotation(Component.class);
                String module = component.value();
                if (TextUtils.isEmpty(module)) {
                    module = method.getName();
                }
                final List<CacheMethod> actionMethods = new ArrayList<>();
                try {
                    final TBaseComponent componentObject = (TBaseComponent) method.invoke(this);

                    Method[] componentObjectMethods = componentObject.getClass().getDeclaredMethods();
                    if (null == componentObjectMethods)
                        continue;
                    final int componentObjectLen = componentObjectMethods.length;
                    for (int m = 0; m < componentObjectLen; m++) {
                        final Method componentObjectMethod = componentObjectMethods[m];
                        if (componentObjectMethod.isAnnotationPresent(RequestMethod.class)) {
                            final RequestMethod requestMethod = componentObjectMethod.getAnnotation
                                    (RequestMethod.class);
                            String methodName = requestMethod.value();
                            CacheMethod cacheMethod = new CacheMethod();
                            if (TextUtils.isEmpty(methodName)) {
                                methodName = componentObjectMethod.getName();
                            }
                            cacheMethod.method = componentObjectMethod;
                            cacheMethod.methodName = methodName;
                            cacheMethod.component = componentObject;
                            actionMethods.add(cacheMethod);
                        }
                    }
                    this.addModuleCache(module, actionMethods);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            componentObject.setHandler(handler);
                            componentObject.init(getContext(), adapter);

                        }
                    });

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                onCreate(getContext());
            }
        });

    }


    //固定js需要调用这个方法
    @JavascriptInterface
    public final  void invoke(String data) {
        WLog.log(data);
        final long preTime =  System.currentTimeMillis();
        synchronized (this) {
            final RequestFilter requestFilter = getFileter();
            final Request request = requestFilter.onFilter(data);
            final String componentName = request.getComponent();
            final String methodName = request.getMethod();
            if (TextUtils.isEmpty(componentName) || TextUtils.isEmpty(methodName)) {
                unknownRequest(data);
                return;
            }

            final List<CacheMethod> methods = cacheMethods.get(componentName);
            if (null == methods) {
                unknownRequest(data);
                return;
            }
            final int cacheMethodSize = methods.size();
            if (0 == cacheMethodSize) {
                unknownRequest(data);
                return;
            }

            for (int i = 0; i < cacheMethodSize; i++) {
                final CacheMethod cacheMethod = methods.get(i);
                final Object obj = request.getData();
                final Method objmethod = cacheMethod.method;
                final Object component = cacheMethod.component;
                if (cacheMethod.methodName.equals(methodName)) {

                    handler.post(new Runnable() {
                        final Request mRequest =  request;
                        @Override
                        public void run() {
                            try {
                                if (null != objmethod) {
                                    objmethod.setAccessible(true);
                                    //获取参数
                                    final Class[] methodClasses = objmethod.getParameterTypes();
                                    Object returnData = null;
                                    if (null != methodClasses && methodClasses.length > 0) {
                                        //如果多参数
                                        returnData =  objmethodInvoke(mRequest, methodClasses,objmethod, component, obj);

                                    } else {
                                        returnData =  objmethodInvoke(objmethod, component);
                                    }

                                    final String callbackMethod = request.getCallback();
                                    if (TextUtils.isEmpty(callbackMethod))
                                        return;
                                    TWebHelper.invokeJs(adapter, callbackMethod, returnData);
                                }
                            } catch (InvocationTargetException e) {
                                WLog.log( "error>InvocationTargetException",e);
                            } catch (IllegalAccessException e) {
                                WLog.log( "error>IllegalAccessException",e);
                            }
                            catch (Exception e) {
                                WLog.log( "error>Exception",e);
                            }
                        }
                    });

                }
            }
        }
        Log.i("BaseAction", "invoke consuming time:"+(System.currentTimeMillis() - preTime));
    }

    //无参
    private Object objmethodInvoke(Method objmethod,Object component) throws InvocationTargetException, IllegalAccessException {
        Object returnData = null;
        if (!ClassUtil.isReturnNullMethod(objmethod)) {
            returnData = objmethod.invoke(component);
        } else {
            objmethod.invoke(component);
        }
        return returnData;
    }


    //有参回调， 如果界面被摧毁，那么不会再执行
    private Object objmethodInvoke(Request request, Class[] methodClasses,Method objmethod, Object component, Object data) throws InvocationTargetException, IllegalAccessException {


        Context context = getContext();
        if(null == context)
            return null;
        Object returnData = null;
        final int len = methodClasses.length;
        Object[] paramData =new Object[len];
        for(int i = 0;i < len; i++ )
        {
            final Class paramClass = methodClasses[i];
            if(IView.class.isAssignableFrom(paramClass))
            {
               IView view = (IView) context;
               if(view.isFinishing())
                   return null;
                paramData[i] = view;
            }
            else  if(Request.class.isAssignableFrom(paramClass))
            {
                paramData[i] = request;
            }
            else
            {
                Object beanObject  =  JsonUtil.getBean(JsonUtil.toJson(data), paramClass);
                paramData[i] = beanObject;
            }
        }


        if (!ClassUtil.isReturnNullMethod(objmethod)) {
            returnData =   objmethod.invoke(component, paramData);

        } else {
            objmethod.invoke(component, paramData);
        }
        return returnData;
    }


    public  static boolean isJsonBean(Object obj) {
        final Class classType = obj.getClass();
        return isJsonClass(classType);
    }

    public static boolean isJsonClass(Class classType)
    {
        return !(String.class.isAssignableFrom(classType)
                || long.class.isAssignableFrom(classType)
                || float.class.isAssignableFrom(classType)
                || int.class.isAssignableFrom(classType)
                || double.class.isAssignableFrom(classType)
                || char.class.isAssignableFrom(classType)
                || byte.class.isAssignableFrom(classType)
                || Long.class.isAssignableFrom(classType)
                || Float.class.isAssignableFrom(classType)
                || Integer.class.isAssignableFrom(classType)
                || Double.class.isAssignableFrom(classType)
                || Character.class.isAssignableFrom(classType)
                || Byte.class.isAssignableFrom(classType)
        );
    }


    private void addModuleCache(String module, List<CacheMethod> methods) {
        cacheMethods.put(module, methods);
    }

    public void runOnUiThread(Runnable runnable)
    {
        if(null == handler)
            return;
        handler.post(runnable);
    }


}
