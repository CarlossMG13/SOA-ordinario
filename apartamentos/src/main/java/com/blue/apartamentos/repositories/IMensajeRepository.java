package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.MensajeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMensajeRepository extends JpaRepository<MensajeModel, Long> {
    
    // Busca todos los mensajes (conversación) asociados a una reservación.
    List<MensajeModel> findByReservacionIdOrderByFechaEnvioAsc(Long idReservacion);

    // Busca mensajes donde un cliente es el destinatario (Bandeja de Entrada).
    List<MensajeModel> findByDestinatarioIdOrderByFechaEnvioDesc(Long idDestinatario);

    // Busca mensajes no leídos para un destinatario.
    List<MensajeModel> findByDestinatarioIdAndLeidoFalse(Long idDestinatario);
    
    // Marca un mensaje específico como leído.
    @Modifying
    @Query("UPDATE MensajeModel m SET m.leido = true WHERE m.id = :idMensaje AND m.destinatario.id = :idDestinatario")
    void marcarComoLeido(@Param("idMensaje") Long idMensaje, @Param("idDestinatario") Long idDestinatario);
}
