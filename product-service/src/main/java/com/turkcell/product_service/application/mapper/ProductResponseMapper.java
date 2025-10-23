package com.turkcell.product_service.application.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.dto.ProductDto;
import com.turkcell.product_service.domain.entities.Product;

@Component
public class ProductResponseMapper {
    public ProductDto toResponse(Product product) {
        return new ProductDto(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock()
        );
    }
}
