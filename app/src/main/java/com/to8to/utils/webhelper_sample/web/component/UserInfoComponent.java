package com.to8to.utils.webhelper_sample.web.component;


import com.to8to.utils.webhelper_sample.bean.User;
import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper_sample.model.IUserModel;
import com.to8to.utils.webhelper_sample.model.UserModel;

/**
 * Created by same.li on 2018/1/11.
 */

public class UserInfoComponent extends TBaseComponent {



    @RequestMethod("all")
    public User getInfo(String username)
    {
         IUserModel userModel = new UserModel();
         return userModel.getInfo(username);
    }


    @RequestMethod("userAge")
    public int getAge(String username)
    {
        IUserModel userModel = new UserModel();
        return userModel.getAge(username);
    }


}
