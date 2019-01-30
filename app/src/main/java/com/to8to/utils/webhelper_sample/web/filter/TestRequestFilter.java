package com.to8to.utils.webhelper_sample.web.filter;

import com.to8to.utils.webhelper.core.RequestFilter;
import com.to8to.utils.webhelper.core.bean.Request;

import org.json.JSONException;
import org.json.JSONObject;


public class TestRequestFilter implements RequestFilter {


    @Override
    public Request onFilter(String data) {
        Request request = new Request();
        request.setComponent("test4C");
        request.setMethod("method1");
        request.setData(data);
        return request;
    }
}
