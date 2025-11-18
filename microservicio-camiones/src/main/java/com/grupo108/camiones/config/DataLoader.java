package com.grupo108.camiones.config;

import com.grupo108.models.Camion;
import com.grupo108.models.Tarifa;
import com.grupo108.models.Transportista;
import com.grupo108.models.enums.TipoCamion;

// Asegúrate de que estos repositorios existan en tu paquete repository
import com.grupo108.camiones.repository.CamionRepository;
import com.grupo108.camiones.repository.TarifaRepository;
import com.grupo108.camiones.repository.TransportistaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            TarifaRepository tarifaRepository,
            CamionRepository camionRepository,
            TransportistaRepository transportistaRepository
    ) {
        return args -> {
              if (tarifaRepository.count() == 0) {
                cargarDatos(tarifaRepository, camionRepository, transportistaRepository);
            }
        };
    }

    private void cargarDatos(
            TarifaRepository tarifaRepository,
            CamionRepository camionRepository,
            TransportistaRepository transportistaRepository) {

        System.out.println("Iniciando precarga de datos...");

        Transportista t1 = new Transportista("Juan", "Pérez", "3511234567", true);
        Transportista t2 = new Transportista("Maria", "Gomez", "3519876543", true);

        List<Transportista> transportistas = transportistaRepository.saveAll(List.of(t1, t2));
        Transportista transportistaJuan = transportistas.get(0); // Juan

        System.out.println("Transportistas precargados.");

        Tarifa liviano = new Tarifa(950.0, 5000.0, 150.0, "Tarifa Liviana (0-25m3)", 0.0, 25.0);
        Tarifa mediano = new Tarifa(950.0, 7000.0, 200.0, "Tarifa Mediana (25-50m3)", 25.0, 50.0);
        Tarifa pesado = new Tarifa(950.0, 10000.0, 300.0, "Tarifa Pesada (>50m3)", 50.0, null);

        tarifaRepository.saveAll(List.of(liviano, mediano, pesado));
        System.out.println("Tarifas precargadas.");


        if (camionRepository.count() == 0) {


            Camion c1 = new Camion("AA123BB", transportistaJuan, 5000.0, 20.0, 12.5, 150.0, TipoCamion.LIVIANO, true);
            Camion c2 = new Camion("CC456DD", transportistaJuan, 12000.0, 45.0, 18.0, 200.0, TipoCamion.MEDIANO, true);
            Camion c3 = new Camion("EE789FF", transportistaJuan, 30000.0, 80.0, 25.0, 300.0, TipoCamion.PESADO, true);

            camionRepository.saveAll(List.of(c1, c2, c3));
            System.out.println("Camiones precargados.");
        }

        System.out.println("Carga de datos finalizada con éxito.");
    }
}