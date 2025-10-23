package com.turkcell.product_service.application.command;

import java.math.BigDecimal;
import com.turkcell.product_service.application.dto.UpdatedProductResponse;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductCommand(
    @NotNull @Positive ProductId id,
    @NotBlank @Size(min = 3, max = 100) String name,
    @NotBlank @Size(min = 3, max = 255) String description,
    @NotNull @Positive BigDecimal amount,
    @NotNull @NotBlank String code,
    @NotNull @NotBlank String currencyName,
    @NotNull @NotBlank String symbol,
    @NotNull @Positive int quantity
) implements Command<UpdatedProductResponse> {}
