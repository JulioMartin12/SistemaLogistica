package com.grupo108.clientes.controller;

import com.grupo108.clientes.service.ClienteService;
import com.grupo108.models.Cliente;
import com.grupo108.models.dtos.ClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/buscar-o-crear")
    @Operation(summary = "Buscar o Registrar Cliente", description = "Si el email existe, devuelve el cliente. Si no, lo crea.")
    public ResponseEntity<Cliente> buscarOCrear(@RequestBody ClienteDto clienteDto) {
        log.info("Solicitud de buscar/crear cliente: {}", clienteDto.getEmail());

        Optional<Cliente> existente = clienteService.buscarPorEmail(clienteDto.getEmail());

        if (existente.isPresent()) {
            log.info("Cliente existente encontrado: {}", existente.get().getIdCliente());
            return ResponseEntity.ok(existente.get());
        }

        Cliente nuevo = new Cliente();
        nuevo.setNombre(clienteDto.getNombre());
        nuevo.setApellido(clienteDto.getApellido());
        nuevo.setEmail(clienteDto.getEmail());
        nuevo.setTelefono(clienteDto.getTelefono());
        nuevo.setDomicilio(clienteDto.getDomicilio());

        Cliente guardado = clienteService.guardarCliente(nuevo);
        log.info("Nuevo cliente registrado: {}", guardado.getIdCliente());

        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        clienteService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}