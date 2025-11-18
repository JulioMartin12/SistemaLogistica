package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transportistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportista")
    private Integer idTransportista;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "El teléfono debe contener solo números y caracteres válidos")
    @Column(nullable = false, length = 25)
    private String telefono;

    @Column(nullable = false)
    private Boolean disponibilidad = true;

    @OneToMany(mappedBy = "transportista", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Camion> camiones = new ArrayList<>();

    public Transportista(String nombre, String apellido, String telefono, Boolean disponibilidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.disponibilidad = disponibilidad;
    }
}