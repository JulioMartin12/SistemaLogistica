package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoTramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion; // varchar(50)

    // Relación OneToMany: Un tipo de tramo aplica a muchos Tramos
    @OneToMany(mappedBy = "tipoTramo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tramo> tramos;
}