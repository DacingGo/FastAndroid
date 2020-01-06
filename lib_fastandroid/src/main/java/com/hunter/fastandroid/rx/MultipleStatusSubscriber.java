package com.hunter.fastandroid.rx;

import com.hunter.fastandroid.base.IBaseRefreshView;
import com.hunter.fastandroid.base.IBaseView;
import com.hunter.fastandroid.exception.ApiException;
import com.hunter.fastandroid.exception.ExceptionFactory;

import rx.Subscriber;

/**
 * Created by Ryan on 2017/8/2.
 */
public class MultipleStatusSubscriber<T> extends ErrorSubscriber<T>{

    private IBaseRefreshView mView;

    public MultipleStatusSubscriber(IBaseRefreshView view) {
        mView = view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.showContentView();
    }

    @Override
    protected void onError(ApiException ex) {
        if (ex.getCode() == ExceptionFactory.ERROR.NETWORK_ERROR) {
            mView.showNetworkErrorView();
        }
        mView.showToast(ex.getCode() + "     " +  ex.getMessage());
        mView.setRefreshing(false);
    }

    @Override
    public void onCompleted() {
        mView.setRefreshing(false);
    }

    @Override
    public void onNext(T t) {
        mView.showContentView();
    }
}
