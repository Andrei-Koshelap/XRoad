package com.digivikings.adapter.controller;

import com.digivikings.adapter.transport.CadastreTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cadastre")
@RequiredArgsConstructor
public class CadastreAdapterController {

    private final CadastreTransport transport;

    @GetMapping("/ehitis-andmed")
    public Mono<ResponseEntity<String>> getEhitiseAndmed(
            @RequestParam String cadastralId,
            @RequestParam(defaultValue = "101110010100") String dataVector
    ) {
        return transport.getEhitiseAndmed(cadastralId, dataVector)
                .map(body -> ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(body));
    }
}
