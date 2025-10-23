package com.turkcell.product_service.application.dto;

import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.domain.valueobjects.Stock;

public record CreatedProductResponse(
    ProductId id, 
    String name, 
    String description, 
    Price price, 
    Stock stock
) {}
