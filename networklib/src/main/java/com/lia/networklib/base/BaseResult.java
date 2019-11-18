package com.lia.networklib.base;

import com.google.gson.annotations.SerializedName;

public class BaseResult<T> extends BaseEntity {

    @SerializedName(value = "status", alternate = {"code", "statusCode", "errorCode"})
    private String statusCode;
    @SerializedName(value = "message")
    private String message;
    @SerializedName(value = "data")
    private T data;
    @SerializedName(value = "token")
    private String token;

    public String getStatusCode() {
        return statusCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
