package com.grupo108.models;

import com.grupo108.models.enums.EstadoTramo;
import com.grupo108.models.enums.TipoTramo;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_id", nullable = false)
    private Geolocalizacion origen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", nullable = false)
    private Geolocalizacion destino;


    private Double costoAproximado;
    private Double costoReal;
    private Date fechaHoraInicioEstimada;
    private Date fechaHoraFinEstimada;
    private Date fechaHoraInicioReal;
    private Date fechaHoraFinReal;
    private Double distanciaKm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTramo tipoTramo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTramo estadoTramo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camion_dominio", nullable = false)
    private Camion camion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;


}