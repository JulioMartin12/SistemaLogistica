package com.grupo108.camiones.repository;

import com.grupo108.models.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {


    @Query("SELECT t FROM Tarifa t WHERE :volumen >= t.volumenMinimo AND (t.volumenMaximo IS NULL OR :volumen <= t.volumenMaximo)")
    Tarifa findTarifaPorVolumen(@Param("volumen") Double volumen);
}