package com.blue.apartamentos.services;

import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.repositories.IDisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadService { 

    @Autowired
    private IDisponibilidadRepository disponibilidadRepository;

    public DisponibilidadModel save(DisponibilidadModel disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public Optional<DisponibilidadModel> findById(Long id) {
        return disponibilidadRepository.findById(id);
    }

    public void deleteById(Long id) {
        disponibilidadRepository.deleteById(id);
    }
    
    public List<DisponibilidadModel> findByPropiedadId(Long idPropiedad) {
        return disponibilidadRepository.findByPropiedadId(idPropiedad);
    }

    public List<DisponibilidadModel> findDisponibleEnRango(Long idPropiedad, LocalDate fechaInicio, LocalDate fechaFin) {
        // Usa la consulta @Query del repositorio
        return disponibilidadRepository.findDisponibleByPropiedadAndRangoFechas(idPropiedad, fechaInicio, fechaFin);
    }
    
    // Metodo para la creacion o actualizacion
    public DisponibilidadModel createOrUpdate(Long idPropiedad, DisponibilidadModel disponibilidad) {

        // Validar que el objeto del body tiene el mismo ID de propiedad
        if (disponibilidad.getPropiedad() == null || !disponibilidad.getPropiedad().getId_propiedad().equals(idPropiedad)) {
             // Lanza un error si la propiedad del path no coincide con la del body
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de propiedad en el body no coincide con el ID de la URL.");
        }
        
        // Chequea si ya existe un registro para esa fecha y actualizarlo si es necesario
        Optional<DisponibilidadModel> existingOpt = disponibilidadRepository.findByPropiedadIdAndFecha(
            idPropiedad, 
            disponibilidad.getFecha()
        );
        
        if (existingOpt.isPresent()) {
            DisponibilidadModel existing = existingOpt.get();
            // Sobreescribir solo los campos que se pueden actualizar (precio y disponibilidad)
            existing.setDisponible(disponibilidad.getDisponible());
            existing.setPrecioEspecial(disponibilidad.getPrecioEspecial());
            disponibilidad = existing;
        }

        return disponibilidadRepository.save(disponibilidad);
    }
}
