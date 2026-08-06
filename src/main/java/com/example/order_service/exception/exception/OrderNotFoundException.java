package com.example.order_service.exception.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderNotFoundException extends RuntimeException {
    private final String message;
    private HttpStatus httpStatus;

    public OrderNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
