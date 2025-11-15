package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geolocalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGeolocalizacion;

    private Double latitud;
    private Double longitud;
    private String direccion;

}