package com.digivikings.adapter.transport;

import reactor.core.publisher.Mono;

public interface CadastreTransport {
    Mono<String> getEhitiseAndmed(String cadastralId, String dataVector);
}
