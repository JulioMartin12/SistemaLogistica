package com.grupo108.camiones.repository;

import com.grupo108.models.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CamionRepository extends JpaRepository<Camion, Long> {

    List<Camion> findByDisponibilidadTrueAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
            Double pesoNecesario, Double volumenNecesario);
}