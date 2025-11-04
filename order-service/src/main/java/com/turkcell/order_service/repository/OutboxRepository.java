package com.turkcell.order_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.order_service.entity.OutboxMessage;
import com.turkcell.order_service.enums.OutboxStatus;

public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
    List<OutboxMessage> findByStatusOrderByCreatedAtAsc(OutboxStatus status);
}
