package com.blue.apartamentos.services;

import com.blue.apartamentos.models.PagoModel;
import com.blue.apartamentos.repositories.IPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService { 

    @Autowired
    private IPagoRepository pagoRepository;

    public PagoModel save(PagoModel pago) {
        return pagoRepository.save(pago);
    }
    
    public Optional<PagoModel> findById(Long id) {
        return pagoRepository.findById(id);
    }
    
    public List<PagoModel> findByReservacionId(Long idReservacion) {
        return pagoRepository.findByReservacionId(idReservacion);
    }
    
    // Procesa y registra un nuevo pago para una reservación específica.
    public PagoModel procesarPago(Long idReservacion, PagoModel pago) {
        
        // Validar que el ID del body coincida con el ID de la URL
        if (pago.getReservacion() == null || !pago.getReservacion().getId().equals(idReservacion)) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de reservación en el body no coincide con el ID de la URL.");
        }

        // Guardar y actualizar el estado de la reservación (si es pago completo)
        PagoModel nuevoPago = pagoRepository.save(pago);
        return nuevoPago;
    }

    // Marca un pago como completado, estableciendo la fecha de pago.
    public PagoModel completarPago(Long idPago, String referenciaPago, String datosPago) {
        PagoModel pago = pagoRepository.findById(idPago)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pago no encontrado."));
        
        pago.setFechaPago(LocalDateTime.now());
        pago.setReferenciaPago(referenciaPago);
        pago.setDatosPago(datosPago);
        
        return pagoRepository.save(pago);
    }
}
