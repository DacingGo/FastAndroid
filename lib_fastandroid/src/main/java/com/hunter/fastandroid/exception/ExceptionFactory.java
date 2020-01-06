package com.hunter.fastandroid.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Ryan on 2017/8/1.
 */
public class ExceptionFactory {


    public static ApiException handleException(Throwable throwable) {
        ApiException ex;
        // HTTP 错误
        if (throwable instanceof HttpException) {
            ex = new ApiException(throwable, ERROR.HTTP_ERROR);
            ex.setMessage("网络错误");
            return ex;
        } else if (throwable instanceof ServerException) {
            ServerException e = (ServerException) throwable;
            ex = new ApiException(e, ERROR.SERVER_ERROR);
            ex.setMessage(e.getMessage());
            return ex;
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException) {
            ex = new ApiException(throwable, ERROR.PARSE_ERROR);
            ex.setMessage("解析错误");            //均视为解析错误
            return ex;
        } else if (throwable instanceof ConnectException) {
            ex = new ApiException(throwable, ERROR.NETWORK_ERROR);
            ex.setMessage("连接失败");  //均视为网络错误
            return ex;
        } else if (throwable instanceof SocketTimeoutException) {
            ex = new ApiException(throwable, ERROR.NETWORK_ERROR);
            ex.setMessage("连接超时");  //均视为网络错误
            return ex;
        } else {
            ex = new ApiException(throwable, ERROR.UNKNOWN);
            ex.setMessage("未知错误");          //未知错误
            return ex;
        }
    }


    public static class ERROR {
        public static final int HTTP_ERROR = 1001;

        public static final int SERVER_ERROR = 1002;

        public static final int PARSE_ERROR = 1003;

        public static final int NETWORK_ERROR = 1004;

        public static final int UNKNOWN = 1005;
    }
}
