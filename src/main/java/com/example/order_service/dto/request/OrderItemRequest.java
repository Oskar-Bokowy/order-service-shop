package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemRequest (
        @NotNull(message = "Product is required")
        Long productId,
        @NotNull(message = "Quantity is required")
        Integer quantity
) {
}
