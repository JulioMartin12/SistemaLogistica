package com.grupo108.contenedores.repository;

import com.grupo108.models.enums.EstadoContenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoContenedorRepository extends JpaRepository<EstadoContenedor, Long> {
}
