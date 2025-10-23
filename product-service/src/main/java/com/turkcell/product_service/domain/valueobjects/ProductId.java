package com.turkcell.product_service.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;
/**
* ProductId Value Object - Ürün kimliği
*/
public final class ProductId {
    private final UUID value;

    private ProductId(UUID value) {
        this.value = value;
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }

    public static ProductId fromString(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Ürün ID'si null veya boş olamaz");
        }
        try {
            return new ProductId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Geçersiz ürün ID formatı: " + id);
        }
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
