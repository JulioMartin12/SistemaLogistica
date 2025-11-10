package com.grupo108.clientes.repository;

import com.grupo108.models.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrioRepository extends JpaRepository<Barrio,Long> {
}
