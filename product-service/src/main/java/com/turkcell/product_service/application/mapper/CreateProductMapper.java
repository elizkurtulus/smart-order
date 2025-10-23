package com.turkcell.product_service.application.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.command.CreateProductCommand;
import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.Currency;
import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;

@Component
public class CreateProductMapper {
    public Product toDomain(CreateProductCommand command) {
        return Product.create(
            command.name(), 
            command.description(), 
            new Price(command.amount(), Currency.fromCode(command.code())),
            new Stock(command.quantity())
        );
    }

    public CreatedProductResponse toResponse(Product product) {
        return new CreatedProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock()
        );
    }
}
