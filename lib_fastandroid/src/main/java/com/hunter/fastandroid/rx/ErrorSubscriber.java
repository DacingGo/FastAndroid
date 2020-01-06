package com.hunter.fastandroid.rx;

import com.hunter.fastandroid.exception.ApiException;

import rx.Subscriber;


/**
 * Created by Ryan on 2017/8/1.
 */
public abstract class ErrorSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            // ???
            onError(new ApiException(e,123));
        }
    }


    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}