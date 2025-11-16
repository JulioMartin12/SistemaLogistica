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

    // El Microservicio Camiones llama a este método
    public double getDistanceInMeters(double originLat, double originLon, double destLat, double destLon) {

        try {
            // 💡 Esta es la implementación de consumo real de la API OSRM
            String coordinates = originLon + "," + originLat + ";" + destLon + "," + destLat;

            String responseBody = webClient.get()
                    .uri(OSRM_URL + coordinates + "?overview=false")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // 💡 Aquí iría el parser del JSON para obtener la distancia
            // Por ahora, usamos simulación para que el MS Camiones no falle.
            return 500000.0; // Distancia simulada (500 km)

        } catch (Exception e) {
            System.err.println("Fallo al conectar OSRM. Usando fallback de distancia simulada.");
            return 500000.0;
        }
    }
}