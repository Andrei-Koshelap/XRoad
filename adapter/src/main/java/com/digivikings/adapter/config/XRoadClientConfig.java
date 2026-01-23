package com.digivikings.adapter.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
public class XRoadClientConfig {

    @Bean
    WebClient xRoadWebClient(
            @Value("${xroad.ss-url}") String ssUrl,
            @Value("${xroad.verify-tls:false}") boolean verifyTls
    ) {

        WebClient.Builder builder = WebClient.builder()
                .baseUrl(ssUrl);

        if (!verifyTls) {
            try {
                SslContext sslContext = SslContextBuilder
                        .forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                        .build();

                HttpClient httpClient = HttpClient.create()
                        .secure(sslSpec -> sslSpec.sslContext(sslContext));

                builder.clientConnector(
                        new ReactorClientHttpConnector(httpClient)
                );

            } catch (SSLException e) {
                throw new IllegalStateException("Failed to create insecure SSL context", e);
            }
        }

        return builder.build();
    }
}

