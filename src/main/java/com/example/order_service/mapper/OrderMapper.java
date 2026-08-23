package com.example.order_service.mapper;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;


    public OrderResponse toResponse(Order order){
        return OrderResponse.builder()
                .clientId(order.getClientId())
                .createdAt(order.getCreatedAt())
                .orderItemsResponse(orderItemMapper.toResponseSet(order.getItems()))
                .build();
    }


    public Order toEntity(OrderRequest request){
        return Order.builder()
                .clientId(request.clientId())
                .createdAt(LocalDateTime.now())
                .items(new HashSet<>())
                .build();
    }
}
