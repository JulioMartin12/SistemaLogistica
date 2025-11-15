package com.grupo108.camiones.service;

import com.grupo108.models.Camion;
import com.grupo108.camiones.repository.CamionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    public Camion guardarCamion(Camion camion) {
        return camionRepository.save(camion);
    }

    public List<Camion> buscarTodos() {
        return camionRepository.findAll();
    }

    // REGLA DE NEGOCIO OBLIGATORIA: Filtrar por capacidad y disponibilidad
    public List<Camion> buscarElegibles(Double peso, Double volumen) {
        // Un camión no puede transportar contenedores que superen su peso o volumen máximo.
        // La consulta se delega al repositorio.
        return camionRepository.findByDisponibilidadTrueAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
                peso, volumen
        );
    }
}