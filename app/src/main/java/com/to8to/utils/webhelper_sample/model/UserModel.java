package com.to8to.utils.webhelper_sample.model;

import com.to8to.utils.webhelper_sample.bean.User;

/**
 * Created by same.li on 2018/1/23.
 */

public class UserModel implements IUserModel {


    @Override
    public User getInfo(String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setAge(16);
        user.setNikeName("Sam");
        user.setDesc("个性签名个性签名哈哈哈");
        return  user;
    }

    @Override
    public int getAge(String userName) {
        return 16;
    }

    @Override
    public void login(String userName, String passwd, ICallback callback) {

        if ("Same.li".equals(userName) && "666666".equals(passwd))
        {
            callback.onResponse("登录成功");
        }
        else
        {
            callback.onResponse("用户名或密码错误");
        }

    }


}
