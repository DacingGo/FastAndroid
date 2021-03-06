package com.hunter.fastandroid.exception;

/**
 * 自定义异常
 *
 * @author Hunter
 */
public class ApiException extends Exception {

    private int code;

    private String message;

    public ApiException(Throwable throwable,int code) {
        super(throwable);
        this.code = code;
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
