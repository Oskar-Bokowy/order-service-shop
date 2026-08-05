package com.example.order_service.service;

import com.example.order_service.dto.request.OrderItemRequest;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.request.WarehouseReservationRequest;
import com.example.order_service.dto.request.WarehouseServiceRequest;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.dto.response.ProductServiceResponse;
import com.example.order_service.mapper.OrderItemMapper;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import com.example.order_service.repository.OrderItemRepository;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.restClient.ClientServiceClient;
import com.example.order_service.restClient.ProductServiceClient;
import com.example.order_service.restClient.WarehouseServiceClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    private final ClientServiceClient clientServiceClient;
    private final ProductServiceClient productServiceClient;
    private final WarehouseServiceClient warehouseServiceClient;

    public ProductServiceResponse getProductForOrder(Long productId) {
        return productServiceClient.getProductById(productId);
    }

    public void checkIfClientExisting(Long clientId) {
        clientServiceClient.getClientById(clientId);
    }

    public void checkIfQuantityAvailable(WarehouseReservationRequest warehouseReservationRequest) {
        warehouseServiceClient.reserve(warehouseReservationRequest);
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        checkIfClientExisting(request.clientId());
        Order order = orderMapper.toEntity(request);
        Set<WarehouseServiceRequest> warehouseItems = new HashSet<>();
        for (OrderItemRequest item : request.orderItemsRequest()) {
            OrderItem itemForSave = orderItemMapper.toEntityFromServiceProduct(getProductForOrder(item.productId()));
            itemForSave.setQuantity(item.quantity());
            order.addItem(itemForSave);
            warehouseItems.add(new WarehouseServiceRequest(item.productId(), item.quantity()));
        }
        checkIfQuantityAvailable(new WarehouseReservationRequest(warehouseItems));
        order.calculateTotalPrice();
        Order orderToSave = orderRepository.save(order);
        return orderMapper.toResponse(orderToSave);
    }


    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse updatedById(OrderRequest orderRequest, Long orderId) {
        Order existing = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.toResponse(existing);
    }
}
