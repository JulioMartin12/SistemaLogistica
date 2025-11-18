package com.grupo108.integracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.grupo108.integracion")
public class MicroservicioIntegracionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioIntegracionApplication.class, args);
    }
}

