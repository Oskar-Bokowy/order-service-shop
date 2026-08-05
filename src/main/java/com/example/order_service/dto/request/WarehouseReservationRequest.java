package com.example.order_service.dto.request;

import lombok.Builder;

import java.util.Set;

@Builder
public record WarehouseReservationRequest (
        Set<WarehouseServiceRequest> warehouseServiceRequests
){
}
