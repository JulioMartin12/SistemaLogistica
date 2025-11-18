package com.grupo108.models.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeolocalizacionDto {

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin("-90.0") @DecimalMax("90.0")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin("-180.0") @DecimalMax("180.0")
    private Double longitud;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
}