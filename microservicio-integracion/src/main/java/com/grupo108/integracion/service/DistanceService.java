package com.grupo108.integracion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

// URL interna de OSRM en Docker Compose
@Service
public class DistanceService {

    private final WebClient webClient;
    private static final String OSRM_URL = "http://osrm:5000/route/v1/driving/";

    public DistanceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public double getDistanceInMeters(double originLat, double originLon, double destLat, double destLon) {

        try {
            String coordinates = originLon + "," + originLat + ";" + destLon + "," + destLat;

            String responseBody = webClient.get()
                    .uri(OSRM_URL + coordinates + "?overview=false")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return 500000.0;

        } catch (Exception e) {
            System.err.println("Fallo al conectar OSRM. Usando fallback de distancia simulada.");
            return 500000.0;
        }
    }
}