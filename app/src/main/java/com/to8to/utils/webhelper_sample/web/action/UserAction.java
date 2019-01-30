package com.to8to.utils.webhelper_sample.web.action;


import android.content.Context;
import android.webkit.JavascriptInterface;

import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.Action;
import com.to8to.utils.webhelper.core.ann.Component;
import com.to8to.utils.webhelper_sample.web.component.UserConsumeComponent;
import com.to8to.utils.webhelper_sample.web.component.UserInfoComponent;


/**
 * Created by same.li on 2018/1/11.
 */

@Action("userAction")
public class UserAction  extends TBaseAction {


    @JavascriptInterface
    public void method()
    {

    }


    @Component("userInfo")
    public TBaseComponent getUserInfoComponent()
    {
        return new UserInfoComponent();
    }


    @Component("userConsumeApi")
    public TBaseComponent getConsumeComponent()
    {
        return new UserConsumeComponent();
    }




}
