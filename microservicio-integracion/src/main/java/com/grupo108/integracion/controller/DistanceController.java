package com.grupo108.integracion.controller;

import com.grupo108.integracion.service.OsrmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/distancia")
@Tag(name = "Integración Geográfica", description = "Cálculo de rutas y distancias")
public class DistanceController {

    private final OsrmService osrmService;

    public DistanceController(OsrmService osrmService) {
        this.osrmService = osrmService;
    }

    @GetMapping
    @Operation(summary = "Calcular distancia", description = "Obtiene la distancia en metros entre dos coordenadas usando OSRM")
    public ResponseEntity<Double> obtenerDistancia(
            @RequestParam("lat1") double lat1,
            @RequestParam("lon1") double lon1,
            @RequestParam("lat2") double lat2,
            @RequestParam("lon2") double lon2)
    {
        Double distanciaMetros = osrmService.calcularDistanciaMetros(lat1, lon1, lat2, lon2);
        return ResponseEntity.ok(distanciaMetros);
    }
}