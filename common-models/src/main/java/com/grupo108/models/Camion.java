package com.grupo108.models;

import com.grupo108.models.enums.TipoCamion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "camiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camion")
    private Integer idCamion;

    @NotBlank(message = "La patente es obligatoria")
    @Pattern(regexp = "^[A-Z0-9-]{6,10}$", message = "La patente debe tener un formato válido (ej: AA123BB o 123XYZ)")
    @Column(nullable = false, unique = true, length = 10)
    private String patente;

    @NotNull(message = "La capacidad de peso es obligatoria")
    @Positive(message = "La capacidad de peso debe ser mayor a 0")
    @Column(name = "capacidad_peso", nullable = false)
    private Double capacidadPeso;

    @NotNull(message = "La capacidad de volumen es obligatoria")
    @Positive(message = "La capacidad de volumen debe ser mayor a 0")
    @Column(name = "capacidad_volumen", nullable = false)
    private Double capacidadVolumen;

    @NotNull(message = "El consumo es obligatorio para el cálculo de costos")
    @PositiveOrZero(message = "El consumo no puede ser negativo")
    @Column(name = "consumo_combustible_promedio", nullable = false)
    private Double consumoCombustiblePromedio;

    @NotNull(message = "El costo base por km es obligatorio")
    @PositiveOrZero(message = "El costo base no puede ser negativo")
    @Column(name = "costo_base_km", nullable = false)
    private Double costoBaseTrasladoKm;

    @NotNull(message = "La disponibilidad es obligatoria")
    @Column(nullable = false)
    private Boolean disponibilidad = true; // Por defecto disponible

    @NotNull(message = "El tipo de camión es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_camion", nullable = false)
    private TipoCamion tipoCamion;

    @ManyToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transportista", nullable = false)
    private Transportista transportista;

    public Camion(String patente, Transportista transportista, Double capacidadPeso,
                  Double capacidadVolumen, Double consumoCombustiblePromedio,
                  Double costoBaseTrasladoKm, TipoCamion tipoCamion, Boolean disponibilidad) {
        this.patente = patente;
        this.transportista = transportista;
        this.capacidadPeso = capacidadPeso;
        this.capacidadVolumen = capacidadVolumen;
        this.consumoCombustiblePromedio = consumoCombustiblePromedio;
        this.costoBaseTrasladoKm = costoBaseTrasladoKm;
        this.tipoCamion = tipoCamion;
        this.disponibilidad = disponibilidad;
    }

    public boolean esAptoParaCarga(Double pesoRequerido, Double volumenRequerido) {
        if (pesoRequerido == null || volumenRequerido == null) return false;
        return this.capacidadPeso >= pesoRequerido && this.capacidadVolumen >= volumenRequerido;
    }
}