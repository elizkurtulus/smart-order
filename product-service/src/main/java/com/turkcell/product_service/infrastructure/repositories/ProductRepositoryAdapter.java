package com.turkcell.product_service.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.infrastructure.entities.JpaProductEntity;
import com.turkcell.product_service.infrastructure.mappers.ProductEntityMapper;

@Component
public class ProductRepositoryAdapter implements ProductRepository{

    private final SpringProductRepository springProductRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryAdapter(ProductEntityMapper productEntityMapper, SpringProductRepository springProductRepository) {
        this.productEntityMapper = productEntityMapper;
        this.springProductRepository = springProductRepository;
    }

    @Override
    public Product save(Product product) {
        JpaProductEntity entity = productEntityMapper.toEntity(product);
        entity = springProductRepository.save(entity);
        return productEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return springProductRepository.findById(id.getValue()).map(productEntityMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return springProductRepository.findAll().stream().map(productEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(ProductId id) {
        springProductRepository.deleteById(id.getValue());
    }

    /* below methods were added additionally */
    @Override
    public List<Product> findByNameContaining(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNameContaining'");
    }

    @Override
    public List<Product> findInStockProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findInStockProducts'");
    }

    @Override
    public List<Product> findOutOfStockProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOutOfStockProducts'");
    }

    @Override
    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPriceRange'");
    }

    @Override
    public boolean existsById(ProductId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public long countInStockProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countInStockProducts'");
    }

}
