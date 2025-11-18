package com.grupo108.integracion.service;

import com.grupo108.models.dtos.GeolocalizacionDto;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {

    public GeolocalizacionDto convertirDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }

        String normalizedDir = java.text.Normalizer.normalize(direccion, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();

        if (normalizedDir.contains("cordoba") || normalizedDir.contains("san martin")) {
            return new GeolocalizacionDto(-31.4173, -64.1841, direccion);
        }
        if (normalizedDir.contains("buenos aires") || normalizedDir.contains("obelisco")) {
            return new GeolocalizacionDto(-34.6037, -58.3816, direccion);
        }

        throw new IllegalArgumentException("Dirección '" + direccion + "' no reconocida para geocodificación simulada.");
    }
}