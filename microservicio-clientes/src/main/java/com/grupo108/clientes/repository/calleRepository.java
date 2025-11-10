package com.grupo108.clientes.repository;

import com.grupo108.models.Calle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface calleRepository extends JpaRepository<Calle, Long> {
}
