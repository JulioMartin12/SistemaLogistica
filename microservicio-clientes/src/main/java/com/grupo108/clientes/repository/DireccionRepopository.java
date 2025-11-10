package com.grupo108.clientes.repository;

import com.grupo108.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepopository extends JpaRepository<Direccion,Long> {
}
