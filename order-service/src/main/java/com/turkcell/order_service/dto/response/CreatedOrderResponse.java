package com.turkcell.order_service.dto.response;

import java.util.Date;
import java.util.UUID;

public class CreatedOrderResponse {
    private UUID id;
    private String name;
    private Date orderDate;
    private String productId;
    private String customerId;

    // Constructors
    public CreatedOrderResponse() {
    }

    public CreatedOrderResponse(UUID id, String name, Date orderDate, String productId, String customerId) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.productId = productId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
