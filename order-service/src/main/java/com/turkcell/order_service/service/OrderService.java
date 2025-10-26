package com.turkcell.order_service.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.turkcell.order_service.dto.request.CreateOrderRequest;
import com.turkcell.order_service.dto.response.CreatedOrderResponse;
import com.turkcell.order_service.entity.Order;
import com.turkcell.order_service.mapper.OrderMapper;
import com.turkcell.order_service.repository.OrderRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }
    
    public List<CreatedOrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.orderListToCreatedOrderResponseList(orders);
    }
    
    public CreatedOrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("order not found"));
        return orderMapper.toResponse(order);
    }
    
    public CreatedOrderResponse createOrder(@Valid CreateOrderRequest request) {
        Order order = orderMapper.toOrderEntity(request);
        order.setOrderDate(new Date());
        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
}
