package com.blue.apartamentos.services;

import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.repositories.IResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaService { 

    @Autowired
    private IResenaRepository resenaRepository;

    // Nuevo método público para exponer la funcionalidad
    public Optional<ResenaModel> findByReservacionId(Long idReservacion) {
        return resenaRepository.findByReservacionId(idReservacion);
    }

    public Optional<ResenaModel> findById(Long id) {
        return resenaRepository.findById(id);
    }
    
    public List<ResenaModel> findByPropiedadId(Long idPropiedad) {
        return resenaRepository.findByPropiedadId(idPropiedad);
    }
    
    public Optional<Double> getAverageRating(Long idPropiedad) {
        return resenaRepository.getAverageRatingByPropiedadId(idPropiedad);
    }

    // Crea una nueva reseña
    public ResenaModel createResena(Long idReservacion, ResenaModel resena) {
        // Una reservación solo puede tener una reseña (OneToOne).
        if (resenaRepository.findByReservacionId(idReservacion).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta reservación ya tiene una reseña asociada.");
        }
        
        // Asegurar que el ID del body coincide con el ID de la URL
        if (resena.getReservacion() == null || !resena.getReservacion().getId().equals(idReservacion)) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de reservación en el body no coincide con el ID de la URL.");
        }
        
        // Guardar
        return resenaRepository.save(resena);
    }

    // Permite al propietario responder a la reseña.
    public ResenaModel responderResena(Long idResena, String respuesta) {
        ResenaModel resena = resenaRepository.findById(idResena)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseña no encontrada."));
        
        resena.setRespuestaPropietario(respuesta);
        resena.setFechaRespuesta(LocalDateTime.now());
        
        return resenaRepository.save(resena);
    }
}