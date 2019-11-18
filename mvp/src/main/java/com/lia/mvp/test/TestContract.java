package com.lia.mvp.test;

import com.lia.networklib.base.BaseEntity;

public interface TestContract {

    interface Model {
        void executeTest();
    }

    interface View<T extends BaseEntity> {
        // JavaBean返回结果 根据业务修改
        void testResult(T t);
    }

    interface Presnter<T extends BaseEntity> {
        // test
        void requestTest();

        // 结果响应，接收到Model层结果，通知View层去刷新
        void responseResult(T t);
    }
}
