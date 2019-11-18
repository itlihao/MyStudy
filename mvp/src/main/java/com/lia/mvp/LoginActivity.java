package com.lia.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lia.mvp.base.BaseActivity;
import com.lia.mvp.entity.UserInfo;
import com.lia.mvp.login.LoginContract;
import com.lia.mvp.login.LoginPresenter;
import com.lia.networklib.base.UserInterface;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginContract.View> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        p.getContract().requestLogin("lia", "210");
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void callBackResult(UserInfo userInfo) {
                if (userInfo != null) {
                    Toast.makeText(LoginActivity.this, userInfo.toString(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        };

    }

    @Override
    protected LoginPresenter getPersenter() {
        return new LoginPresenter();
    }
}
