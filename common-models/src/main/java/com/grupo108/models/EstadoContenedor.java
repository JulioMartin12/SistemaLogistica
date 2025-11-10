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
public class EstadoContenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;

    // Relación OneToMany: Un estado aplica a muchos contenedores
    @OneToMany(mappedBy = "estadoContenedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contenedor> contenedores;
}
