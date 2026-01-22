package com.digivikings.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class XRoadClientConfig {

    @Bean
    WebClient xRoadWebClient(
            @Value("${xroad.ss-url}") String ssUrl
    ) {
        return WebClient.builder()
                .baseUrl(ssUrl)
                .build();
    }
}