package com.spring.fileupload.common.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private String error;
    private String reason;
    private String path;

    public ErrorResponse(String message) {
        this.error = message;
    }

    public ErrorResponse() {
    }
}
