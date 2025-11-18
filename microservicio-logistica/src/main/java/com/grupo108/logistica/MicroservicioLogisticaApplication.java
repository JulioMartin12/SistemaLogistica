package com.grupo108.logistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = {"com.grupo108.models"})
public class MicroservicioLogisticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioLogisticaApplication.class, args);
    }
}