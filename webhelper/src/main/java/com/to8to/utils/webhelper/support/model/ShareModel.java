package com.to8to.utils.webhelper.support.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.to8to.social.Login.LoginService;
import com.to8to.social.TSConstans;
import com.to8to.social.share.QQShare;
import com.to8to.social.share.QQZoneShare;
import com.to8to.social.share.ShareInfo;
import com.to8to.social.share.SinaShare;
import com.to8to.social.share.WXCircleShare;
import com.to8to.social.share.WXShare;
import com.to8to.utils.webhelper.utils.WLog;

/**
 * Created by same.li on 2018/2/9.
 */

public class ShareModel  implements IShareModel{
    public final static int TYPE_QQ = 1;
    public final static int TYPE_QZONE = 2;
    public final static int TYPE_WECHACT = 3;
    public final static int TYPE__WECHACT_CIRCLE = 4;
    public final static int TYPE_SINA= 5;
    private ShareInfo mQQShareInfo;
    private ShareInfo mQZoneShareInfo;
    private ShareInfo mSinaShareInfo;
    private ShareInfo mWeChatShareInfo;
    private ShareInfo mWeChatCircleShareInfo;
    private  SinaShare    sinaShare;
    private  volatile int  shareType;



    public static  class MyShareCallback implements com.to8to.social.share.ShareCallBack
    {
        private ShareCallBack shareCallBack;
        public MyShareCallback(ShareCallBack shareCallBack)
        {
            this.shareCallBack = shareCallBack;
        }

        @Override
        public void onStart() {
            shareCallBack.onStart();
        }

        @Override
        public void onSuccess() {
            shareCallBack.onSuccess();
        }

        @Override
        public void onError(int var1, String var2) {
            shareCallBack.onError(var1, var2);
        }

        @Override
        public void onCancel() {
            shareCallBack.onCancel();
        }
    }





    @Override
    public void shareQzone(Context context, ShareCallBack callBack) {

        shareType = TYPE_QZONE;
        MyShareCallback myShareCallback = null;
        if(null != callBack)
            myShareCallback = new MyShareCallback(callBack);
        QQZoneShare qqZoneShare =    new QQZoneShare((Activity) context);
        qqZoneShare.share(mQZoneShareInfo.getTitle(), mQZoneShareInfo.getContent(),
                mQZoneShareInfo.getImgUrl(), mQZoneShareInfo.getTargetUrl(),myShareCallback );

    }

    @Override
    public void shareQQ(Context context, ShareCallBack callBack) {
        shareType = TYPE_QQ;
        MyShareCallback myShareCallback = null;
        if(null != callBack)
            myShareCallback = new MyShareCallback(callBack);
       QQShare qqShare =  new QQShare((Activity) context);
        qqShare.share(mQQShareInfo.getTitle(), mQQShareInfo.getContent(),
                mQQShareInfo.getImgUrl(), mQQShareInfo.getTargetUrl(),myShareCallback );
    }

    @Override
    public void shareSina(Context context, ShareCallBack callBack) {
        shareType = TYPE_SINA;
        MyShareCallback myShareCallback = null;
        if(null != callBack)
            myShareCallback = new MyShareCallback(callBack);
        sinaShare  = new SinaShare((Activity) context);
        sinaShare.share(mSinaShareInfo.getTitle(), mSinaShareInfo.getContent(),
                mSinaShareInfo.getImgUrl(), mSinaShareInfo.getTargetUrl(),myShareCallback);
    }

    @Override
    public void shareWechat(Context context, ShareCallBack callBack) {
        shareType = TYPE_WECHACT;
        MyShareCallback myShareCallback = null;
        if(null != callBack)
            myShareCallback = new MyShareCallback(callBack);
        WXShare wxShare  =   new WXShare(context, TSConstans.WX_APPID);
        wxShare.share(mWeChatShareInfo.getTitle(), mWeChatShareInfo.getContent(),
                mWeChatShareInfo.getImgUrl(), mWeChatShareInfo.getTargetUrl(),myShareCallback );

    }

    @Override
    public void shareWechatCircle(Context context, ShareCallBack callBack) {
        shareType = TYPE__WECHACT_CIRCLE;
        MyShareCallback myShareCallback = null;
        if(null != callBack)
            myShareCallback = new MyShareCallback(callBack);
        WXCircleShare wxCircleShare =  new WXCircleShare(context, TSConstans.WX_APPID);
        wxCircleShare.share(
                mWeChatCircleShareInfo.getTitle(),
                mWeChatCircleShareInfo.getContent(),
                mWeChatCircleShareInfo.getImgUrl(),
                mWeChatCircleShareInfo.getTargetUrl(),
                myShareCallback );
    }

    @Override
    public void setQQShareInfo(String title,
                               String content,
                               String imgUrl,
                               String targetUrl)
    {
        mQQShareInfo = new ShareInfo();
        mQQShareInfo.setTitle(title);
        mQQShareInfo.setContent(content);
        mQQShareInfo.setImgUrl(imgUrl);
        mQQShareInfo.setTargetUrl(targetUrl);
    }



    @Override
    public void setQZoneShareInfo(String title,
                                  String content,
                                  String imgUrl,
                                  String targetUrl)
    {
        mQZoneShareInfo = new ShareInfo();
        mQZoneShareInfo.setTitle(title);
        mQZoneShareInfo.setContent(content);
        mQZoneShareInfo.setImgUrl(imgUrl);
        mQZoneShareInfo.setTargetUrl(targetUrl);
    }




    @Override
    public void setSinaShareInfo(String title,
                                 String content,
                                 String imgUrl,
                                 String targetUrl)
    {
        mSinaShareInfo = new ShareInfo();
        mSinaShareInfo.setTitle(title);
        mSinaShareInfo.setContent(content);
        mSinaShareInfo.setImgUrl(imgUrl);
        mSinaShareInfo.setTargetUrl(targetUrl);
    }




    @Override
    public void setWeChatShareInfo(String title,
                                   String content,
                                   String imgUrl,
                                   String targetUrl)
    {
        mWeChatShareInfo = new ShareInfo();
        mWeChatShareInfo.setTitle(title);
        mWeChatShareInfo.setContent(content);
        mWeChatShareInfo.setImgUrl(imgUrl);
        mWeChatShareInfo.setTargetUrl(targetUrl);
    }



    @Override
    public void setWeChatCircleShareInfo(String title,
                                         String content,
                                         String imgUrl,
                                         String targetUrl)
    {
        mWeChatCircleShareInfo = new ShareInfo();
        mWeChatCircleShareInfo.setTitle(title);
        mWeChatCircleShareInfo.setContent(content);
        mWeChatCircleShareInfo.setImgUrl(imgUrl);
        mWeChatCircleShareInfo.setTargetUrl(targetUrl);
    }

    @Override
    public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
        WLog.log("shareType="+shareType);
        if(TYPE_SINA  == shareType)
        {
            LoginService loginInterface = sinaShare.getMloginService();
            if (loginInterface != null ) {
                loginInterface.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


}
