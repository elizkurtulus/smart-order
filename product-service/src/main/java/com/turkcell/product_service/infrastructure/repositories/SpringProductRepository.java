package com.turkcell.product_service.infrastructure.repositories;

import com.turkcell.product_service.infrastructure.entities.JpaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringProductRepository extends JpaRepository<JpaProductEntity, UUID>
{
    boolean existsByNameIgnoreCase(String name);
}
