package com.example.order_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String message;
    private LocalDateTime localDateTime;
    private final int httpStatus;
}
