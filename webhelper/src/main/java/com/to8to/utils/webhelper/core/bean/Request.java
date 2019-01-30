package com.to8to.utils.webhelper.core.bean;



/**
 * Created by same.li on 2018/1/10.
 */

public class Request {

    private String component;
    private String method;
    private String callback;
    private Object data;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
