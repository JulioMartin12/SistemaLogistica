package com.grupo108.logistica.client;

import com.grupo108.models.dtos.GeolocalizacionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-integracion", url = "http://microservicio-integracion:8080")
public interface GeocodingClient {

    @GetMapping("/api/v1/geocoding/coordenadas")
    GeolocalizacionDto obtenerCoordenadas(@RequestParam("direccion") String direccion);
}