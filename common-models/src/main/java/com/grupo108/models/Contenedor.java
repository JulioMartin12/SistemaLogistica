package com.grupo108.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo108.models.enums.EstadoContenedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "contenedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenedor")
    private Integer idContenedor;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a cero")
    @Column(nullable = false)
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser mayor a cero")
    @Column(nullable = false)
    private Double volumen;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    @NotNull(message = "El estado del contenedor es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_contenedor", nullable = false)
    private EstadoContenedor estadoContenedor;

}