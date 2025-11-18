package com.grupo108.models.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCreacionDto {


    @NotNull(message = "Los datos del cliente son obligatorios")
    @Valid
    private ClienteDto cliente;

    @NotNull(message = "Los datos del contenedor son obligatorios")
    @Valid
    private ContenedorDto contenedor;

    @NotNull(message = "El origen es obligatorio")
    @Valid
    private GeolocalizacionDto origen;

    @NotNull(message = "El destino es obligatorio")
    @Valid
    private GeolocalizacionDto destino;
}