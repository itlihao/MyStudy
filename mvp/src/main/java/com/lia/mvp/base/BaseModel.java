package com.lia.mvp.base;

public abstract class BaseModel<P extends BasePresenter, CONTRACT> {

    public P p;

    public BaseModel(P p) {
        this.p = p;
    }

    public abstract CONTRACT getContract();
}
