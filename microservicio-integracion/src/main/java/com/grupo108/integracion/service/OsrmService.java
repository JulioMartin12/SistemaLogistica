package com.grupo108.integracion.service;

import com.grupo108.integracion.dto.OsrmResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class OsrmService {

    private final RestTemplate restTemplate;

    private static final String OSRM_BASE_URL = "http://osrm:5000/route/v1/driving/";

    public OsrmService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double calcularDistanciaMetros(double lat1, double lon1, double lat2, double lon2) {
        String coordenadas = String.format("%s,%s;%s,%s", lon1, lat1, lon2, lat2);

        String url = UriComponentsBuilder.fromHttpUrl(OSRM_BASE_URL + coordenadas)
                .queryParam("overview", "false")
                .toUriString();

        log.info("🗺️ Consultando OSRM: {}", url);

        try {
            OsrmResponseDto response = restTemplate.getForObject(url, OsrmResponseDto.class);

            if (response != null && !response.getRoutes().isEmpty()) {
                Double distancia = response.getRoutes().get(0).getDistance();
                log.info("Distancia obtenida: {} metros", distancia);
                return distancia;
            }
        } catch (Exception e) {
            log.error("Error conectando con OSRM: {}", e.getMessage());
        }

        return 0.0;
    }
}