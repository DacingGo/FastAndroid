package com.hunter.fastandroid.rx;

import com.hunter.fastandroid.vo.BaseEntity;

import rx.Observable;

/**
 * Created by Ryan on 2017/8/1.
 */
public class HandleErrorTransformer<T> implements Observable.Transformer<BaseEntity<T>,T>{


    @Override
    public Observable<T> call(Observable<BaseEntity<T>> baseEntityObservable) {
        return baseEntityObservable.map(new ServerResponseFunc<T>())
                .onErrorResumeNext(new HttpResponseFunc<T>());
    }
}
