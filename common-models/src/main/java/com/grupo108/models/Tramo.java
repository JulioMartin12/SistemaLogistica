package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String origen;
    private String destino;
    private Double costoAproximado;
    private Double costoReal;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camion_id", nullable = false)
    private Camion camion; // ¡Añadir este campo al código!

    // ManyToOne: FK ruta_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_tramo_id", nullable = false)
    private TipoTramo tipoTramo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_tramo_id", nullable = false)
    private EstadoTramo estadoTramo;
}