package com.digivikings.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class XRoadGateway {

    private final WebClient xRoadWebClient;

    @Value("${xroad.client}") private String xRoadClient;
    @Value("${xroad.service}") private String xRoadService;

    public Mono<ResponseEntity<String>> getParcel(String cadastralId, String correlationId) {
        //  путь до REST-service через SS зависит от того,
        // как ты зарегистрируешь service description в SS.
        // "xroadPath".
        String xroadPath = "/r1/" + "cadastre/getParcel/v1"; // example path

        return xRoadWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(xroadPath)
                        .queryParam("cadastralId", cadastralId)
                        .build())
                .header("X-Road-Client", xRoadClient)
                .header("X-Road-Service", xRoadService)
                .header("X-Road-Id", correlationId)
                // need user or nick/hash
                //.header("X-Road-UserId", "user:" + userHash)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);
    }
}
