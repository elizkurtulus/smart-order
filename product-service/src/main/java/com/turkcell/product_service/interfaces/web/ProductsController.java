package com.turkcell.product_service.interfaces.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.product_service.core.cqrs.CommandHandler;
import com.turkcell.product_service.core.cqrs.QueryHandler;
import com.turkcell.product_service.application.query.GetProductQuery;
import com.turkcell.product_service.application.command.CreateProductCommand;
import com.turkcell.product_service.application.dto.ProductDto;
import com.turkcell.product_service.application.dto.CreatedProductResponse;
import com.turkcell.product_service.application.command.DeleteProductCommand;
import com.turkcell.product_service.application.command.UpdateProductCommand;
import com.turkcell.product_service.application.dto.UpdatedProductResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductsController {

    private final QueryHandler<GetProductQuery, ProductDto> getProductQueryHandler;
    //private final QueryHandler<GetProductQuery, ProductDto> getProductQueryHandler;
    private final CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler;
    private final CommandHandler<UpdateProductCommand, UpdatedProductResponse> updateProductCommandHandler;
    private final CommandHandler<DeleteProductCommand, Void> deleteProductCommandHandler;

    public ProductsController(CommandHandler<CreateProductCommand, CreatedProductResponse> createProductCommandHandler,
            CommandHandler<DeleteProductCommand, Void> deleteProductCommandHandler,  QueryHandler<GetProductQuery, ProductDto> getProductQueryHandler, 
            CommandHandler<UpdateProductCommand, UpdatedProductResponse> updateProductCommandHandler) {
        this.createProductCommandHandler = createProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
        this.getProductQueryHandler = getProductQueryHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@Valid @RequestParam("id") GetProductQuery query) {
        return getProductQueryHandler.handle(query);
    }

    @GetMapping("/all")
    public String getProducts() {
        return "Product";
    }

    @PostMapping
    public CreatedProductResponse createProduct(@Valid @RequestBody CreateProductCommand command) {
        return createProductCommandHandler.handle(command);
    }

    @PutMapping
    public UpdatedProductResponse updateProduct(@Valid @RequestBody UpdateProductCommand command) {
        return updateProductCommandHandler.handle(command);
    }

    @DeleteMapping("/{id}")
    public Void deleteProduct(@Valid @RequestParam("id") DeleteProductCommand command) {
        return deleteProductCommandHandler.handle(command);
    }
}
