package com.lia.mvp.test;

import com.lia.mvp.MainActivity;
import com.lia.mvp.base.BasePresenter;
import com.lia.networklib.base.UserInterface;

public class TestPresenter extends BasePresenter<MainActivity, TestModel, TestContract.Presnter> {
    @Override
    public TestModel getModel() {
        return new TestModel(this);
    }

    @Override
    public TestContract.Presnter getContract() {
        return new TestContract.Presnter<UserInterface>() {
            @Override
            public void requestTest() {
                m.getContract().executeTest();
            }

            @Override
            public void responseResult(UserInterface userInterface) {
                getView().getContract().testResult(userInterface);
            }
        };
    }
}
