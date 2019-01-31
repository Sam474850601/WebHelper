package com.to8to.utils.webhelper.support.webservice.action;


import android.util.Log;

import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.Action;
import com.to8to.utils.webhelper.core.ann.Component;
import com.to8to.utils.webhelper.support.webservice.component.ToolbarComponent;


/**
 * Created by same.li on 2018/1/18.
 * 导航栏action
 */

@Action("webhelperAction")
public class WebhelperAction extends TBaseAction {

    @Override
    protected void unknownRequest(String data) {
        Log.e("WebhelperAction", "unknow info:"+data);
    }

    @Component("toolbarSettingApi")
    public TBaseComponent getToolbarSettingMoudle()
    {
        return new ToolbarComponent();
    }



}
