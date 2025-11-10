package com.grupo108.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;

    @OneToMany(mappedBy = "estadoTramo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tramo> tramos;
}