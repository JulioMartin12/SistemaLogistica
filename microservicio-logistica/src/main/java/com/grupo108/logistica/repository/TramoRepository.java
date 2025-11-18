package com.grupo108.logistica.repository;

import com.grupo108.models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Integer> {
}