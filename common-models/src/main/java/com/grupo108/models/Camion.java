package com.grupo108.models;

import com.grupo108.models.enums.TipoCamion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String patente;

    private Double capacidadPeso;
    private Double capacidadVolumen;
    private Double consumoCombustiblePromedio;
    private Double costoBaseTrasladoKm;
    private Boolean disponibilidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCamion tipoCamion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportista_id", nullable = false)
    private Transportista transportista;

    public Camion(String patente, Transportista transportista, Double capacidadPeso, Double capacidadVolumen, Double consumoCombustiblePromedio, Double costoBaseTrasladoKm, TipoCamion tipoCamion, Boolean disponibilidad) {
        this.patente = patente;
        this.transportista = transportista;
        this.capacidadPeso = capacidadPeso;
        this.capacidadVolumen = capacidadVolumen;
        this.consumoCombustiblePromedio = consumoCombustiblePromedio;
        this.costoBaseTrasladoKm = costoBaseTrasladoKm;
        this.tipoCamion = tipoCamion;
        this.disponibilidad = disponibilidad;
    }
}
