package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.ResenaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IResenaRepository extends JpaRepository<ResenaModel, Long> {
    
    // Busca la reseña asociada a una reservación específica.
    Optional<ResenaModel> findByReservacionId(Long idReservacion);

    // Busca todas las reseñas para una propiedad, navegando a través de la Reservacion.
    @Query("SELECT r FROM ResenaModel r WHERE r.reservacion.propiedad.id = :idPropiedad ORDER BY r.fechaResena DESC")
    List<ResenaModel> findByPropiedadId(@Param("idPropiedad") Long idPropiedad);

    // Calcula el promedio de la calificación general para una propiedad.
    @Query("SELECT AVG(r.calificacionGeneral) FROM ResenaModel r WHERE r.reservacion.propiedad.id = :idPropiedad")
    Optional<Double> getAverageRatingByPropiedadId(@Param("idPropiedad") Long idPropiedad);
}
