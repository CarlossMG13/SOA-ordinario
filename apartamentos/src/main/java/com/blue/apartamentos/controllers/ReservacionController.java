package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.services.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    // Crea una nueva reservacion
    @PostMapping
    public ResponseEntity<ReservacionModel> createReservacion(@RequestBody ReservacionModel reservacion) {
        // Revisar que User y Propiedad estan correctamente enlazadas
        ReservacionModel nuevaReservacion = reservacionService.save(reservacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReservacion);
    }

    // Obtiene todas las reservaciones
    @GetMapping
    public ResponseEntity<List<ReservacionModel>> getAllReservaciones() {
        List<ReservacionModel> reservaciones = reservacionService.findAll();
        return ResponseEntity.ok(reservaciones);
    }

    // Obtiene una reservacion por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservacionModel> getReservacionById(@PathVariable Long id) {
        Optional<ReservacionModel> reservacion = reservacionService.findById(id);
        return reservacion.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualiza una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<ReservacionModel> updateReservacion(@PathVariable Long id, @RequestBody ReservacionModel reservacionDetails) {
        Optional<ReservacionModel> reservacionOpt = reservacionService.findById(id);

        if (reservacionOpt.isPresent()) {
            ReservacionModel existingReservacion = reservacionOpt.get();
            
            // Actualizar campos permitidos (ej. fecha de salida, número de huéspedes, notas)
            existingReservacion.setFechaEntrada(reservacionDetails.getFechaEntrada());
            existingReservacion.setFechaSalida(reservacionDetails.getFechaSalida());
            existingReservacion.setNumeroHuespedes(reservacionDetails.getNumeroHuespedes());
            existingReservacion.setNotas(reservacionDetails.getNotas());

            final ReservacionModel updatedReservacion = reservacionService.save(existingReservacion);
            return ResponseEntity.ok(updatedReservacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Elimina una reserva por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservacion(@PathVariable Long id) {
        if (reservacionService.findById(id).isPresent()) {
            reservacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // --- Métodos de búsqueda anidada (ej. por propiedad) ---

    // Obtiene todas las reservaciones de una propiedad
    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<ReservacionModel>> getReservacionesByPropiedad(@PathVariable Long idPropiedad) {
        List<ReservacionModel> reservaciones = reservacionService.findByPropiedadId(idPropiedad);
        if (reservaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservaciones);
    }
}
