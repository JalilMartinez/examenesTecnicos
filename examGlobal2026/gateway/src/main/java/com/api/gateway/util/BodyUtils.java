package com.api.gateway.util;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

public class BodyUtils {

    private static final int MAX_SIZE = 1024 * 1024; // 1MB

    public static Mono<byte[]> extractBody(ServerHttpRequest request) {
        return DataBufferUtils.join(request.getBody())
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    if (bytes.length > MAX_SIZE) {
                        throw new IllegalStateException("Request body too large");
                    }

                    return bytes;
                })
                .defaultIfEmpty(new byte[0]);
    }
}