package com.example.online_banking.rest.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseData<T> {
    private int status;
    private String error;
    private String errorCode;
    private String errorDesc;
    private T data;

    public static <T> ResponseData<T> ok() {
        return restResult(null, HttpStatus.OK, null, null);
    }

    public static <T> ResponseData<T> ok(T data) {
        return restResult(data, HttpStatus.OK, null, null);
    }

    public static <T> ResponseData<T> failed(HttpStatus status, String errorCode, String errorDesc) {
        return restResult(null, status, errorCode, errorDesc);
    }

    public static <T> ResponseData<T> failed(T data, HttpStatus status, String errorCode, String errorDesc) {
        return restResult(data, status, errorCode, errorDesc);
    }

    private static <T> ResponseData<T> restResult(T data, HttpStatus status, String errorCode, String errorDesc) {
        ResponseData<T> apiResult = new ResponseData();
        apiResult.setStatus(status.value());
        apiResult.setError(status.getReasonPhrase());
        apiResult.setErrorCode(errorCode);
        apiResult.setErrorDesc(errorDesc);
        apiResult.setData(data);
        return apiResult;
    }

    public ResponseData() {
    }
}
