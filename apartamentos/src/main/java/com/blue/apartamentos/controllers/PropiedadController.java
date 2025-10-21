package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.enums.EstadoPropiedad;
import com.blue.apartamentos.models.enums.TipoPropiedad;
import com.blue.apartamentos.services.PropiedadService;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
    
    @Autowired
    private PropiedadService propiedadService;

    // Recuperar todas la propiedades
    @GetMapping
    public List<PropiedadModel> getAllPropiedades() {
        return propiedadService.getAllPropiedades();
    }

    // Propiedad por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadModel> getPropiedadesById(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        return propiedad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva propiedad
    @PostMapping
    public PropiedadModel createPropiedad(@RequestBody PropiedadModel propiedad) {
        return propiedadService.savePropiedad(propiedad);
    }

    // Actualizar propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadModel> updatePropiedad(@PathVariable Long id, @RequestBody PropiedadModel propiedadDetails) {
        Optional<PropiedadModel> propiedadOptional = propiedadService.getPropiedadById(id);
        if (propiedadOptional.isPresent()) {
            PropiedadModel propiedadToUpdate = propiedadOptional.get();
            propiedadToUpdate.setDireccion(propiedadDetails.getDireccion());
            propiedadToUpdate.setTipo(propiedadDetails.getTipo());
            propiedadToUpdate.setPrecio_noche(propiedadDetails.getPrecio_noche());
            // Actualizar otros campos según sea necesario

            PropiedadModel updatedPropiedad = propiedadService.savePropiedad(propiedadToUpdate);
            return ResponseEntity.ok(updatedPropiedad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        if (propiedad.isPresent()) {
            propiedadService.deletePropiedad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para recuperar propiedades por Tipo(ACTIVO/INACTIVO/SUSPENDIDO)
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByTipo(@PathVariable String tipo){
        try{
            // Convierte la cadena de texto de la URL a tu Enum TipoPropiedad
            TipoPropiedad tipoEnum = TipoPropiedad.valueOf(tipo.toUpperCase());

            // Llama al servicio para filtrar por este "tipo"
            List<PropiedadModel> propiedades = propiedadService.getPropiedadesByTipo(tipoEnum);

            if (propiedades.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(propiedades);
        }catch(IllegalArgumentException e){
            // Maneja el caso en que el valor no es uno de los enums válidos
            return ResponseEntity.badRequest().build();
        }
    }

    // Metodo para recuperar todas las propiedades de un propietario
    @GetMapping("/propietario/{idUsuario}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByPropietario(@PathVariable Long idUsuario) {
        
        // El servicio maneja la validación del tipo de usuario y la consulta
        List<PropiedadModel> propiedades = propiedadService.getPropiedadesByPropietarioId(idUsuario);

        if (propiedades.isEmpty()) {
            // Si el servicio devuelve lista vacía (porque no es PROPIETARIO o no tiene propiedades), devuelve 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(propiedades);
    }

    // Metodo para recuperar propiedades por Estado (DISPONIBLE/MANTENIMIENTO/NO_DISPONIBLE)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByEstado(@PathVariable String estado){
        try{
            // Convierte la cadena de texto de la URL a tu Enum EstadoPropiedad
            EstadoPropiedad estadoEnum = EstadoPropiedad.valueOf(estado.toUpperCase());

            List<PropiedadModel> propiedades = propiedadService.getPropiedadesByEstado(estadoEnum);

            if (propiedades.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(propiedades);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
