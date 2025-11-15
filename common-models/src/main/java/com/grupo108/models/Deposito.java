package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private Double costoEstadiaDiario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocalizacion_id", referencedColumnName = "idGeolocalizacion", nullable = false)
    private Geolocalizacion ubicacion;

}