package com.example.order_service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemResponse (
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal priceAtPurchase
) {
}
