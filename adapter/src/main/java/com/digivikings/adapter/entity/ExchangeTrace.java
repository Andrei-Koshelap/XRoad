package com.digivikings.adapter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "exchange_trace")
@Getter
@Setter
public class ExchangeTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "correlation_id", nullable = false, unique = true, length = 64)
    private String correlationId;

    @Column(name = "xroad_client", nullable = false, length = 200)
    private String xroadClient;

    @Column(name = "xroad_service", nullable = false, length = 200)
    private String xroadService;

    @Column(name = "request_ts", nullable = false)
    private Instant requestTs;

    @Column(name = "response_ts")
    private Instant responseTs;

    @Column(name = "http_status")
    private Integer httpStatus;

    @Column(name = "result", nullable = false, length = 16)
    private String result; // OK / ERROR

    @Column(name = "error_code", length = 64)
    private String errorCode;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;
}
