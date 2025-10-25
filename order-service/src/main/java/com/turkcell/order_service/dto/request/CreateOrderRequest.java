package com.turkcell.order_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateOrderRequest {
    @Size(min = 3, max = 30)
    @NotBlank
    private String name;
    @NotBlank
    private String productId;
    @NotBlank
    private String customerId;

    // Constructors
    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String name, String productId, String customerId) {
        this.name = name;
        this.productId = productId;
        this.customerId = customerId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
