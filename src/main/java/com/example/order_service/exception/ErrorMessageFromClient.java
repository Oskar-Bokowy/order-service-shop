package com.example.order_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorMessageFromClient {
    private final String message;
}
