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
@Table(name = "depositos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposito")
    private Integer idDeposito;

    @NotBlank(message = "El nombre del depósito es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "El costo de estadía diario es obligatorio")
    @PositiveOrZero(message = "El costo de estadía no puede ser negativo")
    @Column(name = "costo_estadia_diario", nullable = false)
    private Double costoEstadiaDiario;

    @NotNull(message = "La ubicación es obligatoria")
    @OneToOne(cascade = CascadeType.ALL) // Si borramos el depósito, borramos su geo asociada
    @JoinColumn(name = "id_geolocalizacion", referencedColumnName = "id_geolocalizacion", nullable = false)
    private Geolocalizacion ubicacion;

}