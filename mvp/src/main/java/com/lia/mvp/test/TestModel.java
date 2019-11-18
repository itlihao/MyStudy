package com.lia.mvp.test;

import com.lia.mvp.base.BaseModel;
import com.lia.networklib.base.BaseResult;
import com.lia.networklib.base.UserInterface;
import com.lia.networklib.exception.ApiException;
import com.lia.networklib.network.NetUtil;
import com.lia.networklib.observer.RxObserver;

public class TestModel extends BaseModel<TestPresenter, TestContract.Model> {

    TestModel(TestPresenter testPresenter) {
        super(testPresenter);
    }

    @Override
    public TestContract.Model getContract() {
        return new TestContract.Model() {
            @Override
            public void executeTest() {
                NetUtil.getInstance().apiTest(p.getView(), new RxObserver<BaseResult<UserInterface>>(p.getView()) {
                    @Override
                    public void onNextC(BaseResult<UserInterface> result) {
                        if (result.getData() != null) {
                            p.getContract().responseResult(result.getData());
                        } else {
                            p.getContract().responseResult(null);
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
            }
        };
    }
}
