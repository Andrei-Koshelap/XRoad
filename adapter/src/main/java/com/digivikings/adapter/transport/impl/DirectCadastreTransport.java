package com.digivikings.adapter.transport.impl;

import com.digivikings.adapter.transport.CadastreTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@ConditionalOnProperty(name = "adapter.mode", havingValue = "DIRECT", matchIfMissing = true)
@RequiredArgsConstructor
class DirectCadastreTransport implements CadastreTransport {

    @Qualifier("xroadWebClient")
    private final WebClient webClient;


    @Value("${adapter.provider-base-url}")
    private String providerBaseUrl;

    @Override
    public Mono<String> getEhitiseAndmed(String cadastralId, String dataVector) {
        return webClient.get()
                .uri(providerBaseUrl + "/api/v1/ehitis-andmed?cadastralId={id}&dataVector={dv}",
                        cadastralId, dataVector)
                .retrieve()
                .bodyToMono(String.class);
    }
}