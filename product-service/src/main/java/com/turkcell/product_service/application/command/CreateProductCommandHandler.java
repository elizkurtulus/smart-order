package com.turkcell.product_service.application.command;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.application.mapper.CreateProductMapper;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand, CreatedProductResponse> {
    private final ProductRepository productRepository;
    private final CreateProductMapper createdProductMapper;

    public CreateProductCommandHandler(ProductRepository productRepository, CreateProductMapper createdProductMapper) {
        this.productRepository = productRepository;
        this.createdProductMapper = createdProductMapper;
    }

    @Override
    public CreatedProductResponse handle(CreateProductCommand command) {
        Product product = createdProductMapper.toDomain(command);

        product = productRepository.save(product);

        return createdProductMapper.toResponse(product);
    }
}
