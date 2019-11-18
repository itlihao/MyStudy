package com.lia.networklib.observer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lia.networklib.exception.ApiException;

import io.reactivex.disposables.Disposable;

public abstract class RxObserver<T> extends BaseObserver<T> {
    private static final String TAG = RxObserver.class.getSimpleName();

    private Context mContext;

    protected RxObserver(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(Disposable subscription) {
        Log.e(TAG, "onSubscribe >>" + "开始任务");
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete >>" + "任务完成");
    }

    @Override
    protected void onError(ApiException ex) {
        Toast.makeText(mContext, ex.code, Toast.LENGTH_LONG).show();
        Log.e(TAG, "onError >>" + ex.message);
    }

    public abstract void onNextC(T t);

    @Override
    public void onNext(T t) {
        onNextC(t);
    }
}
