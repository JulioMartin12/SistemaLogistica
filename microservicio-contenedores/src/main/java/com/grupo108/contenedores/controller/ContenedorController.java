package com.grupo108.contenedores.controller;

import com.grupo108.contenedores.service.ContenedorService;
import com.grupo108.models.Contenedor;
import com.grupo108.models.dtos.ContenedorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; // 👈 NECESARIO PARA VALIDAR
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contenedores")
// @Slf4j <-- ELIMINADO PARA EVITAR CONFLICTO CON EL LOGGER MANUAL
@Tag(name = "Gestión de Contenedores", description = "Operaciones sobre la carga física")
public class ContenedorController {

    private static final Logger log = LoggerFactory.getLogger(ContenedorController.class);
    private final ContenedorService contenedorService;

    public ContenedorController(ContenedorService contenedorService) {
        this.contenedorService = contenedorService;
    }

    @PostMapping
    @Operation(summary = "Registrar Contenedor", description = "Crea un nuevo contenedor físico asociado a un cliente")
    public ResponseEntity<Contenedor> crearContenedor(@Valid @RequestBody ContenedorDto contenedorDto) {
        log.info("📦 Recibiendo solicitud para crear contenedor de {}kg", contenedorDto.getPeso());

        Contenedor nuevo = contenedorService.crearDesdeDto(contenedorDto);

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}