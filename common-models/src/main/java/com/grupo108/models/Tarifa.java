package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double costoKm;
    private Double costoCombustible;
    private Double costoEstadia;

    @OneToOne(mappedBy = "tarifa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Camion camion;
}
