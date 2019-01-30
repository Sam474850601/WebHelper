package com.to8to.utils.webhelper.support.model;


import android.content.Context;
import android.content.Intent;


/**
 * Created by same.li on 2018/2/9.
 * 接口设计不依赖土巴兔分享，由子类实现
 */

public interface IShareModel {


    //分享过程回调
    interface ShareCallBack {

        //准备分享
        void onStart();

        //分享成功
        void onSuccess();

        //分享错误
        void onError(int var1, String var2);

        //分享被取消
        void onCancel();
    }

    //qq空间
     void shareQzone(Context context, ShareCallBack callBack);

    //qq
     void shareQQ(Context context, ShareCallBack callBack) ;
    //新浪
     void shareSina(Context context, ShareCallBack callBack);
    //分享给微信
     void shareWechat(Context context, ShareCallBack callBack) ;
     //分享到微信朋友圈
     void shareWechatCircle(Context context, ShareCallBack callBack);

     //设置QQ分享信息
     void setQQShareInfo(String title,
                               String content,
                               String imgUrl,
                               String targetUrl);


    //设置QQ空间分享信息
    void setQZoneShareInfo(String title,
                                  String content,
                                  String imgUrl,
                                  String targetUrl);



    //设置新浪分享信息
     void setSinaShareInfo(String title,
                                 String content,
                                 String imgUrl,
                                 String targetUrl);


    //设置微信分享信息
    void setWeChatShareInfo(String title,
                                   String content,
                                   String imgUrl,
                                   String targetUrl);



    //设置微信朋友圈分享信息
    void setWeChatCircleShareInfo(String title,
                                         String content,
                                         String imgUrl,
                                         String targetUrl);
    //分享回调
    void authorizeCallBack(int requestCode, int resultCode, Intent data);
}
