package com.to8to.utils.webhelper_sample.web.component;

import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper_sample.ui.view.ITestView;

/**
 * Created by same.li on 2018/1/30.
 */

public class Test3Component extends TBaseComponent {


    @RequestMethod
    public void method1(ITestView testView)
    {
        testView.toast("Test3Component method1");
    }


}
