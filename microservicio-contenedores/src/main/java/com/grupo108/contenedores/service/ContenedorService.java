package com.grupo108.contenedores.service;

import com.grupo108.models.Contenedor;
import com.grupo108.contenedores.repository.ContenedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenedorService {

    private final ContenedorRepository contenedorRepository;

    public ContenedorService(ContenedorRepository contenedorRepository) {
        this.contenedorRepository = contenedorRepository;
    }

    public Contenedor guardarContenedor(Contenedor contenedor) {
        // En un entorno real, aquí irían validaciones de unicidad.
        return contenedorRepository.save(contenedor);
    }

    public List<Contenedor> buscarTodos() {
        return contenedorRepository.findAll();
    }

    public Contenedor buscarPorId(Long id) {
        return contenedorRepository.findById(id).orElse(null);
    }
}