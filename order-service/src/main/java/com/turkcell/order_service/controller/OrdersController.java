package com.turkcell.order_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.order_service.dto.request.CreateOrderRequest;
import com.turkcell.order_service.dto.response.CreatedOrderResponse;
import com.turkcell.order_service.entity.OutboxMessage;
import com.turkcell.order_service.events.OrderCreatedEvent;
import com.turkcell.order_service.repository.OutboxRepository;
import com.turkcell.order_service.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public OrdersController(OrderService orderService, OutboxRepository outboxRepository,
            ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public CreatedOrderResponse createOrder(@RequestBody CreateOrderRequest request) throws JsonProcessingException {

        CreatedOrderResponse order = orderService.createOrder(request);
        OrderCreatedEvent event = new OrderCreatedEvent(order.getProductId());

        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setAggregateId(UUID.randomUUID()); // normalde db'de oluşan order'ın idsi
        outboxMessage.setAggregateType("Order");
        outboxMessage.setEventId(UUID.randomUUID());
        outboxMessage.setEventType("OrderCreatedEvent");
        outboxMessage.setPayloadJson(objectMapper.writeValueAsString(event));
        outboxRepository.save(outboxMessage);

        return order;
    }

    @GetMapping("{id}")
    public CreatedOrderResponse getOrderById(@PathVariable("id") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping()
    public List<CreatedOrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

}