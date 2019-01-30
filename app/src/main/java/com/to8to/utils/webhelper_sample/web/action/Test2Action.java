package com.to8to.utils.webhelper_sample.web.action;

import android.content.Context;
import android.util.Log;

import com.to8to.utils.webhelper.core.RequestFilter;
import com.to8to.utils.webhelper.core.TBaseAction;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.Action;
import com.to8to.utils.webhelper.core.ann.Component;
import com.to8to.utils.webhelper_sample.web.component.Test3Component;
import com.to8to.utils.webhelper_sample.web.component.Test4Component;
import com.to8to.utils.webhelper_sample.web.filter.TestRequestFilter;

/**
 * Created by same.li on 2018/1/30.
 */

@Action("t2Action")
public class Test2Action  extends TBaseAction{

    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
        Log.d("test", "Test2Action onCreate");
    }

    final TestRequestFilter filter   = new TestRequestFilter();

    @Override
    protected RequestFilter getFileter() {
        return filter;
    }

    @Override
    protected void unknownRequest(String data) {
        Log.d("test", "unknownRequest data:"+data);
    }



    @Component("test3C")
    public TBaseComponent getTest3Component()
    {

        return new Test3Component();
    }


    @Component("test4C")
    public TBaseComponent getTest4Component()
    {

        return new Test4Component();
    }


}
