package com.hunter.fastandroid.exception;

/**
 * Created by Ryan on 2017/8/1.
 */
public class ServerException extends RuntimeException{

    private int code;

    private String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
