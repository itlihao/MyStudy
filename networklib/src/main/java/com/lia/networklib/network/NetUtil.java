package com.lia.networklib.network;

import android.content.Context;

import com.lia.networklib.base.BaseResult;
import com.lia.networklib.base.UserInterface;
import com.lia.networklib.manager.RetrofitManager;
import com.lia.networklib.scheduler.RetryWhenHandler;
import com.lia.networklib.scheduler.RxSchedulers;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class NetUtil {

    private NetUtil() {
    }

    private static volatile NetUtil single = null;

    // 静态工厂方法
    public static NetUtil getInstance() {
        if (single == null) {
            synchronized (NetUtil.class) {
                if (single == null) {
                    single = new NetUtil();
                }
            }
        }
        return single;
    }

    public void apiTest(Context context, Observer<BaseResult<UserInterface>> observer) {
        HashMap<String, String> parmarData2 = new HashMap<>();
        Observable<BaseResult<UserInterface>> observable =
                RetrofitManager.getInstance().getNetApi().ApiTest(parmarData2);

        observable.retryWhen(new RetryWhenHandler())
                .compose(RxSchedulers.io_main(context)).subscribe(observer);
    }
}
