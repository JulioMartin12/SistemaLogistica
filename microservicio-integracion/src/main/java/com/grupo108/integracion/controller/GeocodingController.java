package com.grupo108.integracion.controller;

import com.grupo108.integracion.service.GeocodingService;
import com.grupo108.models.dtos.GeolocalizacionDto;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/geocoding")
public class GeocodingController {

    private static final Logger log = LoggerFactory.getLogger(GeocodingController.class);

    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping("/coordenadas")
    @Operation(summary = "Geocodificación", description = "Convierte una dirección textual a coordenadas (Lat/Lon)")
    public GeolocalizacionDto obtenerCoordenadas(@RequestParam("direccion") String direccion) {
        log.info("Solicitud de geocodificación para: {}", direccion);
        return geocodingService.convertirDireccion(direccion);
    }
}