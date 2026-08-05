package com.example.order_service.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;


@Builder
public record OrderResponse (
        Long clientId,
        LocalDateTime createdAt,
        Set<OrderItemResponse> orderItemsResponse
) {
}
