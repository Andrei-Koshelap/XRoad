package com.digivikings.adapter.transport.impl;

import com.digivikings.adapter.transport.CadastreTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@ConditionalOnProperty(name = "adapter.mode", havingValue = "XROAD")
@RequiredArgsConstructor
class XroadCadastreTransport implements CadastreTransport {

    private final WebClient xroadWebClient;

    @Value("${xroad.ss-url}")
    private String ssUrl;
    @Value("${xroad.client-id-url}")
    String clientIdUrl;
    @Value("${xroad.service-id-url}")
    String serviceIdUrl;

    @Override
    public Mono<String> getEhitiseAndmed(String cadastralId, String dataVector) {
        String soap = XroadSoapTemplates.ehitiseAndmeteParing(cadastralId, dataVector);


        return xroadWebClient.post()
                .uri(ssUrl)
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("Accept", "text/xml")
                // .header("SOAPAction", "ehitiseAndmeteParing")
                .bodyValue(soap)
                .retrieve()
                .bodyToMono(String.class);
    }
}
