package com.grupo108.contenedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTramoRepository extends JpaRepository<EstadoTramoRepository, Long> {
}
