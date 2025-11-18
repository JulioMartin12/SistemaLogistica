package com.grupo108.logistica.controller;

import com.grupo108.logistica.service.SolicitudService;
import com.grupo108.models.Solicitud;
import com.grupo108.models.dtos.SolicitudCreacionDto;
import com.grupo108.models.dtos.SolicitudResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Logística y Solicitudes", description = "Orquestación de pedidos de traslado")
public class SolicitudController {

    private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);
    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    @Operation(summary = "Registrar nueva solicitud", description = "Crea cliente, registra contenedor, calcula costo y genera la solicitud.")
    public ResponseEntity<SolicitudResponseDto> crearSolicitud(@Valid @RequestBody SolicitudCreacionDto solicitudDto) {

        log.info("Recibida nueva solicitud de transporte para cliente: {}", solicitudDto.getCliente().getEmail());
        Solicitud nuevaSolicitud = solicitudService.crearSolicitud(solicitudDto);

        SolicitudResponseDto responseDto = new SolicitudResponseDto();
        responseDto.setNumeroSolicitud(nuevaSolicitud.getNumeroSolicitud());
        responseDto.setEstado(nuevaSolicitud.getEstado());
        responseDto.setIdCliente(nuevaSolicitud.getCliente().getIdCliente());
        responseDto.setIdContenedor(nuevaSolicitud.getContenedor().getIdContenedor());

        responseDto.setCostoEstimado(nuevaSolicitud.getCostoEstimado());

        if (nuevaSolicitud.getRuta() != null) {
            responseDto.setFechaCreacion(nuevaSolicitud.getRuta().getFechaAsignacion());
        }

        if (nuevaSolicitud.getCostoEstimado() == null) {
            responseDto.setMensaje("Solicitud creada. Advertencia: Costeo y ruta inicial fallaron (Error de Geocodificación/Integración).");
        } else {
            responseDto.setMensaje("Solicitud creada y costeo inicial exitoso.");
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/asignar")
    @Operation(summary = "Asignar ruta y camión", description = "Busca un camión apto y lo asigna a una Solicitud en estado BORRADOR, cambiando el estado a PROGRAMADA.")
    public ResponseEntity<Solicitud> asignarRuta(@PathVariable Integer id) {
        log.info("🚛 Solicitud de asignación de ruta para ID: {}", id);

        Solicitud solicitudProgramada = solicitudService.asignarRuta(id);

        return ResponseEntity.ok(solicitudProgramada);
    }
}