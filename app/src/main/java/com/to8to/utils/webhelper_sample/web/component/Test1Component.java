package com.to8to.utils.webhelper_sample.web.component;

import android.content.Context;
import android.util.Log;

import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper_sample.bean.RequestParam;

/**
 * Created by same.li on 2018/1/29.
 */

public class Test1Component  extends TBaseComponent{

    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
        Log.e("test", "Test1Component onCreate");
    }


    @RequestMethod
    public void  method0()
    {
        Log.e("test", "Test1Component method0");
    }


    @RequestMethod
    public void  method1()
    {
        Log.e("test", "Test1Component method1");
    }



    @RequestMethod
    public void  method2(boolean  isSuccess)
    {
        Log.e("test", "Test1Component method2 isSuccess:"+isSuccess);
    }


    @RequestMethod
    public void  method3(int   value)
    {
        Log.e("test", "Test1Component  method3:"+value);

    }



    @RequestMethod
    public void  method4(String data)
    {
        Log.e("test", "Test1Component  method4:"+data);
    }


    /**
     * @param requestParam  任意格式 json bean都可以。
     */
    @RequestMethod
    public void  method5(RequestParam requestParam)
    {
        Log.e("test", "Test1Component  method6: data1="+requestParam.getData1()+
        ", data2="+requestParam.getData2());
    }


    //返回的String 不能有特殊符号。如果必须要传。那么自行用base64压缩。在文档注释
    @RequestMethod
    public String  method6()
    {
        Log.e("test", "Test1Component  method6");
        return "method6 data";
    }


    public  static  class Data
    {
        private String testData1;
        private String testData;

        public String getTestData1() {
            return testData1;
        }

        public void setTestData1(String testData1) {
            this.testData1 = testData1;
        }

        public String getTestData() {
            return testData;
        }

        public void setTestData(String testData) {
            this.testData = testData;
        }
    }


    public  static  class Param
    {
        private String testParam1;
        private String testParam2;

        public String getTestParam1() {
            return testParam1;
        }

        public void setTestParam1(String testParam1) {
            this.testParam1 = testParam1;
        }

        public String getTestParam2() {
            return testParam2;
        }

        public void setTestParam2(String testParam2) {
            this.testParam2 = testParam2;
        }
    }



    /**
     * ResponseDataBean会被转化成json字符串传给js
     * @param param  {'component':'','method':'','data': '', 'callback':''} 中的data值
     * @return jsonbean ,最终会传给js
     */
    @RequestMethod
    public Data method7(Param param)
    {
        Log.e("test", "Test1Component  method7 param1"+param.getTestParam1());
        Log.e("test", "Test1Component  method7 param2"+param.getTestParam2());
        Data data = new Data();
        data.setTestData("dadfsaf");
        data.setTestData1("ddddddd");
        return  data;
    }

}
