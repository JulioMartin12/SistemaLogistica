package com.grupo108.models.dtos;

import com.grupo108.models.enums.EstadoContenedor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorDto {

    private Integer idContenedor;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser positivo")
    private Double volumen;

    private EstadoContenedor estado;

    private Integer idCliente;
}