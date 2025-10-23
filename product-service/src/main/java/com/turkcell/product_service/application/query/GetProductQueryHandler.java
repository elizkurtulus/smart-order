package com.turkcell.product_service.application.query;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.application.dto.ProductDto;
import com.turkcell.product_service.application.mapper.ProductResponseMapper;
import com.turkcell.product_service.core.cqrs.QueryHandler;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.entities.Product;
import java.util.Optional;

@Component
public class GetProductQueryHandler implements QueryHandler<GetProductQuery, ProductDto>{

    private final ProductRepository productRepository;
    private final ProductResponseMapper productResponseMapper;

    public GetProductQueryHandler(ProductResponseMapper productResponseMapper, ProductRepository productRepository) {
        this.productResponseMapper = productResponseMapper;
        this.productRepository = productRepository;
    }
    @Override
    public ProductDto handle(GetProductQuery query) {
        Optional<Product> optionalProduct =  productRepository.findById(query.id());
        Product product = optionalProduct.orElseThrow(
            () -> new RuntimeException("Product not found with id: " + query.id()));

        return productResponseMapper.toResponse(product);
    }

}
