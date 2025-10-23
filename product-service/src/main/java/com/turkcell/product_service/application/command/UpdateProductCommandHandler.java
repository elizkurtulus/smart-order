package com.turkcell.product_service.application.command;

import org.springframework.stereotype.Component;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.application.mapper.UpdateProductMapper;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import java.util.Optional;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand, UpdatedProductResponse>{
    private final ProductRepository productRepository;
    private final UpdateProductMapper updateProductMapper;

    public UpdateProductCommandHandler(ProductRepository productRepository, UpdateProductMapper updateProductMapper) {
        this.productRepository = productRepository;
        this.updateProductMapper = updateProductMapper;
    }

    @Override
    public UpdatedProductResponse handle(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.id());
        Product product = optionalProduct.orElseThrow(
            () -> new RuntimeException("Product not found with id: " + command.id()));

        Product updatedProduct = productRepository.save(product);
        
        return updateProductMapper.toResponse(updatedProduct);
    }
} 