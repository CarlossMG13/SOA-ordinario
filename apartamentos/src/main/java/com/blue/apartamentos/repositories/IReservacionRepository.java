package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.ReservacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IReservacionRepository extends JpaRepository<ReservacionModel, Long> {
    
    // Buscar todas las reservaciones de una propiedad específica (por el ID de la propiedad)
    List<ReservacionModel> findByPropiedadId(Long idPropiedad);

    // Buscar todas las reservaciones hechas por un cliente (por el ID del cliente)
    List<ReservacionModel> findByUserId(Long idUsuario);

    // Buscar una reservación por su código único
    Optional<ReservacionModel> findByCodigoReserva(String codigoReserva);
}
