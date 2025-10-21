package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.PropiedadImgModel;
import com.blue.apartamentos.services.PropiedadImgService; // Importa la clase de servicio directamente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propiedades/{idPropiedad}/imagenes")
public class PropiedadImgController {
    
    @Autowired
    private PropiedadImgService propiedadImgService; 

    // Recupera todas las imagenes de una propiedad especifica
    @GetMapping
    public ResponseEntity<List<PropiedadImgModel>> getByPropiedadId(@PathVariable Long idPropiedad) {
        List<PropiedadImgModel> imagenes = propiedadImgService.findByPropiedadId(idPropiedad);
        if (imagenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenes);
    }
    
    // Guarda una imagen en una propiedad especifica
    @PostMapping
    public ResponseEntity<PropiedadImgModel> createImagen(@PathVariable Long idPropiedad, @RequestBody PropiedadImgModel imagen) {
        PropiedadImgModel nuevaImagen = propiedadImgService.save(imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaImagen);
    }
    
    //Recupera una imagen por su ID
    @GetMapping("/{idImagen}")
    public ResponseEntity<PropiedadImgModel> getById(@PathVariable Long idImagen) {
        Optional<PropiedadImgModel> imagen = propiedadImgService.findById(idImagen);
        
        return imagen.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Elimina imagen por su ID
    @DeleteMapping("/{idImagen}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Long idImagen) {
        Optional<PropiedadImgModel> existing = propiedadImgService.findById(idImagen);
        
        if (existing.isPresent()) {
            propiedadImgService.deleteById(idImagen);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
