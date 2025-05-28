package com.ecomarket.ApiNotificaciones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomarket.ApiNotificaciones.Model.Notificacion;
import com.ecomarket.ApiNotificaciones.Service.NotificacionService;

public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;


    @GetMapping
    public ResponseEntity<List<Notificacion>> getAll(){
        return ResponseEntity.ok(notificacionService.getAll());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> getByUsuario(@PathVariable Integer usuarioId) {
        return notificacionService.getByUsuarioId(usuarioId);
    }





}
