package com.grupo108.camiones.controller;

import com.grupo108.camiones.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tarifas")
@Tag(name = "Gestión de Tarifas", description = "Cálculo de costos de envío")
@Slf4j
public class TarifaController {
    private static final Logger log = LoggerFactory.getLogger(TarifaController.class);
    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    @GetMapping("/estimar")
    @Operation(summary = "Calcular costo estimado", description = "Calcula el costo aproximado basándose en promedios de la flota elegible")
    public ResponseEntity<Double> calcularCostoEstimado(
            @RequestParam("peso") Double peso,
            @RequestParam("volumen") Double volumen,
            @RequestParam("latOrigen") double latOrigen,
            @RequestParam("lonOrigen") double lonOrigen,
            @RequestParam("latDestino") double latDestino,
            @RequestParam("lonDestino") double lonDestino) {

        log.info("💰 Calculando estimación para carga de {}kg / {}m3", peso, volumen);

        Double costo = tarifaService.calcularCostoAproximado(peso, volumen, latOrigen, lonOrigen, latDestino, lonDestino);

        return ResponseEntity.ok(costo);
    }
}