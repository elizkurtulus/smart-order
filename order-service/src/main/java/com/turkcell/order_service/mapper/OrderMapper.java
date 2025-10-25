package com.turkcell.order_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.turkcell.order_service.dto.request.CreateOrderRequest;
import com.turkcell.order_service.dto.response.CreatedOrderResponse;
import com.turkcell.order_service.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    Order toEntity(CreateOrderRequest request);
    
    CreatedOrderResponse toResponse(Order order);


    List<CreatedOrderResponse> orderListToCreatedOrderResponseList(List<Order> orderList);
}
