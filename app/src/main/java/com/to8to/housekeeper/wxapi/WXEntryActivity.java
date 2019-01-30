package com.to8to.housekeeper.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.to8to.social.TSConstans;




public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, TSConstans.WX_APPID, false);
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }


    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        /**
         * 请求token
         */
        if (resp.errCode == 0) {
           if (resp instanceof SendMessageToWX.Resp) {

                showMessage("分享成功");
                this.finish();
            } else {
                showMessage("未知请求的响应"+ resp.getClass().getName());

                this.finish();
            }
        }
        /**
         * 拒绝或取消
         */
        else {
            this.finish();
        }
    }



    private Toast mToast;

    private void showMessage(String message)
    {
        if(null == mToast)
        {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        mToast.setText(message);
        mToast.show();
    }


}