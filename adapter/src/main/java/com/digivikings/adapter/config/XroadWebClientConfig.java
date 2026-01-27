package com.digivikings.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class XroadWebClientConfig {

    @Bean
    public WebClient xroadWebClient(
            WebClient.Builder builder,
            @Value("${xroad.verify-tls:false}") boolean verifyTls
    ) {
        if (verifyTls) {
            return builder.build();
        }

        // NB dev/test! Insecure SSL context that trusts all certificates
        io.netty.handler.ssl.SslContext sslContext;
        try {
            sslContext = io.netty.handler.ssl.SslContextBuilder
                    .forClient()
                    .trustManager(io.netty.handler.ssl.util.InsecureTrustManagerFactory.INSTANCE)
                    .build();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to build insecure SSL context", e);
        }

        reactor.netty.http.client.HttpClient httpClient = reactor.netty.http.client.HttpClient.create()
                .secure(spec -> spec.sslContext(sslContext));

        return builder
                .clientConnector(new org.springframework.http.client.reactive.ReactorClientHttpConnector(httpClient))
                .build();
    }
}

