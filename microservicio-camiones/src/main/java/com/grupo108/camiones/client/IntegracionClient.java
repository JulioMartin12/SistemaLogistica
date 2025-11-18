package com.grupo108.camiones.client;

import org.slf4j.Logger;        // 👈
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;

@Component
public class IntegracionClient {
    private static final Logger log = LoggerFactory.getLogger(IntegracionClient.class);
    private final WebClient webClient;

    private static final String INTEGRACION_URL = "http://microservicio-integracion:8080/api/v1/distancia";

    public IntegracionClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(INTEGRACION_URL).build();
    }

    public Double obtenerDistancia(double lat1, double lon1, double lat2, double lon2) {
        log.info("📞 Llamando a MS-Integracion para calcular distancia entre [{},{}] y [{},{}]", lat1, lon1, lat2, lon2);

        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("lat1", lat1)
                            .queryParam("lon1", lon1)
                            .queryParam("lat2", lat2)
                            .queryParam("lon2", lon2)
                            .build())
                    .retrieve()
                    .bodyToMono(Double.class)
                    .timeout(Duration.ofSeconds(5))
                    .onErrorResume(e -> {

                        log.error("Error al conectar con MS-Integracion: {}. Usando 0.0 como fallback.", e.getMessage());
                        return Mono.just(0.0);
                    })
                    .block();

        } catch (Exception e) {
            log.error("Excepción inesperada en WebClient: {}", e.getMessage());
            return 0.0;
        }
    }
}