package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.PagoModel;
import com.blue.apartamentos.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservaciones/{idReservacion}/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Recupera todos los pagos asociados a una reservación.
    @GetMapping
    public ResponseEntity<List<PagoModel>> getPagosByReservacionId(@PathVariable Long idReservacion) {
        List<PagoModel> pagos = pagoService.findByReservacionId(idReservacion);
        return ResponseEntity.ok(pagos);
    }
    
    // Registra un nuevo pago.
    @PostMapping
    public ResponseEntity<PagoModel> createPago(@PathVariable Long idReservacion, @RequestBody PagoModel pago) {
        try {
            PagoModel nuevoPago = pagoService.procesarPago(idReservacion, pago);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Recupera un pago específico por su ID.
    @GetMapping("/{idPago}")
    public ResponseEntity<PagoModel> getPagoById(@PathVariable Long idReservacion, @PathVariable Long idPago) {
        Optional<PagoModel> pagoOpt = pagoService.findById(idPago);
        
        if (pagoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        PagoModel pago = pagoOpt.get();
        
        // Asegurar que el pago pertenece a la reservación correcta.
        if (!pago.getReservacion().getId().equals(idReservacion)) {
            // Devuelve 404 para ocultar si el recurso existe o no bajo otra URL.
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pago);
    }
    
    // Actualiza el estado de un pago (ej. de PENDIENTE a COMPLETADO, o FALLIDO).
    @PutMapping("/{idPago}")
    public ResponseEntity<PagoModel> updatePago(@PathVariable Long idReservacion, @PathVariable Long idPago, @RequestBody PagoModel pagoDetails) {
        Optional<PagoModel> pagoOpt = pagoService.findById(idPago);

        if (pagoOpt.isEmpty() || !pagoOpt.get().getReservacion().getId().equals(idReservacion)) {
            return ResponseEntity.notFound().build();
        }
        
        PagoModel existingPago = pagoOpt.get();
        
        // Lógica de actualización simple (Solo permite cambiar estado, fecha/referencia, o datos)
        existingPago.setEstado(pagoDetails.getEstado());
        
        // Actualizar fecha/referencia
        if (pagoDetails.getFechaPago() != null) {
            existingPago.setFechaPago(pagoDetails.getFechaPago());
        }
        if (pagoDetails.getReferenciaPago() != null) {
            existingPago.setReferenciaPago(pagoDetails.getReferenciaPago());
        }
        if (pagoDetails.getDatosPago() != null) {
            existingPago.setDatosPago(pagoDetails.getDatosPago());
        }
        
        final PagoModel updatedPago = pagoService.save(existingPago);
        return ResponseEntity.ok(updatedPago);
    }
}
