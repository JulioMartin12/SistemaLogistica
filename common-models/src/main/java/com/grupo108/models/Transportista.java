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
    public class Transportista {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id; // id_transportista

        private String nombre; // varchar(150)
        private String apellido; // varchar(150)
        private String telefono; // varchar(50)

        // Relación OneToMany: Un transportista puede conducir muchos camiones (a lo largo del tiempo)
        @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Camion> camiones;
    }
