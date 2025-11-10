package com.grupo108.camiones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.grupo108.models"})
public class MicroservicioCamionesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroservicioCamionesApplication.class,args);
    }
}
