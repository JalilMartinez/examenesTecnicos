package com.api.gateway.filter;

import com.api.gateway.client.ValidationClient;
import com.api.gateway.util.BodyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RequestValidationGatewayFilter
        extends AbstractGatewayFilterFactory<RequestValidationGatewayFilter.Config> {

    private final ValidationClient validationClient;

    public RequestValidationGatewayFilter(ValidationClient validationClient) {
        super(Config.class);
        this.validationClient = validationClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->

                BodyUtils.extractBody(exchange.getRequest())
                        .flatMap(body -> {

                            if (body.length == 0) {
                                log.debug("Empty body, skipping validation");
                                return chain.filter(exchange);
                            }

                            return validationClient.validate(body)
                                    .then(forwardRequest(exchange, chain, body))
                                    .onErrorResume(e -> handleError(exchange, e));
                        });
    }

    private Mono<Void> forwardRequest(
            org.springframework.web.server.ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain,
            byte[] body
    ) {
        var mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {

            @Override
            public Flux<DataBuffer> getBody() {
                return Flux.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(body));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(super.getHeaders());
                headers.setContentLength(body.length);
                return headers;
            }
        };

        log.debug("Forwarding request after validation");

        return chain.filter(exchange.mutate()
                .request(mutatedRequest)
                .build());
    }

    private Mono<Void> handleError(
            org.springframework.web.server.ServerWebExchange exchange,
            Throwable e
    ) {

        log.error("Error during validation flow", e);

        if (e instanceof org.springframework.web.reactive.function.client.WebClientResponseException ex) {

            exchange.getResponse().setStatusCode(ex.getStatusCode());
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            byte[] errorBytes = ex.getResponseBodyAsByteArray();
            DataBuffer buffer = exchange.getResponse()
                    .bufferFactory()
                    .wrap(errorBytes);

            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
    }
}