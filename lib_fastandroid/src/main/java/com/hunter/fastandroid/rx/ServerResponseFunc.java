package com.hunter.fastandroid.rx;

import com.hunter.fastandroid.exception.ServerException;
import com.hunter.fastandroid.vo.BaseEntity;

import rx.functions.Func1;

/**
 * Created by Ryan on 2017/8/1.
 */
public class ServerResponseFunc<T> implements Func1<BaseEntity<T>,T>{

    @Override
    public T call(BaseEntity<T>  baseEntity) {
        if (baseEntity.getCode() != 0) {
            // 服务器返回失败
            throw new ServerException(baseEntity.getCode(),baseEntity.getMsg());
        }
        return baseEntity.getData();
    }
}
