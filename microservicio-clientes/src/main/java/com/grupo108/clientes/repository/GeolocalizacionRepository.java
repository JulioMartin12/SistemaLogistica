package com.grupo108.clientes.repository;

import com.grupo108.models.Geolocalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocalizacionRepository  extends JpaRepository<Geolocalizacion,Long> {
}
