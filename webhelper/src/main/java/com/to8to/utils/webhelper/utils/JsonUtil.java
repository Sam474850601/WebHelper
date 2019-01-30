package com.to8to.utils.webhelper.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by same.li on 2018/1/11.
 * Json转化工具
 */
public final class JsonUtil {

    public static <T> T getBean(String json, Class<T> classType) {

        if(null != json && !"".equals(json)) {
            try {
                Gson gson = new Gson();
                Object bean = gson.fromJson(json, classType);
                return (T) bean;
            } catch (Exception e) {
                WLog.log( "error:Exception",e);
            }
        }
            return null;

    }

    public static String toJson(Object obj) {
        if(null != obj) {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } else {
            return "{}";
        }
    }
}