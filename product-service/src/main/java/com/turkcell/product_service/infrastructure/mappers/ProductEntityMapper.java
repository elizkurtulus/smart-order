package com.turkcell.product_service.infrastructure.mappers;

import java.math.BigDecimal;
import com.turkcell.product_service.domain.valueobjects.*;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.infrastructure.entities.JpaProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper
{
    public JpaProductEntity toEntity(Product product)
    {
        JpaProductEntity productEntity = new JpaProductEntity();
        productEntity.setId(product.getId().getValue());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setAmount(product.getPrice().getAmount());
        productEntity.setCurrency(product.getPrice().getCurrency().getCode());
        productEntity.setStock(product.getStock().getQuantity());
        return productEntity;
    }

    public Product toDomain(JpaProductEntity entity)
    {
        return Product.reconstruct(
                ProductId.fromString(entity.id().toString()),
                entity.name(),
                entity.description(),
                new Price((BigDecimal) entity.amount(), Currency.fromCode(entity.currency())),
                new Stock(entity.stock())
        );
    }
}