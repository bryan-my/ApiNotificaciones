package com.ecomarket.ApiNotificaciones.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.ApiNotificaciones.Model.Boleta;

public interface BoletaRepository extends JpaRepository <Boleta, Integer>{

    List<Boleta> findByFechaEmisionBetween(LocalDateTime start, LocalDateTime end);

}
