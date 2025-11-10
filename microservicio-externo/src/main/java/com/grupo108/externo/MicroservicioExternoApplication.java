package com.grupo108.externo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.grupo108.models"})
public class MicroservicioExternoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioExternoApplication.class,args);
    }
}
