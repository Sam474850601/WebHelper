package com.to8to.utils.webhelper.core;

import com.to8to.utils.webhelper.core.bean.Request;
import com.to8to.utils.webhelper.utils.JsonUtil;

/**
 * Created by same.li on 2018/1/11.
 * 请求json格式为RequestData的过滤器。
 */

public class DefaultRequestFilter implements RequestFilter {

    public  static  RequestFilter newInstance()
    {
        return  new DefaultRequestFilter();
    }

    @Override
    public Request onFilter(String data) {

        Request request = null;
        if(data.startsWith("{") && data.endsWith("}"))
        {
            request  = JsonUtil.getBean(data, Request.class );
        }
        if(null == request)
        {
            request = new Request();
            request.setData(data);
        }
        return request;
    }
}
