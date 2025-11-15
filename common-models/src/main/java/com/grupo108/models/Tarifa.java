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

    // El valor del litro de combustible configurado para el cálculo general
    private Double valorLitroCombustible;

    // Cargo fijo por gestión, que depende de la cantidad de tramos.
    private Double cargoGestionBase;

    // Valor base de costo por kilómetro (Puede ser usado para rangos de volumen,
    // o como un valor inicial para el cálculo aproximado).
    private Double costoKmBase;

    private String descripcion;

    // Opcional: Rangos de Volumen/Peso (Si se decide implementar el esquema por rangos)
    private Double volumenMinimo;
    private Double volumenMaximo;

    public Tarifa( Double valorLitroCombustible, Double cargoGestionBase, Double costoKmBase, String descripcion, Double volumenMinimo, Double volumenMaximo) {
        this.valorLitroCombustible = valorLitroCombustible;
        this.cargoGestionBase = cargoGestionBase;
        this.costoKmBase = costoKmBase;
        this.descripcion = descripcion;
        this.volumenMinimo = volumenMinimo;
        this.volumenMaximo = volumenMaximo;
    }
}