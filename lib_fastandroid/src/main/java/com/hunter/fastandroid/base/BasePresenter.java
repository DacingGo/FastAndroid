package com.hunter.fastandroid.base;

import com.hunter.fastandroid.app.ServiceManager;
import com.hunter.fastandroid.rx.HandleErrorTransformer;
import com.hunter.fastandroid.vo.BaseEntity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter基类
 *
 * @author Hunter
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;

    public void attach(V view) {
        mView = view;
    }

    public void detach(){
        mView = null;
    }

    public BasePresenter() {
        initService();
    }

    protected abstract void initService();

    public <T> T getService(Class<T> clazz) {
        ServiceManager serviceManager = ServiceManager.getInstance();
        return serviceManager.getService(clazz);
    }

    public <T> void subscribe(Observable<BaseEntity<T>> observable, Subscriber<T> subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HandleErrorTransformer<T>())
                .compose(mView.<T>bind())
                .subscribe(subscriber);
    }
}

