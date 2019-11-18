package com.lia.networklib.exception;

/**
 * Created by android on 2017/12/6.
 */
public class ServerException extends RuntimeException {
    // 异常处理，为速度，不必要设置getter和setter
    public String code;
    public String message;

    public ServerException(String message, String code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}