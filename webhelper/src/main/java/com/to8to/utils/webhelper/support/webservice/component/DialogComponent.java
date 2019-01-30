package com.to8to.utils.webhelper.support.webservice.component;

import com.to8to.social.share.ShareInfo;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.support.bean.ShareData;
import com.to8to.utils.webhelper.support.bean.ShareDatas;
import com.to8to.utils.webhelper.support.ui.dialog.ShareDialog;
import com.to8to.utils.webhelper.support.ui.view.IShareView;
import com.to8to.utils.webhelper.utils.WLog;

/**
 * Created by same.li on 2018/2/9.
 */

public class DialogComponent extends TBaseComponent {



    /**
     * 打开分享对话,各自平台的信息都需要设置
     */
    @RequestMethod("openShareDialog")
    public void openShareDialog(IShareView iShareView,ShareDatas infos)
    {
        WLog.log(infos);
        ShareDialog shareDialog = new ShareDialog(getContext());
        iShareView.onShareDialogPrepare(shareDialog);
        ShareData qq = infos.getQq();
        shareDialog.setQQShareInfo(qq.getTitle(),qq.getContent(),qq.getImgUrl(),qq.getTargetUrl());
        ShareData qZone = infos.getqZone();
        shareDialog.setQZoneShareInfo(qZone.getTitle(),qZone.getContent(),qZone.getImgUrl(),qZone.getTargetUrl());
        ShareData wechat = infos.getWechat();
        shareDialog.setWeChatShareInfo(wechat.getTitle(),wechat.getContent(),wechat.getImgUrl(),wechat.getTargetUrl());
        ShareData wechatCircle = infos.getWechatCircle();
        shareDialog.setWeChatCircleShareInfo(wechatCircle.getTitle(),wechatCircle.getContent(),wechatCircle.getImgUrl(),wechatCircle.getTargetUrl());
        ShareData sina = infos.getSina();
        shareDialog.setSinaShareInfo(sina.getTitle(),sina.getContent(),sina.getImgUrl(),sina.getTargetUrl());
        shareDialog.show();
    }


    /**
     * 打开分享对话,分享的信息都统一
     */
    @RequestMethod("openUnifiedShareDialog")
    public void openUnifiedShareDialog(IShareView iShareView, ShareData data)
    {
        WLog.log(data);
        ShareDialog shareDialog = new ShareDialog(getContext());
        iShareView.onShareDialogPrepare(shareDialog);
        ShareInfo shareInfo = new ShareInfo(data.getTitle(),data.getContent(),data.getImgUrl(),data.getTargetUrl());
        shareDialog.setQQShareInfo(shareInfo);
        shareDialog.setQZoneShareInfo(shareInfo);
        shareDialog.setWeChatShareInfo(shareInfo);
        shareDialog.setWeChatShareInfo(shareInfo);
        shareDialog.setSinaShareInfo(shareInfo);
        shareDialog.show();
    }




}
