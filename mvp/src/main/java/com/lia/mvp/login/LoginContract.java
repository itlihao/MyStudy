package com.lia.mvp.login;

import com.lia.networklib.base.BaseEntity;

public interface LoginContract {

    interface Model {
        // Model层子类完成方法的具体实现
        void executeLogin(String name, String pwd);
    }

    interface View<T extends BaseEntity> {
        // JavaBean返回结果
        void callBackResult(T t);
    }

    interface Presnter<T extends BaseEntity> {
        // 登录请求
        void requestLogin(String name, String pwd);

        // 结果响应，接收到Model层结果，通知View层去刷新
        void responseResult(T t);
    }
}
