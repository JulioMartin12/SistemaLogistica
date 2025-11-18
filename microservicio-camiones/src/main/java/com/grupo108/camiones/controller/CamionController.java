package com.grupo108.camiones.controller;

import com.grupo108.models.Camion;
import com.grupo108.camiones.service.CamionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/camiones")
@Slf4j
@Tag(name = "Gestión de Camiones", description = "Endpoints para administrar la flota y consultar disponibilidad")
public class CamionController {

    private final CamionService camionService;
    private static final Logger log = LoggerFactory.getLogger(CamionController.class);
    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo camión", description = "Guarda un camión validando patente única y capacidades positivas")
    public ResponseEntity<Camion> crearCamion(@Valid @RequestBody Camion camion) {
        log.info("Iniciando registro de camión con patente: {}", camion.getPatente());

        Camion nuevoCamion = camionService.guardarCamion(camion);

        log.info("Camión registrado con éxito. ID: {}", nuevoCamion.getIdCamion());
        return new ResponseEntity<>(nuevoCamion, HttpStatus.CREATED);
    }


    @GetMapping("/elegibles")
    @Operation(summary = "Buscar camiones aptos para carga", description = "Retorna camiones disponibles que soporten el peso y volumen solicitados")
    public ResponseEntity<List<Camion>> buscarCamionesElegibles(
            @RequestParam Double peso,
            @RequestParam Double volumen) {

        log.info("camiones aptos para Peso: {}kg, Volumen: {}m3", peso, volumen);

        List<Camion> elegibles = camionService.buscarElegibles(peso, volumen);

        if (elegibles.isEmpty()) {
            log.warn("No se encontraron camiones aptos para la carga solicitada.");
        } else {
            log.info("Se encontraron {} camiones aptos.", elegibles.size());
        }

        return new ResponseEntity<>(elegibles, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Listar toda la flota", description = "Devuelve todos los camiones registrados en el sistema")
    public ResponseEntity<List<Camion>> obtenerTodos() {
        log.info("Consultando listado completo de camiones");
        return new ResponseEntity<>(camionService.buscarTodos(), HttpStatus.OK);
    }
}