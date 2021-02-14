package com.spring.fileupload.common.exception;

public class NotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE_TEMPLATE = "The entity/resource %s is not found";

    public NotFoundException(Class resource) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, resource.getSimpleName()));
    }

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

    public NotFoundException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
