# Webhelper库的介绍

## 一、简介

Webhelper是一个为解决Webview的js与java源生api交互便捷的框架。针对以往业务层与视图层交互差， 层次分明不明显，以及业务层文件多，总的维护性差而实现。
整个设计思想参考根据MVP架构行为模式，并且通过依赖注入方式使用，简洁有力而灵活（对Model层暂不提供注解标识，考虑性能优化问题，开发者自行分层，按架构层次划分即可）。本库提供了适配器可以兼容各种Webview依赖注入，只需执一行代码便可绑定（如手机自带系统的Webview, 腾讯的x5内核Webview等）。本库内置腾讯X5依赖包 ，内含简单的腾讯x5内核WebView的Activity基类， 简单封装的ToobarActivity基类， 


## 二、相关示意图

### 1.架构示意图

![image](https://github.com/Sam474850601/WebHelper/blob/master/MPW.png)



> 附： Action是与Webview绑定的对象，是component的容器，其中component充当js与源生java api的Model交互以及改变Android View行为的桥梁


---


### 2.Web请求api时序图

![image](https://github.com/Sam474850601/WebHelper/blob/master/request_life.png)



### 3.核心组件类图



![image](https://github.com/Sam474850601/WebHelper/blob/master/class.png)


###  3.数据交互协议图

![image](https://github.com/Sam474850601/WebHelper/blob/master/1_0.png)


## 使用Webhelper集成的腾讯的x5 WevView
只需要配置2步。
#### 1.X5InitServce这个服务，在Application 启动即可.


####  2.另外，要兼容其他cpu需要配置 MultiDex

```xml
<service android:name="com.to8to.utils.webhelper.support.service.X5InitServce"/>
```

```java
public class MyApplication extends Application  {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化x5内核
        X5InitServce.init(getApplicationContext());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
```

gradle.properties 文件

```gradle
Android.useDeprecatedNdk=true
```

app build.gradle 文件


```
defaultConfig 
{
    //.....
    multiDexEnabled true
    //.....
}
    
dependencies {
 //.....
  compile 'com.android.support:multidex:1.0.2'
 //.....
}
```





## 四、Webhelper组件使用说明

### 1.简单实例演示

#### 需求

用户通过使用Webview的组件，访问java api用户Model接口，获取到用户信息显示到Webview

##### 附：js 调用java api 执行约束执行方式:

![image](D:/土巴兔/1_0.png)

#### 第一步，编写获取用户信息 java api

Js绑定对象:userAction


Js绑定对象 | userAction
---|---





请求参数  |  参数值  | 格式
---|---|---
component | userInfo | String
method | all | String
data |   需填（用户名）   |  String


响应js回调方法 | 参数值 | 格式
---|---|---
freshInfoFromJavaApi | {"age":0,"desc":"","nikeName":"","userName":""} |  String(Json)



首先提供js绑定对象为 UserAction, js调用的引用为userAction, 使用注解@Action("userAction")提供，执行方法为invoke. 不需要实现，
全部js请求由UserAction的父类TBaseAction分发.

```java


@Action("userAction")
public class UserAction  extends TBaseAction {

    /**
    *   注入用户信息Component缓存到容器
    */
    @Component("userInfo")
    public TBaseComponent getUserInfoComponent()
    {
        return new UserInfoComponent();
    }
}


/**
* 用户信息接口Model
*/
public interface IUserModel {

    //获取用户全部信息
    User getInfo(String userName);


}


/**
* 实现IUserModel，提供获取用户信息伪代码
*/
public class UserModel implements IUserModel {


    @Override
    public User getInfo(String userName) {
        User user = new User();
        if("Same.li".equals(userName))
        {
            user.setUserName(userName);
            user.setAge(16);
            user.setNikeName("Sam");
            user.setDesc("个性签名个性签名哈哈哈");
        }
        return  user;
    }

}




/**
*  用户信息api   
*/
public class UserInfoComponent extends TBaseComponent {

    
    /**
    * 获取用户全部属性，输出User
    */
    @RequestMethod("all")
    public User  getInfo(String username)
    {
         IUserModel userModel = new UserModel();
         return userModel.getInfo(username);
    }

}


public class Test1Activity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        webView = findViewById(R.id.webview);
        //注入依赖
        TWebHelper.addAction(UserAction.class, this, new NormolWebViewAdapter(webView));
        //加载界面
        webView.loadUrl("file:///android_asset/userInfo.html");
    }
}


```

activity_test1.xml

```xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <WebView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/webview"
        />

</LinearLayout>
```




##### 第二步,编写h5界面， 根据java 已知接口完成逻辑交互：userInfo.html



```html
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>test1</title>
    <script src="./jquery.min.js"></script>
    <script>

        function getInfo(userName)
        {

            var requestApi ={
                                'component':'userInfo',
                                'method':'all',
                                'callback':'freshInfoFromJavaApi',
                                'data': userName
                            };

            var jsonStr = JSON.stringify(requestApi);
            window.userAction.invoke(jsonStr);
        }
    
        function freshInfoFromJavaApi(data)
        {
              var user = JSON.parse(data)
             $("#age").html(user.age);
             $("#nick").html(user.nikeName);
             $("#desc").html(user.desc);
        }


       $(document).ready(function(){

         $("#btnGetInfo").click(function(){
            var userName = $("#userName").val();
            getInfo(userName);
         });

       });


    </script>


</head>

<body>

用户: <input type="text" id="userName" value="Same.li"/><br/>

<div>
    昵称:<span id="nick"> </span>
</div>
<div>
    年龄:<span id="age"> </span>
</div>
<div>
    个人描述:<span id="desc"> </span>
</div>

<button id="btnGetInfo">获取用户信息</button>
</body>

</html>    
    
    

```

界面预览

![image](D:/土巴兔/1_1.png)


点击“获取用户信息”按钮

![image](D:/土巴兔/1_2.png)



## 2.注解使用介绍

###  @Action 
Action注解的对象代表与js 绑定的对象，是Conmponent的容器， 是一对多的映射关系.注意，@Action的名字不能重复，否者部分会失效，注解的对象需要继承TBaseAction,并且js需要每次请求，需要调用invoke方法，由TBaseAction分发执行api.

```java

@Action("userAction")
public class UserAction  extends TBaseAction {

    //...
}



```

###  @Conmponent
在Action中注入Conmponent的对象代表是js与model，java源生view交互的桥梁，
每个Conmponent可以被不同的Action注入使用。同一个Action,不能使用多个相同的
Conmponent.每个Conmponent需要继承TBaseComponent.而且只能在TBaseAction配置注入， 在Action配置注解Component时，可以写值（如@Component("xxx")）也可以不写（@Component）,
不写的情况是在于项目不混淆的情况下可以使用。否则失效。



```java 

public class UserInfoComponent extends TBaseComponent {
    //...
}

public class UserConsumeComponent extends TBaseComponent {

    //...
}


@Action("userAction")
public class UserAction  extends TBaseAction {


    @Component("userInfo")
    public TBaseComponent getUserInfoComponent()
    {
        return new UserInfoComponent();
    }


    @Component("userConsume")
    public TBaseComponent getConsumeComponent()
    {
        return new UserConsumeComponent();
    }

}


```
###  @RequestMethod

 被@RequestMethod注解的方法，代表是Js请求执行的方法.只能在TBaseComponent子类注解使用。在注解时，可以写值（如@RequestMethod("xxx")）也可以不写（@RequestMethod）,和Component一样不写的情况是在于项目不混淆的情况下可以使用。并且不允许重载，否则失效。有3种使用的方法:

#### （1）、无参数， 无返回值。（不会执行js回调）
```Java



    @RequestMethod("method1")
    public void method1()
    {


    }

```
#### （2）、有参数， 无返回值。（不会执行js回调）


```Java

    @RequestMethod("method2")
    public void method2(User user)
    {


    }  
    
}

```

> 附:User是期望JS传来的数据是
{
    'component':'',
    'method':'method2',
    'callback':'',
    'data': obj
};
中的 obj值,类型可以是json bean，也可以是string，也可以是int


#### （3）、有参数， 有返回值。（会执行js回调,执行callback中的名字的js方法）


```java

   @RequestMethod("method3")
    public User  method3(String  userName)
    {
        User user = new User();
        
        //...
        return user;
    }

```
#### （4）、js请求异步的方法，并回调


```java

    @RequestMethod
    public void method4(final Request request, LoginDataJsonBean bean)
    {
        IUserModel userModel = new UserModel();
        userModel.login(bean.getUserName(), bean.getPassword(), new ICallback() {


            @Override
            public void onResponse(String message) {
               //TBaseComponent自带方法
               invokeJs(request,  message);
            }
        });
    }

```




#### （5）、无参,访问视图层



```java

public class XXXActivity extends Activity implements ITestView {



    @Override
    public void toast(String value) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
    
    //....
}


public class XXXComponent extends TBaseComponent {

    @RequestMethod
    public void method5(ITestView iTestView)
    {
        iTestView.toast("hello word");
    }
}


```
#### （6）、有参,访问视图层,异步调用js


```java

    @RequestMethod
    public void method6(ITestView iTestView, final Request request, LoginDataJsonBean bean)
    {
        iTestView.toast("");

        IUserModel userModel = new UserModel();
        userModel.login(bean.getUserName(), bean.getPassword(), new ICallback() {
                

            @Override
            public void onResponse(String message) {
                //TBaseComponent内的方法
                invokeJs(request,  message);
            }
        });
    }


```

#### （7）、有参,访问视图层，并同步执行回调js

```java

    @RequestMethod
    public String  method7(ITestView iTestView, final final Request request, LoginDataJsonBean bean)
    {
        iTestView.toast("");
        
        
       return    "sadfasdf";  
    }


```


## 3. Aciton过滤器RequestFilter


请求过滤器。每次action请求处理前，都会执行这个方法。可以在这进行中转

```java

public interface RequestFilter {
     Request onFilter(String data);
}


//如， 可以把所有请求都转换到这个方法执行。前提参数格式相符合

public class OldRequestFilter implements RequestFilter {


    @Override
    public Request onFilter(String data) {
        Request request = new Request();
        request.setComponent("oldVersionModule");
        request.setMethod( "oldMethod");
        try {
            request.setData(new JSONObject(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request;
    }
}

```



## 4. WebView适配IWebviewAdapter
为了兼容所有的Webivew，不直接引用WebView，而使用IWebviewAdapter来代理.
(如TWebHelper中就是用到addAction(TBaseAction,TBaseAction,IWebviewAdapter)
还有invokeJs（IWebviewAdapter,method,params）).

其中代理的方法,如下


```java


public interface IWebviewAdapter
{
     void addJavascriptInterface(Object actionObj, String actionField);

     void loadUrl(String url);

     void setJavaScriptEnabled(boolean enabled);

     boolean getJavaScriptEnabled();

}


```

库里已实现了2种适配
#### 1.传统WebView适配
```java

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.to8to.utils.webhelper.core.IWebviewAdapter;

  public class NormolWebViewAdapter implements IWebviewAdapter {


    WebView webView;
    WebSettings webSettings;
    public NormolWebViewAdapter(android.webkit.WebView webView)
    {
        this.webView = webView;
        webSettings = webView.getSettings();
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavascriptInterface(Object actionObj, String actionField) {
        if(null == webView)
            return;
        webView.addJavascriptInterface(actionObj, actionField);
    }

    @Override
    public void loadUrl(String url) {
        if(null == webView)
            return;
        webView.loadUrl(url);
    }

    @Override
    public void setJavaScriptEnabled(boolean enabled) {
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean getJavaScriptEnabled() {
        return webSettings.getJavaScriptEnabled();
    }


}  

```

#### 2.x5适配


```java

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.to8to.utils.webhelper.core.IWebviewAdapter;


public class TbsWebViewAdapter  implements IWebviewAdapter {


    WebView webView;
    WebSettings webSettings;
    public TbsWebViewAdapter(com.tencent.smtt.sdk.WebView webView)
    {
        this.webView = webView;
        webSettings = webView.getSettings();
    }

    @Override
    public void addJavascriptInterface(Object actionObj, String actionField) {
        if(null == webView)
            return;
        webView.addJavascriptInterface(actionObj, actionField);
    }

    @Override
    public void loadUrl(String url) {
        if(null == webView)
            return;
        webView.loadUrl(url);
    }

    @Override
    public void setJavaScriptEnabled(boolean enabled) {
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean getJavaScriptEnabled() {
        return webSettings.getJavaScriptEnabled();
    }
}
 

```


### X5混淆

```
#-optimizationpasses 7
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers 
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**
#-overloadaggressively

#@proguard_debug_start
# ------------------ Keep LineNumbers and properties ---------------- #
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
-renamesourcefileattribute TbsSdkJava
-keepattributes SourceFile,LineNumberTable
#@proguard_debug_end

# --------------------------------------------------------------------------
# Addidional for x5.sdk classes for apps

-keep class com.tencent.smtt.export.external.**{
*;
}

-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
*;
}

-keep class com.tencent.smtt.sdk.CacheManager {
public *;
}

-keep class com.tencent.smtt.sdk.CookieManager {
public *;
}

-keep class com.tencent.smtt.sdk.WebHistoryItem {
public *;
}

-keep class com.tencent.smtt.sdk.WebViewDatabase {
public *;
}

-keep class com.tencent.smtt.sdk.WebBackForwardList {
public *;
}

-keep public class com.tencent.smtt.sdk.WebView {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
public static final <fields>;
public java.lang.String getExtra();
public int getType();
}

-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
public <fields>;
public <methods>;
}


-keepattributes InnerClasses

-keep public enum com.tencent.smtt.sdk.WebSettings$** {
*;
}

-keep public enum com.tencent.smtt.sdk.QbSdk$** {
*;
}

-keep public class com.tencent.smtt.sdk.WebSettings {
public *;
}


-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebViewClient {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
public <fields>;
public <methods>;
}

-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
public protected *;
}

# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
public protected *;
}

-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
public protected *;
}

-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebIconDatabase {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebStorage {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
public <fields>;
public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.Tbs* {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.utils.LogFileUtils {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLog {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLogClient {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.CookieSyncManager {
public <fields>;
public <methods>;
}

# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.utils.Apn {
public <fields>;
public <methods>;
}
-keep class com.tencent.smtt.** {
*;
}
# end


-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
public <fields>;
public <methods>;
}

-keep class MTT.ThirdAppInfoNew {
*;
}

-keep class com.tencent.mtt.MttTraceEvent {
*;
}

# Game related
-keep public class com.tencent.smtt.gamesdk.* {
public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBooter {
public <fields>;
public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
public protected *;
}

-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
public *;
}
#---------------------------------------------------------------------------


#------------------ 下方是android平台自带的排除项，这里不要动 ----------------

-keep public class * extends android.app.Activity{
public <fields>;
public <methods>;
}
-keep public class * extends android.app.Application
{
public <fields>;
public <methods>;
}
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepattributes *Annotation*

-keepclasseswithmembernames class *{
native <methods>;
}

-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}

#------------------ 下方是共性的排除项目 ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
... *JNI*(...);
}

-keepclasseswithmembernames class * {
... *JRI*(...);
}

-keep class **JNI* {*;}

 


```


### Webhelper混淆

```
    
-keep class com.to8to.utils.webhelper.** {*;}

-keepclassmembers class * {
 @com.to8to.utils.webhelper.core.ann.RequestMethod <methods>;
 @com.to8to.utils.webhelper.core.ann.Component <methods>;
}


```
