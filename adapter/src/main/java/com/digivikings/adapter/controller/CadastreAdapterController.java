package com.digivikings.adapter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cadastre")
public class CadastreAdapterController {

    private final WebClient webClient;
    private final String mode;
    @Value("${adapter.provider-base-url}")
    private final String providerBaseUrl;

    public CadastreAdapterController(
            WebClient webClient,
            @Value("${adapter.mode}") String mode,
            @Value("${adapter.provider-base-url}") String providerBaseUrl
    ) {
        this.webClient = webClient;
        this.mode = mode;
        this.providerBaseUrl = providerBaseUrl;
    }

    @GetMapping("/ehitis-andmed")
    public Mono<ResponseEntity<String>> getEhitiseAndmed(
            @RequestParam String cadastralId,
            @RequestParam(defaultValue = "101110010100") String dataVector
    ) {
        if (!"DIRECT".equalsIgnoreCase(mode)) {
            return Mono.just(ResponseEntity.status(501).body("XROAD mode not enabled yet"));
        }

        // DIRECT: вызываем provider-service напрямую
        return webClient.get()
                .uri(providerBaseUrl + "/api/v1/ehitis-andmed?cadastralId={id}&dataVector={dv}",
                        cadastralId, dataVector)
                .retrieve()
                .toEntity(String.class);
    }
}
