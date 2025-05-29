package com.ecomarket.ApiNotificaciones.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecomarket.ApiNotificaciones.Model.Boleta;
import com.ecomarket.ApiNotificaciones.Model.Notificacion;
import com.ecomarket.ApiNotificaciones.Repository.BoletaRepository;
import com.ecomarket.ApiNotificaciones.Repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    private BoletaRepository boletaRepository;



    public void generarNotificacionesDelDia() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);

        List<Boleta> boletas = boletaRepository.findByFechaEmisionBetween(inicio, fin);

        for (Boleta boleta : boletas) {

             if (notificacionRepository.findByBoletaId(boleta.getBoletaId()).isPresent()) {
        continue; // Ya existe, la saltamos
    }
            Notificacion notificacion = new Notificacion();
            notificacion.setUsuarioId(boleta.getUsuario().getId());
            notificacion.setTipo("Compra");
            notificacion.setTitulo("¡Compra realizada!");
            notificacion.setMensaje("Hola " + boleta.getUsuario().getNombre() + ", tu compra fue registrada por $" + boleta.getTotal());
            notificacion.setEnviada_en(LocalDateTime.now());
            notificacion.setEstado("enviada");

            notificacionRepository.save(notificacion);
        }
    }

    // Obtener todos los productos
    public List<Notificacion> getAll() {
        return notificacionRepository.findAll();

    }

    public List<Notificacion> getByUsuarioId(Integer usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public Notificacion getById(Integer id){
        Optional<Notificacion> notificacion = notificacionRepository.findById(id);
        return notificacion.orElse(null);
    }
    

    public Notificacion add(Notificacion notificacion){
        return notificacionRepository.save(notificacion);
    }

    public Notificacion update(Integer id, Notificacion notificacion) {
        if (notificacionRepository.existsById(id)) {
            notificacion.setId(id); 
            return notificacionRepository.save(notificacion); 
        }
        return null; 
    }

     public Notificacion delete(Integer id) {
        Optional<Notificacion> notificacion = notificacionRepository.findById(id);
        if (notificacion.isPresent()) {
            notificacionRepository.deleteById(id); 
            return notificacion.get(); 
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<Void> crearNotificacion(@RequestBody Notificacion notificacion) {
        notificacion.setEnviada_en(LocalDateTime.now());
        notificacionRepository.save(notificacion);
    return ResponseEntity.ok().build();
}

}
