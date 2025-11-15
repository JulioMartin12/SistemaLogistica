package com.grupo108.clientes.service;

import com.grupo108.clientes.repository.ClienteRepository;
import com.grupo108.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    // Inyección de dependencia del repositorio
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardarCliente(Cliente cliente) {
        // En un entorno real, aquí irían validaciones de email, etc.
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        clienteRepository.deleteById(id);
    }
}
