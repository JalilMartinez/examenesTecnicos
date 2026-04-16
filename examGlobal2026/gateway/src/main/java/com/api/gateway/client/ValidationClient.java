package com.api.gateway.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
public class ValidationClient {

    private final WebClient validationWebClient;
    private final String endpoint;

    public ValidationClient( WebClient validationWebClient, @Value("${validation.service.endpoint}") String endpoint){
        this.validationWebClient = validationWebClient;
        this.endpoint = endpoint;
    }
    public Mono<Void> validate(byte[] body) {
        log.debug("aqui");
        return validationWebClient.post()
                .uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .timeout(Duration.ofSeconds(3))
                .doOnSuccess(r -> log.debug("Validation success ajskdhfklsdfjsd"))
                .doOnError(e -> log.error("Validation error", e))
                .then();
    }
}