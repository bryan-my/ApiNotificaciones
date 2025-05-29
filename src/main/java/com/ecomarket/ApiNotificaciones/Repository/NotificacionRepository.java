package com.ecomarket.ApiNotificaciones.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.ApiNotificaciones.Model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{
    List<Notificacion> findByUsuarioId(Integer usuarioId);

    Optional<Notificacion> findByBoletaId(Integer boletaId);

}
