package com.grupo108.camiones.service;

import com.grupo108.models.Camion;
import com.grupo108.camiones.repository.CamionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CamionService {

    private final CamionRepository camionRepository;
    private static final Logger log = LoggerFactory.getLogger(CamionService.class);

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    @Transactional
    public Camion guardarCamion(Camion camion) {
        log.info("Intentando guardar camión con patente: {}", camion.getPatente());
        return camionRepository.save(camion);
    }

    @Transactional(readOnly = true)
    public List<Camion> buscarTodos() {
        return camionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Camion> buscarElegibles(Double peso, Double volumen) {
        log.debug("Filtrando camiones disponibles para Peso>={} y Volumen>={}", peso, volumen);

        return camionRepository.findCamionesAptos(peso, volumen);
    }
}