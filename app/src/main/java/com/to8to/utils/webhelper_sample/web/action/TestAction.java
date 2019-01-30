package com.to8to.utils.webhelper_sample.web.action;

import android.content.Context;
import android.util.Log;

import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.Action;
import com.to8to.utils.webhelper.core.ann.Component;
import com.to8to.utils.webhelper_sample.web.component.Test1Component;
import com.to8to.utils.webhelper_sample.web.component.Test2Component;

/**
 * Created by same.li on 2018/1/29.
 */

@Action("testAction")
public class TestAction extends TBaseAction {


    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
        Log.d("test", "TestAction onCreate");
    }



    @Component("component1")
    public TBaseComponent component1()
    {

        return new Test1Component();
    }


    @Component
    public TBaseComponent component2()
    {
        return new Test2Component();
    }




}
