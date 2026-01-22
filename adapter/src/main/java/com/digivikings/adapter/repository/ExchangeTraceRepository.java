package com.digivikings.adapter.repository;

import com.digivikings.adapter.entity.ExchangeTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExchangeTraceRepository extends JpaRepository<ExchangeTrace, UUID> {
    Optional<ExchangeTrace> findByCorrelationId(String correlationId);
}
