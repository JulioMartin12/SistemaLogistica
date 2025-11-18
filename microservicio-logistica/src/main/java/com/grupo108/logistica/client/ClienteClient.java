package com.grupo108.logistica.client;

import com.grupo108.models.Cliente;
import com.grupo108.models.dtos.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-clientes", url = "http://microservicio-clientes:8080")
public interface ClienteClient {

    @PostMapping("/api/v1/clientes/buscar-o-crear")
    Cliente buscarOCrear(@RequestBody ClienteDto clienteDto);
}