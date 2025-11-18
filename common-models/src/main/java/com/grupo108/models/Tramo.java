package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo108.models.enums.EstadoTramo;
import com.grupo108.models.enums.TipoTramo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tramos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramo")
    private Integer idTramo;

    @NotNull(message = "El origen es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_origen", nullable = false)
    private Geolocalizacion origen;

    @NotNull(message = "El destino es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_destino", nullable = false)
    private Geolocalizacion destino;

    @PositiveOrZero(message = "La distancia no puede ser negativa")
    @Column(name = "distancia_km")
    private Double distanciaKm;

    @PositiveOrZero
    @Column(name = "costo_aproximado")
    private Double costoAproximado;

    @PositiveOrZero
    @Column(name = "costo_real")
    private Double costoReal;

    @Column(name = "fecha_hora_inicio_estimada")
    private LocalDateTime fechaHoraInicioEstimada;

    @Column(name = "fecha_hora_fin_estimada")
    private LocalDateTime fechaHoraFinEstimada;

    @Column(name = "fecha_hora_inicio_real")
    private LocalDateTime fechaHoraInicioReal;

    @Column(name = "fecha_hora_fin_real")
    private LocalDateTime fechaHoraFinReal;

    @NotNull(message = "El tipo de tramo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tramo", nullable = false)
    private TipoTramo tipoTramo;

    @NotNull(message = "El estado del tramo es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tramo", nullable = false)
    private EstadoTramo estadoTramo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_camion")
    private Camion camion;

    @NotNull(message = "La ruta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;
}