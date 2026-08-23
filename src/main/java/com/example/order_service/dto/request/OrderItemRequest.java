package com.example.order_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemRequest(
        @NotNull(message = "Product is required")
        @Min(0)
        Long productId,
        @NotNull(message = "Quantity is required")
        @Min(1)
        Integer quantity
) {
}
