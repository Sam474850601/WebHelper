package com.to8to.utils.webhelper_sample.web.component;

import android.content.Context;

import com.to8to.utils.webhelper.core.TBaseComponent;
import com.to8to.utils.webhelper.core.ann.RequestMethod;
import com.to8to.utils.webhelper.core.bean.Request;
import com.to8to.utils.webhelper_sample.model.ICallback;
import com.to8to.utils.webhelper_sample.model.IUserModel;
import com.to8to.utils.webhelper_sample.model.UserModel;
import com.to8to.utils.webhelper_sample.ui.view.ITestView;


/**
 * Created by same.li on 2018/1/23.
 */

public class UserConsumeComponent extends TBaseComponent {

    @Override
    protected void onCreate(Context context) {
        super.onCreate(context);
    }

    final IUserModel userModel = new UserModel();

    @RequestMethod("login")
    public void login(ITestView testView, final Request request, UserLoginParam userData) {
        String userName = userData.getUsername();
        String password = userData.getPassword();
        testView.toast("用户名:" + userName + ",密码：" + password);
        userModel.login(userName, password, new ICallback() {

            @Override
            public void onResponse(String message) {
                invokeJs(request, message);
            }

        });
    }





    public static  class UserLoginParam
    {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
