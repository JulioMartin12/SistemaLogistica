package com.grupo108.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;

    private String nroCalle;
    private String piso;
    private String dpto;
    private String codigoPostal;

    // ManyToOne: FK id_cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // ManyToOne: FK id_calle
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_calle", nullable = false)
    private Calle calle;

    // OneToOne: Mapeo de Geolocalizacion
    @OneToOne(mappedBy = "direccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Geolocalizacion geolocalizacion;

    @OneToOne(mappedBy = "direccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Deposito deposito;
}