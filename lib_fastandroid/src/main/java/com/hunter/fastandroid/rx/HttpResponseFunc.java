package com.hunter.fastandroid.rx;

import com.hunter.fastandroid.exception.ExceptionFactory;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Ryan on 2017/8/1.
 */

public class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>>{

    @Override
    public Observable<T> call(Throwable throwable) {
        // handle error
        return Observable.error(ExceptionFactory.handleException(throwable));
    }
}
