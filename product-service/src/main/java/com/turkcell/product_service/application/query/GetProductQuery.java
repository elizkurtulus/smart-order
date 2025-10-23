package com.turkcell.product_service.application.query;

import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.application.dto.ProductDto;
import com.turkcell.product_service.core.cqrs.Query;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GetProductQuery (
    @NotNull @Positive ProductId id
) implements Query<ProductDto> {}
