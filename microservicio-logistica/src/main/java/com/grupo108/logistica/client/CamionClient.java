package com.grupo108.logistica.client;

import com.grupo108.models.Camion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-camiones", url = "http://microservicio-camiones:8080")
public interface CamionClient {

    // Método que llama al TarifaController en el MS Camiones
    @GetMapping("/api/v1/tarifas/estimar")
    Double calcularCostoEstimado(
            @RequestParam("peso") Double peso,
            @RequestParam("volumen") Double volumen,
            @RequestParam("latOrigen") double latOrigen,
            @RequestParam("lonOrigen") double lonOrigen,
            @RequestParam("latDestino") double latDestino,
            @RequestParam("lonDestino") double lonDestino);

    @GetMapping("/api/v1/camiones/elegibles")
    List<Camion> buscarElegibles(
            @RequestParam("peso") Double peso,
            @RequestParam("volumen") Double volumen);
}