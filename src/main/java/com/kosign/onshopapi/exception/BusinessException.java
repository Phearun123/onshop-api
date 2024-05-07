package com.kosign.onshopapi.exception;

import com.kosign.onshopapi.common.api.StatusCode;
public class BusinessException extends RuntimeException {
    private Object body;
    private final StatusCode errorCode;

    public BusinessException(StatusCode errorCode, Object body) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.body = body;
    }

    public BusinessException(StatusCode errorCode, String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(StatusCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(StatusCode errorCode, Throwable e) {

        this(errorCode);
    }

    public StatusCode getErrorCode() {
        return errorCode;
    }

    public Object getBody() {
        return body;
    }


 }
