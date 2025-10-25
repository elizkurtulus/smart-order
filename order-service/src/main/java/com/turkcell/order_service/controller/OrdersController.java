package com.turkcell.order_service.controller;

import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.order_service.dto.request.CreateOrderRequest;
import com.turkcell.order_service.dto.response.CreatedOrderResponse;
import com.turkcell.order_service.events.OrderCreatedEvent;
import com.turkcell.order_service.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final StreamBridge streamBridge;
    private final OrderService orderService;

    public OrdersController(StreamBridge streamBridge, OrderService orderService) {
        this.streamBridge = streamBridge;
        this.orderService = orderService;
    }

    @PostMapping()
    public CreatedOrderResponse createOrder(@RequestBody CreateOrderRequest request) {

        CreatedOrderResponse order = orderService.createOrder(request);
        OrderCreatedEvent event = new OrderCreatedEvent(order.getProductId());

        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(event).build();

        streamBridge.send("orderCreated-out-0", message);

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