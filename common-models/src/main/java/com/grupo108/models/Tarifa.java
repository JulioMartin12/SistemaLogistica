package com.grupo108.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa")
    private Integer idTarifa;

    @NotBlank(message = "La descripción es obligatoria para identificar la tarifa")
    @Size(max = 100, message = "La descripción no puede superar los 100 caracteres")
    private String descripcion;

    @NotNull(message = "El valor del litro de combustible es obligatorio")
    @PositiveOrZero(message = "El valor del combustible no puede ser negativo")
    @Column(name = "valor_litro_combustible", nullable = false)
    private Double valorLitroCombustible;

    @NotNull(message = "El cargo de gestión base es obligatorio")
    @PositiveOrZero
    @Column(name = "cargo_gestion_base", nullable = false)
    private Double cargoGestionBase;

    @NotNull(message = "El costo por km base es obligatorio")
    @PositiveOrZero
    @Column(name = "costo_km_base", nullable = false)
    private Double costoKmBase;

    @NotNull(message = "El volumen mínimo es obligatorio para definir el rango")
    @PositiveOrZero
    @Column(name = "volumen_minimo")
    private Double volumenMinimo;

    @Column(name = "volumen_maximo")
    private Double volumenMaximo;

    public Tarifa(Double valorLitroCombustible, Double cargoGestionBase, Double costoKmBase, String descripcion, Double volumenMinimo, Double volumenMaximo) {
        this.valorLitroCombustible = valorLitroCombustible;
        this.cargoGestionBase = cargoGestionBase;
        this.costoKmBase = costoKmBase;
        this.descripcion = descripcion;
        this.volumenMinimo = volumenMinimo;
        this.volumenMaximo = volumenMaximo;
    }

    public boolean aplicaParaVolumen(Double volumenContenedor) {
        if (volumenContenedor == null) return false;
        if (volumenContenedor < volumenMinimo) return false;
        return volumenMaximo == null || volumenContenedor <= volumenMaximo;
    }
}