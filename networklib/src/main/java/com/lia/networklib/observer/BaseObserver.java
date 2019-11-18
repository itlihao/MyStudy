package com.lia.networklib.observer;

import com.lia.networklib.exception.ApiException;
import com.lia.networklib.exception.ExceptionHelper;

import io.reactivex.Observer;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ExceptionHelper.exceptionHandler(e)));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
