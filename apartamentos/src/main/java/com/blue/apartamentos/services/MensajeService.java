package com.blue.apartamentos.services;

import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.repositories.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService { 

    @Autowired
    private IMensajeRepository mensajeRepository;

    public Optional<MensajeModel> findById(Long id) {
        return mensajeRepository.findById(id);
    }
    
    public List<MensajeModel> getConversacionByReservacionId(Long idReservacion) {
        return mensajeRepository.findByReservacionIdOrderByFechaEnvioAsc(idReservacion);
    }
    
    public List<MensajeModel> getBandejaEntrada(Long idDestinatario) {
        return mensajeRepository.findByDestinatarioIdOrderByFechaEnvioDesc(idDestinatario);
    }

    // Enviar un mensaje.
    @Transactional
    public MensajeModel enviarMensaje(MensajeModel mensaje) {
        Long idRemitente = mensaje.getRemitente() != null ? mensaje.getRemitente().getId() : null;
        Long idDestinatario = mensaje.getDestinatario() != null ? mensaje.getDestinatario().getId() : null;
        Long idReservacion = mensaje.getReservacion() != null ? mensaje.getReservacion().getId() : null;

        if (idRemitente == null || idDestinatario == null || idReservacion == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Remitente, Destinatario y Reservación son obligatorios.");
        }
        
        // El campo 'leido' y 'fechaEnvio' se inicializan en el @PrePersist del modelo.
        return mensajeRepository.save(mensaje);
    }

    // Marca un mensaje como leído por el destinatario.
    @Transactional
    public void marcarComoLeido(Long idMensaje, Long idDestinatario) {
        // Se valida en la query que el destinatario sea el dueño del mensaje para evitar accesos no autorizados.
        mensajeRepository.marcarComoLeido(idMensaje, idDestinatario);
    }
}
