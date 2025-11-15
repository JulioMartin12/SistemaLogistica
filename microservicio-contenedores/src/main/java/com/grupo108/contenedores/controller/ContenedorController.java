package com.grupo108.contenedores.controller;

import com.grupo108.models.Contenedor;
import com.grupo108.contenedores.service.ContenedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenedores") // Endpoint base
public class ContenedorController {

    private final ContenedorService contenedorService;

    public ContenedorController(ContenedorService contenedorService) {
        this.contenedorService = contenedorService;
    }

    @PostMapping // Crear Contenedor
    public ResponseEntity<Contenedor> crearContenedor(@RequestBody Contenedor contenedor) {
        Contenedor nuevoContenedor = contenedorService.guardarContenedor(contenedor);
        return new ResponseEntity<>(nuevoContenedor, HttpStatus.CREATED);
    }

    @GetMapping // Obtener todos
    public ResponseEntity<List<Contenedor>> obtenerTodos() {
        return new ResponseEntity<>(contenedorService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // Obtener por ID
    public ResponseEntity<Contenedor> obtenerPorId(@PathVariable Long id) {
        Contenedor contenedor = contenedorService.buscarPorId(id);
        return contenedor != null
                ? new ResponseEntity<>(contenedor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}