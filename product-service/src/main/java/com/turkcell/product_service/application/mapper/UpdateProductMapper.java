package com.turkcell.product_service.application.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.command.UpdateProductCommand;
import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.Currency;
import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;

@Component
public class UpdateProductMapper {
    public Product toDomain(UpdateProductCommand command) {
        return Product.reconstruct(
            command.id(),
            command.name(), 
            command.description(), 
            new Price(command.amount(), Currency.fromCode(command.code())),
            new Stock(command.quantity())
        );
    }

    public UpdatedProductResponse toResponse(Product product) {
        return new UpdatedProductResponse(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock()
        );
    }
}
