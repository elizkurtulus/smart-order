package com.turkcell.product_service.application.command;

import com.turkcell.product_service.core.cqrs.Command;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DeleteProductCommand(
    @NotNull @Positive ProductId id
) implements Command<Void> {}
