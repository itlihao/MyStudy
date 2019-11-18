package com.lia.mvp.login;

import com.lia.mvp.base.BaseModel;
import com.lia.mvp.entity.UserInfo;
import com.lia.networklib.base.BaseResult;
import com.lia.networklib.base.UserInterface;
import com.lia.networklib.exception.ApiException;
import com.lia.networklib.network.NetUtil;
import com.lia.networklib.observer.RxObserver;

public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {

    LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String pwd) {
                if ("lia".equals(name) && "210".equals(pwd)) {
                    p.getContract().responseResult(new UserInfo("MVP", "lia"));
                } else {
                    p.getContract().responseResult(null);
                }
            }
        };
    }
}
