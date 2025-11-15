package com.grupo108.camiones.config;

import com.grupo108.models.*; // Importa todas las entidades
import com.grupo108.camiones.repository.CamionRepository;
import com.grupo108.camiones.repository.TarifaRepository;
import com.grupo108.camiones.repository.TransportistaRepository;

import com.grupo108.models.enums.TipoCamion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration // 💡 Anotación que indica a Spring que esta clase contiene Beans
public class DataLoader {

    // 💡 Paso 2: Crear el Bean CommandLineRunner para su ejecución
    @Bean
    public CommandLineRunner loadData(
            TarifaRepository tarifaRepository,
            CamionRepository camionRepository,
            TransportistaRepository transportistaRepository
    ) {
        // La lógica de inserción se ejecuta una vez que la aplicación arranca
        return args -> {

            // 1. Lógica de Precarga va aquí

            // 2. Ejecutar lógica de inserción
            if (tarifaRepository.count() == 0) {
                cargarDatos(tarifaRepository, camionRepository, transportistaRepository);
            }
        };
    }

    // 💡 Paso 3: El método que contiene la lógica de inserción
    private void cargarDatos(
            TarifaRepository tarifaRepository,
            CamionRepository camionRepository,
            TransportistaRepository transportistaRepository) {

        // 1. Precargar Transportista (Necesario para la FK en Camion)
        Transportista t = new Transportista("Juan", "Pérez", "3511234567", true); // ⬅️ CORREGIDO
         t = transportistaRepository.save(t);
        System.out.println("Transportista precargado.");


        // 2. Precargar Tarifas (Ajustado al constructor de 7 parámetros de Tarifa)
        // Los parámetros son: (id, valorLitro, cargoGestion, costoKm, descripcion, volMin, volMax)

        // Asumo que el valorLitroCombustible es fijo para todas (ej: 950.0)
        // y el cargoGestionBase es fijo (ej: 5000.0)

        Tarifa liviano = new Tarifa( 950.0, 5000.0, 150.0, "Camión Liviano (0-25m3)", 0.0, 25.0);
        Tarifa mediano = new Tarifa( 950.0, 7000.0, 200.0, "Camión Mediano (25-50m3)", 25.1, 50.0);
        Tarifa pesado = new Tarifa( 950.0, 10000.0, 300.0, "Camión Pesado (50-100m3)", 50.1, 100.0);

        tarifaRepository.saveAll(List.of(liviano, mediano, pesado));
        System.out.println("Tarifas precargadas.");

        // 3. Precargar Camiones (Ajustado al nuevo constructor asumido)
        // Parámetros asumidos: (id, patente, capPeso, capVol, consumoPromedio, disponibilidad, TIPO, transportista, tarifa)

        if (camionRepository.count() == 0) {

            // Parámetros: (patente, transportista, capPeso, capVol, consumo, costoBaseKm, tipoCamion, disponibilidad)

            Camion camion1 = new Camion("AAA111", t, 5000.0, 25.0, 8.5, 150.0, TipoCamion.LIVIANO, true);
            Camion camion2 = new Camion("BBB222", t, 12000.0, 45.0, 12.0, 200.0, TipoCamion.MEDIANO, true);
            Camion camion3 = new Camion("CCC333", t, 30000.0, 80.0, 18.0, 300.0, TipoCamion.PESADO, true);

            camionRepository.saveAll(List.of(camion1, camion2, camion3));
            System.out.println("Camiones precargados.");
        }
    }
}
