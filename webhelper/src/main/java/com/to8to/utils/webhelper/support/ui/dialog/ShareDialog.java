package com.to8to.utils.webhelper.support.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.to8to.social.share.ShareInfo;
import com.to8to.utils.webhelper.R;
import com.to8to.utils.webhelper.support.model.IShareModel;
import com.to8to.utils.webhelper.support.model.ShareModel;
import com.to8to.utils.webhelper.utils.NetUtil;

/**
 * Created by same.li on 2018/2/8.
 */

public class ShareDialog extends CommonDialog {


    public ShareDialog(Context context)
    {
        super(R.layout.dialog_share, context);
    }

    final IShareModel shareModel = new ShareModel();


    private LinearLayout shareQQ,
            shareSina,
            shareWechat,
            shareWxcircle,
            shareLayout,
            shareQQZone;



    private ProgressDialog progressDialog;



    final MyShareCallback callback = new MyShareCallback();

    class MyShareCallback implements IShareModel.ShareCallBack {

        @Override
        public void onStart() {
            if(!progressDialog.isShowing())
                 progressDialog.show();
        }

        @Override
        public void onSuccess() {
            if(progressDialog.isShowing())
                 progressDialog.dismiss();
        }

        @Override
        public void onError(int var1, String var2) {
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }

        @Override
        public void onCancel() {
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }



    public void setQQShareInfo(ShareInfo info)
    {
        shareModel.setQQShareInfo(info.getTitle(), info.getContent(), info.getImgUrl(), info.getTargetUrl());
    }


    public void setQQShareInfo(String title,
                               String content,
                               String imgUrl,
                               String targetUrl)
    {
        shareModel.setQQShareInfo(title, content,imgUrl, targetUrl );
    }

    public void setQZoneShareInfo(ShareInfo info)
    {
        shareModel.setQZoneShareInfo(info.getTitle(), info.getContent(), info.getImgUrl(), info.getTargetUrl());
    }


    public void setQZoneShareInfo(String title,
                                  String content,
                                  String imgUrl,
                                  String targetUrl)
    {
        shareModel.setQZoneShareInfo(title, content,imgUrl, targetUrl );
    }


    public void setSinaShareInfo(ShareInfo info)
    {
        shareModel.setSinaShareInfo(info.getTitle(), info.getContent(), info.getImgUrl(), info.getTargetUrl() );
    }

    public void setSinaShareInfo(String title,
                                 String content,
                                 String imgUrl,
                                 String targetUrl)
    {
        shareModel.setSinaShareInfo(title, content,imgUrl, targetUrl );
    }


    public void setWeChatShareInfo(ShareInfo info)
    {
        shareModel.setWeChatShareInfo(info.getTitle(), info.getContent(), info.getImgUrl(), info.getTargetUrl() );
    }

    public void setWeChatShareInfo(String title,
                                   String content,
                                   String imgUrl,
                                   String targetUrl)
    {
        shareModel.setWeChatShareInfo(title, content,imgUrl, targetUrl );
    }


    public void setWeChatCircleShareInfo(ShareInfo info)
    {
        shareModel.setWeChatCircleShareInfo(info.getTitle(), info.getContent(), info.getImgUrl(), info.getTargetUrl() );
    }

    public void setWeChatCircleShareInfo(String title,
                                         String content,
                                         String imgUrl,
                                         String targetUrl)
    {
        shareModel.setWeChatCircleShareInfo(title, content,imgUrl, targetUrl );
    }



    @Override
    public void onInit(Window window, View view) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("请稍后...");
        shareLayout =  findViewById(R.id.share_layout);
        shareQQ = findViewById(R.id.share_qq);
        shareSina = findViewById(R.id.share_sina);
        shareWechat = findViewById(R.id.share_wechat);
        shareWxcircle = findViewById(R.id.share_wxcircle);
        shareQQZone =findViewById(R.id.share_qqzone);

        shareQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNetwork()) {
                    return;
                }
                dismiss();
                shareModel.shareQQ(getContext(), callback);

            }
        });
        shareQQZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNetwork()) {
                    return;
                }
                dismiss();
                shareModel.shareQzone(getContext(),callback);

            }
        });
        shareSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNetwork()) {
                    return;
                }
                dismiss();
                shareModel.shareSina(getContext(), callback);

            }
        });

        shareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNetwork()) {
                    return;
                }
                dismiss();
                shareModel.shareWechat(getContext(),callback);

            }
        });
        shareWxcircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkNetwork()) {
                    return;
                }

                dismiss();
                shareModel.shareWechatCircle(getContext(), callback);
            }
        });
        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    private boolean checkNetwork()
    {
        if (NetUtil.checkNetwork(getContext()) == NetUtil.Status.NO_NETWORK) {
            showMessage("请检查你的网络");
            return false;
        }
        return true;
    }

    private Toast mToast;

    private void showMessage(String message)
    {
        if(null == mToast)
        {
            mToast = Toast.makeText(getParentView().getContext(), "", Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        mToast.setText(message);
        mToast.show();
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        shareModel.authorizeCallBack(requestCode, resultCode, data);
    }
}
