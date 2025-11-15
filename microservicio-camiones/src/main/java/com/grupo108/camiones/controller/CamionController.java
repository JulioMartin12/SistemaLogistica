package com.grupo108.camiones.controller;

import com.grupo108.models.Camion;
import com.grupo108.camiones.service.CamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/camiones")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @PostMapping
    public ResponseEntity<Camion> crearCamion(@RequestBody Camion camion) {
        Camion nuevoCamion = camionService.guardarCamion(camion);
        return new ResponseEntity<>(nuevoCamion, HttpStatus.CREATED);
    }

    // Endpoint para buscar camiones elegibles para un envío (Regla de Negocio)
    @GetMapping("/elegibles")
    public ResponseEntity<List<Camion>> buscarCamionesElegibles(
            @RequestParam Double peso,
            @RequestParam Double volumen) {

        List<Camion> elegibles = camionService.buscarElegibles(peso, volumen);
        return new ResponseEntity<>(elegibles, HttpStatus.OK);
    }

    @GetMapping // Obtener todos
    public ResponseEntity<List<Camion>> obtenerTodos() {
        return new ResponseEntity<>(camionService.buscarTodos(), HttpStatus.OK);
    }
}