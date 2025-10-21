package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;
    
    // Recupera la conversación completa asociada a una reservación.
    @GetMapping("/reservaciones/{idReservacion}/mensajes")
    public ResponseEntity<List<MensajeModel>> getConversacionByReservacionId(@PathVariable Long idReservacion) {
        List<MensajeModel> mensajes = mensajeService.getConversacionByReservacionId(idReservacion);
        return ResponseEntity.ok(mensajes);
    }
    
    // Envía un nuevo mensaje dentro del contexto de una reservación.
    @PostMapping("/reservaciones/{idReservacion}/mensajes")
    public ResponseEntity<MensajeModel> enviarMensaje(
        @PathVariable Long idReservacion, 
        @RequestBody MensajeModel mensaje
    ) {
        // Se asegura que el ID de la URL y el del body coincidan (si el body lo incluye)
        if (mensaje.getReservacion() == null || !mensaje.getReservacion().getId().equals(idReservacion)) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de reservación en el body no coincide con el ID de la URL.");
        }
        
        try {
            MensajeModel nuevoMensaje = mensajeService.enviarMensaje(mensaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMensaje);
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene la bandeja de entrada de un usuario (mensajes donde es destinatario).
    @GetMapping("/usuarios/{idUsuario}/mensajes/recibidos")
    public ResponseEntity<List<MensajeModel>> getBandejaEntrada(@PathVariable Long idUsuario) {
        // Nota: En un sistema real, el idUsuario se tomaría del token de seguridad (principal)
        List<MensajeModel> mensajes = mensajeService.getBandejaEntrada(idUsuario);
        return ResponseEntity.ok(mensajes);
    }
    
    // Marca un mensaje específico como leído.
    @PutMapping("/mensajes/{idMensaje}/leido")
    public ResponseEntity<Void> marcarComoLeido(
        @PathVariable Long idMensaje, 
        @RequestParam Long idDestinatario // En un sistema real, se tomaría del token
    ) {
        try {
            mensajeService.marcarComoLeido(idMensaje, idDestinatario);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}