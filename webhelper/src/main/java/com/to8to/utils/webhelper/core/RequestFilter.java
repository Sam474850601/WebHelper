package com.to8to.utils.webhelper.core;

import com.to8to.utils.webhelper.core.bean.Request;

/**
 * Created by same.li on 2018/1/10.
 * 请求过滤器。每次action请求处理前，都会执行这个方法。
 */

public interface RequestFilter {
     Request onFilter(String data);
}
