package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "rutas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Integer idRuta;

    @NotNull(message = "La fecha de asignación es obligatoria")
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    @Min(value = 0, message = "La cantidad de tramos no puede ser negativa")
    @Column(name = "cantidad_tramos")
    private Integer cantidadTramos = 0;

    @Min(value = 0, message = "La cantidad de depósitos no puede ser negativa")
    @Column(name = "cantidad_depositos")
    private Integer cantidadDepositos = 0;

    @NotNull(message = "La solicitud asociada es obligatoria")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_solicitud", nullable = false, unique = true)
    private Solicitud solicitud;

    @OneToMany(mappedBy = "ruta", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Tramo> tramos = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void actualizarContadores() {
        if (this.tramos != null) {
            this.cantidadTramos = this.tramos.size();
           }
    }
}