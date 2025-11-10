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
    private Double latitud; // numeric(10, 8)
    private Double longitud; // numeric(11, 8)
    private Double costoEstadiaDiario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion", unique = true, nullable = false)
    private Direccion direccion;
}
