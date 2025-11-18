package com.grupo108.logistica.client;

import com.grupo108.models.Contenedor;
import com.grupo108.models.dtos.ContenedorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-contenedores", url = "http://microservicio-contenedores:8080")
public interface ContenedorClient {

    @PostMapping("/api/v1/contenedores")
    Contenedor crear(@RequestBody ContenedorDto contenedorDto);
}