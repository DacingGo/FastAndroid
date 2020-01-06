package com.hunter.fastandroid.rx;

import android.content.DialogInterface;

import com.hunter.fastandroid.base.IBaseView;
import com.hunter.fastandroid.exception.ApiException;
import com.orhanobut.logger.Logger;

/**
 * RxJava 自定义Subscriber
 *
 * @param <T>
 * @author Hunter
 */
public abstract class ResponseSubscriber<T> extends ErrorSubscriber<T> {
    private static final String TAG = "ResponseSubscriber";
    private IBaseView mBaseView;

    public ResponseSubscriber(IBaseView baseView) {
        mBaseView = baseView;
        mBaseView.setProgressCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ResponseSubscriber.this.unsubscribe();
            }
        });

    }

    @Override
    public void onStart() {
        mBaseView.showProgress("");
    }

    @Override
    public void onCompleted() {
        mBaseView.hideProgress();
    }

    @Override
    protected void onError(ApiException ex) {
        mBaseView.hideProgress();
        Logger.e(TAG, ex.getMessage());
        mBaseView.showToast(ex.getMessage());
    }
}
