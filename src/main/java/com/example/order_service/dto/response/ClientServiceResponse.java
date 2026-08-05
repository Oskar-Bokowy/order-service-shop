package com.example.order_service.dto.response;

import lombok.Builder;

@Builder
public record ClientServiceResponse (
        Long clientId
) {
}
