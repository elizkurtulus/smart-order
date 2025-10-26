package com.turkcell.order_service.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.order_service.dto.request.CreateOrderRequest;
import com.turkcell.order_service.dto.response.CreatedOrderResponse;
import com.turkcell.order_service.entity.Order;

import java.util.List;

@Component
public class OrderMapper {
    
    public Order toOrderEntity(CreateOrderRequest request) {
        Order order = new Order();
        order.setName(request.getName());
        order.setProductId(request.getProductId());
        order.setCustomerId(request.getCustomerId());
        return order;
    }
    
    public CreatedOrderResponse toResponse(Order order) {
        CreatedOrderResponse response = new CreatedOrderResponse();
        response.setId(order.getId());
        response.setName(order.getName());
        response.setOrderDate(order.getOrderDate());
        response.setProductId(order.getProductId());
        response.setCustomerId(order.getCustomerId());
        return response;
    }

    public List<CreatedOrderResponse> orderListToCreatedOrderResponseList(List<Order> orderList) {
        return orderList.stream()
                .map(this::toResponse)
                .toList();
    }
}
