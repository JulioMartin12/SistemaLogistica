package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "geolocalizaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geolocalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_geolocalizacion")
    private Integer idGeolocalizacion;

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "La latitud no puede ser menor a -90")
    @DecimalMax(value = "90.0", message = "La latitud no puede ser mayor a 90")
    @Column(nullable = false)
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "La longitud no puede ser menor a -180")
    @DecimalMax(value = "180.0", message = "La longitud no puede ser mayor a 180")
    @Column(nullable = false)
    private Double longitud;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    @Column(nullable = false, length = 255)
    private String direccion;

    public String getCoordenadasString() {
        return this.latitud + "," + this.longitud;
    }
}