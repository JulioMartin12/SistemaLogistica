package com.grupo108.camiones.repository;

import com.grupo108.models.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Integer> {

    @Query("SELECT c FROM Camion c WHERE c.disponibilidad = true AND c.capacidadPeso >= :peso AND c.capacidadVolumen >= :volumen")
    List<Camion> findCamionesAptos(@Param("peso") Double peso, @Param("volumen") Double volumen);
}