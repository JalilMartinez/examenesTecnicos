package com.api.dataprocessor.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
//antes tenia extends Filter, funcionaba, pero al investigar, vi que OnceperRequest es mejor
// con este, spring asegura que, en caso de llamadas multiples, solo se ejecute una vez por peticion, evitando que el id se mueva o le pase cosas raras
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID_KEY = "correlationId";
    private static final String CORRELATION_HEADER = "X-Correlation-ID";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // 1. Generamos el ID único
            String correlationId = UUID.randomUUID().toString().substring(0, 8);

            // 2. Lo guardamos en el MDC para que aparezca en los logs [%X{correlationId}]
            MDC.put(CORRELATION_ID_KEY, correlationId);

            // 3. Lo añadimos al header de respuesta (muy útil para que el cliente reporte errores)
            response.setHeader(CORRELATION_HEADER, correlationId);

            // 4. Continuamos con el siguiente filtro (ahora sí, el de JWT tendrá el ID disponible)
            filterChain.doFilter(request, response);

        } finally {
            // 5. Limpiamos el MDC. Esto es CRÍTICO en servidores como Tomcat
            // porque los hilos se reutilizan para diferentes usuarios.
            MDC.remove(CORRELATION_ID_KEY);
        }
    }
}