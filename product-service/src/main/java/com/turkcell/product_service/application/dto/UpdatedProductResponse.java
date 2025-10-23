package com.turkcell.product_service.application.dto;

import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;

public record UpdatedProductResponse(
    String name, 
    String description, 
    Price price, 
    Stock stock
) {}
