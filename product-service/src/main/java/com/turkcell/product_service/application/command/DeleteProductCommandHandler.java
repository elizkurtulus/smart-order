package com.turkcell.product_service.application.command;

import org.springframework.stereotype.Component;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.domain.entities.Product;
import java.util.Optional;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand, Void>{

    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(DeleteProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.id());
        optionalProduct.orElseThrow(() -> new RuntimeException("Product not found with id:" + command.id()));

        productRepository.deleteById(command.id());
        return null;
    }
}
