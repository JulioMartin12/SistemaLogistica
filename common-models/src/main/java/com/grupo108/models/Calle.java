package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCalle;

    private String nombre; // varchar(150)

    // ManyToOne: FK id_barrio está en esta tabla
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barrio", nullable = false)
    private Barrio barrio;
}
