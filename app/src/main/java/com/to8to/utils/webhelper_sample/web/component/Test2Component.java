package com.to8to.utils.webhelper_sample.web.component;

import android.content.Context;
import android.util.Log;

import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.core.bean.Request;
import com.to8to.utils.webhelper_sample.ui.view.ITest2View;
import com.to8to.utils.webhelper_sample.ui.view.ITest3View;
import com.to8to.utils.webhelper_sample.ui.view.ITestView;

/**
 * Created by same.li on 2018/1/29.
 */

public class Test2Component extends TBaseComponent{

    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
        Log.d("test", "Test2Component onCreate");
    }

    // 参数中IView的引用，Request引用 可有可无，不限制顺序.

    @RequestMethod
    public void method1(ITestView view)
    {
        view.toast("holy shit!");
    }

    @RequestMethod
    public void method2(ITest2View view)
    {
        view.setMessage("method2 was called!");
    }

    @RequestMethod
    public void method3(ITest3View view)
    {
        view.toast("method3 dasfasdf");
        view.setMessage("method3 was called!");
    }


    @RequestMethod
    public void method4(ITest3View view, String data)
    {
        view.toast(data);
        view.setMessage(data);
    }

    static class MethodParam
    {
       private  String name;
       private int age;
       private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Method4Param{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }


    @RequestMethod
    public void method5(ITest3View view, MethodParam param)
    {
        view.toast(param.toString());
        view.setMessage(param.toString());
    }





    @RequestMethod
    public String  method6(ITest3View view, MethodParam param)
    {
        view.toast("method6 :"+param.toString());
        view.setMessage("method6 :"+param.toString());
        return "call method ok";
    }


    static class Data
    {
        private String data;
        private String name;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    @RequestMethod
    public Data  method7(ITest3View view, MethodParam param)
    {
        view.toast(param.toString());
        view.setMessage(param.toString());
        Data data = new Data();
        data.setData("111111");
        data.setName("hahahaha");
        return data;
    }

    // 参数中IView的引用，Request引用 可有可无，不限制顺序.
    @RequestMethod
    public void   method8(ITest3View view, Request request, MethodParam param)
    {
        view.toast(param.toString());
        view.setMessage(param.toString());
        Data data = new Data();
        data.setData("111111");
        data.setName("hahahaha");
        //如果是异步，那么可以这样回调js
        invokeJs(request, data);
    }




}
