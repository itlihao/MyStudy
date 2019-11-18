package com.lia.mvp.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseActivity, M extends BaseModel, CONTRACT> {

    protected M m;

    // 绑定View弱应用
    private WeakReference<V> vWeakReference;

    public BasePresenter() {
        m = getModel();
    }

    void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    public abstract M getModel();

    public abstract CONTRACT getContract();

}
