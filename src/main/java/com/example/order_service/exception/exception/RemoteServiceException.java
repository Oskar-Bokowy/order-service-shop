package com.example.order_service.exception.exception;

import lombok.Getter;

@Getter
public class RemoteServiceException extends RuntimeException {
    private final String responseBody;

    public RemoteServiceException(String responseBody) {
        super(responseBody);
        this.responseBody = responseBody;
    }
}
