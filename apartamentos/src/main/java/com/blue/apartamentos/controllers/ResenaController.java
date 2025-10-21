package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.services.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    // Recupera la reseña asociada a una reservación específica.
    @GetMapping("/api/reservaciones/{idReservacion}/resena")
public ResponseEntity<ResenaModel> getResenaByReservacionId(@PathVariable Long idReservacion) {
    Optional<ResenaModel> resenaOpt = resenaService.findByReservacionId(idReservacion); 
    
    return resenaOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
}
    
    // Crea una nueva reseña para una reservación.
    @PostMapping("/api/reservaciones/{idReservacion}/resena")
    public ResponseEntity<ResenaModel> createResena(@PathVariable Long idReservacion, @RequestBody ResenaModel resena) {
        try {
            ResenaModel nuevaResena = resenaService.createResena(idReservacion, resena);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaResena);
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene todas las reseñas para una propiedad.
    @GetMapping("/api/propiedades/{idPropiedad}/resenas")
    public ResponseEntity<List<ResenaModel>> getResenasByPropiedad(@PathVariable Long idPropiedad) {
        List<ResenaModel> resenas = resenaService.findByPropiedadId(idPropiedad);
        return ResponseEntity.ok(resenas);
    }
    
    // Obtiene el promedio de calificación general para una propiedad.
    @GetMapping("/api/propiedades/{idPropiedad}/resenas/promedio")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long idPropiedad) {
        Optional<Double> promedio = resenaService.getAverageRating(idPropiedad);
        
        return promedio.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.noContent().build());
    }
    
    // Permite al propietario responder a una reseña específica.
    @PutMapping("/api/resenas/{idResena}/respuesta")
    public ResponseEntity<ResenaModel> responderResena(@PathVariable Long idResena, @RequestBody String respuesta) {
        try {
            // Nota: Aquí se necesitaría la lógica de seguridad (ej. @PreAuthorize)
            ResenaModel resenaActualizada = resenaService.responderResena(idResena, respuesta);
            return ResponseEntity.ok(resenaActualizada);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}
