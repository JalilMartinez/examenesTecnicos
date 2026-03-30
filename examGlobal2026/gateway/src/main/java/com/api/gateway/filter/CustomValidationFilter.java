package com.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

@Component
public class CustomValidationFilter extends AbstractGatewayFilterFactory<CustomValidationFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public CustomValidationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    // 1. Si no hay body (ej. GET), inyectamos un buffer de 0 bytes para que no se corte el flujo
                    .defaultIfEmpty(exchange.getResponse().bufferFactory().allocateBuffer(0))
                    .flatMap(dataBuffer -> {

                        // 2. Evaluamos si el buffer está vacío
                        if (dataBuffer.readableByteCount() == 0) {
                            DataBufferUtils.release(dataBuffer);
                            // No hay body, seguimos el flujo normal hacia el microservicio final sin validar
                            return chain.filter(exchange);
                        }

                        // 3. Leer y liberar el buffer
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        DataBufferUtils.release(dataBuffer);

                        // 4. Llamada al Validador
                        return webClientBuilder.build()
                                .post()
                                .uri("http://localhost:8082/validate")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .bodyValue(bytes)
                                .retrieve()
                                .toBodilessEntity()
                                .flatMap(response -> {
                                    // CASO ÉXITO: Decoramos y seguimos al micro 8081
                                    ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                                        @Override
                                        public Flux<DataBuffer> getBody() {
                                            return Flux.just(exchange.getResponse().bufferFactory().wrap(bytes));
                                        }
                                    };
                                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                                })
                                .onErrorResume(WebClientResponseException.class, e -> {
                                    // CASO ERROR VALIDADOR: Escribimos error y CORTAMOS EL FLUJO
                                    // (Al devolver writeWith, NUNCA se llama a chain.filter)
                                    exchange.getResponse().setStatusCode(e.getStatusCode());
                                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

                                    byte[] errorBytes = e.getResponseBodyAsByteArray();
                                    DataBuffer db = exchange.getResponse().bufferFactory().wrap(errorBytes);

                                    return exchange.getResponse().writeWith(Flux.just(db));
                                })
                                .onErrorResume(e -> {
                                    // CASO ERROR CONEXIÓN u otros
                                    exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                                    return exchange.getResponse().setComplete();
                                });
                    });
            // ¡ELIMINADO el switchIfEmpty problemático!
        };
    }

    public static class Config { }
}