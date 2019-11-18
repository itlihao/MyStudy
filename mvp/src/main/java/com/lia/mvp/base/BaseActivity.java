package com.lia.mvp.base;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.RxActivity;

public abstract class BaseActivity<P extends BasePresenter, CONTRACT> extends RxActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected P p;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 弱应用
        p = getPersenter();
        // 绑定view
        p.bindView(BaseActivity.this);
    }

    public abstract CONTRACT getContract();

    // 从子类获取Presenter
    protected abstract P getPersenter();

    // 出现异常时告知View层
    public void error(Exception e) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unBindView();
    }
}
