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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente; //

    // Datos personales y de contacto
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;


   // @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "ubicacion_id", referencedColumnName = "idGeolocalizacion")
    //private Geolocalizacion ubicacion;


    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Contenedor> contenedores;

    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Solicitud> solicitudes;
}