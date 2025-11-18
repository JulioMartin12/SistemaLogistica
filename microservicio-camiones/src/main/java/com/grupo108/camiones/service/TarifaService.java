package com.grupo108.camiones.service;

import com.grupo108.camiones.client.IntegracionClient;
import com.grupo108.camiones.repository.TarifaRepository;
import com.grupo108.models.Camion;
import com.grupo108.models.Tarifa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaService {
    private static final Logger log = LoggerFactory.getLogger(TarifaService.class);
    private final IntegracionClient integracionClient;
    private final CamionService camionService;
    private final TarifaRepository tarifaRepository;

    public TarifaService(IntegracionClient integracionClient,
                         CamionService camionService,
                         TarifaRepository tarifaRepository) {
        this.integracionClient = integracionClient;
        this.camionService = camionService;
        this.tarifaRepository = tarifaRepository;
    }


    public Double calcularCostoAproximado(Double pesoCarga, Double volumenCarga,
                                          double latOrigen, double lonOrigen,
                                          double latDestino, double lonDestino) {

        Double distanciaMetros = integracionClient.obtenerDistancia(latOrigen, lonOrigen, latDestino, lonDestino);
        Double distanciaKm = distanciaMetros / 1000.0;

        List<Camion> camionesAptos = camionService.buscarElegibles(pesoCarga, volumenCarga);

        if (camionesAptos.isEmpty()) {
            log.warn("No hay camiones aptos para peso {} y volumen {}", pesoCarga, volumenCarga);
            throw new RuntimeException("No hay flota disponible para esta capacidad");
        }

        double promedioConsumo = camionesAptos.stream()
                .mapToDouble(Camion::getConsumoCombustiblePromedio)
                .average().orElse(0.0);

        Tarifa tarifaRef = tarifaRepository.findTarifaPorVolumen(volumenCarga);

        if (tarifaRef == null) {
            throw new RuntimeException("No existe tarifa configurada para el volumen: " + volumenCarga);
        }

        return calcularFormula(distanciaKm, promedioConsumo, tarifaRef);
    }

    public Double calcularCostoReal(Camion camion, Double volumenReal,
                                    double latOrigen, double lonOrigen,
                                    double latDestino, double lonDestino) {

        Double distanciaKm = integracionClient.obtenerDistancia(latOrigen, lonOrigen, latDestino, lonDestino) / 1000.0;

        Tarifa tarifa = tarifaRepository.findTarifaPorVolumen(volumenReal);

        if (tarifa == null) {
            throw new RuntimeException("Error calculando costo real: Sin tarifa para volumen " + volumenReal);
        }

        return calcularFormula(distanciaKm, camion.getConsumoCombustiblePromedio(), tarifa);
    }

    private Double calcularFormula(Double km, Double consumo, Tarifa tarifa) {
        Double costoGestion = tarifa.getCargoGestionBase();
        Double costoPorKm = km * tarifa.getCostoKmBase();
        Double costoCombustible = km * consumo * tarifa.getValorLitroCombustible();
        Double total = costoGestion + costoPorKm + costoCombustible;
        return Math.round(total * 100.0) / 100.0;
    }
}