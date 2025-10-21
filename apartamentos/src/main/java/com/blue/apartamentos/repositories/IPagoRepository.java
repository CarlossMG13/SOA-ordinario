package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.PagoModel;
import com.blue.apartamentos.models.enums.EstadoPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPagoRepository extends JpaRepository<PagoModel, Long> {
    
    // Busca todos los pagos asociados a una reservación específica.
    List<PagoModel> findByReservacionId(Long idReservacion);

    // Busca pagos por estado (útil para revisar pagos pendientes o fallidos).
    List<PagoModel> findByEstado(EstadoPago estado);

    // Busca un pago por la referencia única.
    Optional<PagoModel> findByReferenciaPago(String referenciaPago);
}
