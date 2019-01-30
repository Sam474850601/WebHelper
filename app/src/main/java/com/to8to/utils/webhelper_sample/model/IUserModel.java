package com.to8to.utils.webhelper_sample.model;

import com.to8to.utils.webhelper_sample.bean.User;

/**
 * Created by same.li on 2018/1/23.
 */

public interface IUserModel {

    User getInfo(String userName);

    int getAge(String userName);

    void login(String userName , String passwd, ICallback callback);

}
