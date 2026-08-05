package com.example.order_service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductServiceResponse(
        Long productId,
        String productName,
        BigDecimal price
){
}
