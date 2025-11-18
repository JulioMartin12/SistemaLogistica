package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo108.models.enums.EstadoSolicitud;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_solicitud")
    private Integer numeroSolicitud;

    @PositiveOrZero(message = "El costo estimado no puede ser negativo")
    @Column(name = "costo_estimado")
    private Double costoEstimado;

    @PositiveOrZero(message = "El costo real no puede ser negativo")
    @Column(name = "costo_real")
    private Double costoReal;

    @Column(name = "tiempo_estimado_entrega")
    private LocalDateTime tiempoEstimado;

    @Column(name = "tiempo_real_entrega")
    private LocalDateTime tiempoReal;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado = EstadoSolicitud.BORRADOR;

    @NotNull(message = "El contenedor es obligatorio")
    @OneToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "id_contenedor", unique = true, nullable = false)
    private Contenedor contenedor;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Ruta ruta;
}