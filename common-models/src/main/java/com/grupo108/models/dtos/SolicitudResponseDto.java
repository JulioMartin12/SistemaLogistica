package com.grupo108.models.dtos;

import com.grupo108.models.enums.EstadoSolicitud;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SolicitudResponseDto {
    private Integer numeroSolicitud;
    private Double costoEstimado;
    private EstadoSolicitud estado;
    private Integer idCliente;
    private Integer idContenedor;
    private LocalDateTime fechaCreacion;
    private String mensaje;
}