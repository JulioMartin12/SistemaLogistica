package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barrio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBarrio;

    private String nombre;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", nullable = false)
    private Ciudad ciudad;


    @OneToMany(mappedBy = "barrio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Calle> calles;
}
