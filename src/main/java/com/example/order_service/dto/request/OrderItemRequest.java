package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemRequest (
        @NotNull
        Long productId,
        @NotNull
        Integer quantity
) {
}
