package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.services.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propiedades/{idPropiedad}/disponibilidad")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    // Recupera todos los registros de disponibilidad de una propiedad
    @GetMapping
    public ResponseEntity<List<DisponibilidadModel>> getByPropiedadId(@PathVariable Long idPropiedad) {
        List<DisponibilidadModel> disponibilidad = disponibilidadService.findByPropiedadId(idPropiedad);
        return ResponseEntity.ok(disponibilidad);
    }
    
    // Crea o actualiza la disponibilidad de una fecha.
    @PostMapping
    public ResponseEntity<DisponibilidadModel> createOrUpdate(@PathVariable Long idPropiedad, @RequestBody DisponibilidadModel disponibilidad) {
        try {
            DisponibilidadModel resultado = disponibilidadService.createOrUpdate(idPropiedad, disponibilidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Encuentra la disponibilidad REAL (disponible=true) en un rango de fechas.
    @GetMapping("/buscar")
    public ResponseEntity<List<DisponibilidadModel>> findDisponibleEnRango(
        @PathVariable Long idPropiedad,
        @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate inicio,
        @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        if (inicio.isAfter(fin)) {
            return ResponseEntity.badRequest().build();
        }
        
        List<DisponibilidadModel> disponible = disponibilidadService.findDisponibleEnRango(idPropiedad, inicio, fin);
        return ResponseEntity.ok(disponible);
    }

    // Elimina un registro de disponibilidad por su ID.
    @DeleteMapping("/{idDisponibilidad}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Long idDisponibilidad) {
        Optional<DisponibilidadModel> existing = disponibilidadService.findById(idDisponibilidad);
        
        if (existing.isPresent()) {
            disponibilidadService.deleteById(idDisponibilidad);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
