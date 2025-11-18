package com.grupo108.clientes.repository;

import com.grupo108.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
