package com.turkcell.order_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
