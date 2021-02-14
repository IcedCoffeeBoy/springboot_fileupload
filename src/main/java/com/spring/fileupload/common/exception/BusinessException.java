package com.spring.fileupload.common.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}
