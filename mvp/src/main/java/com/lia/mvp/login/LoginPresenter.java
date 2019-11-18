package com.lia.mvp.login;

import com.lia.mvp.LoginActivity;
import com.lia.mvp.base.BasePresenter;
import com.lia.mvp.entity.UserInfo;
import com.lia.mvp.utils.SharedPreferencesUtil;
import com.lia.networklib.base.UserInterface;

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel, LoginContract.Presnter> {
    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginContract.Presnter getContract() {
        return new LoginContract.Presnter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {
                // 三种风格，
                m.getContract().executeLogin(name, pwd);

                // 2. 让功能模块去工作（Library, 下载、请求、图片加载）

                // 3. P层自己处理
                /*if ("lia".equals(name) && "210".equals(pwd)) {
                    responseResult(new UserInfo("MVP", "lia"));
                } else {
                    responseResult(null);
                }*/
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                // 有结果通知View层
                getView().getContract().callBackResult(userInfo);
                SharedPreferencesUtil.saveBoolData(getView(), "isLogin", true);
            }
        };
    }
}
