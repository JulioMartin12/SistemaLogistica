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
        private int id;

        private String nombre;
        private String apellido;
        private String telefono;
        private boolean disponibilidad;

        @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Camion> camiones;

    public Transportista(String nombre, String apellido, String telefono, boolean disponibilidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.disponibilidad = disponibilidad;
    }
}
