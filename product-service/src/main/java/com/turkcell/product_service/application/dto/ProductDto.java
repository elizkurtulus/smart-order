package com.turkcell.product_service.application.dto;

import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;

public record ProductDto(
    String name, 
    String description, 
    Price price, 
    Stock stock
) {}
