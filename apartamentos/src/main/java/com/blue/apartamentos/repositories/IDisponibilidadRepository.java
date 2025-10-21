package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.DisponibilidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {
    
    // Todos los registros de disponibilidad en una propiedad especifica
    List<DisponibilidadModel> findByPropiedadId(Long idPropiedad);

    // Disponibilidad de una propiedad en un rango de fechas
    List<DisponibilidadModel> findByPropiedadIdAndFechaBetween(Long idPropiedad, LocalDate fechaInicio, LocalDate fechaFin);
    
    // Registro de disponibilidad por propiedad y fecha
    Optional<DisponibilidadModel> findByPropiedadIdAndFecha(Long idPropiedad, LocalDate fecha);
    
    // Disponibilidad en un rango de Fechas 
    @Query("SELECT d FROM DisponibilidadModel d WHERE d.propiedad.id = :idPropiedad AND d.fecha BETWEEN :inicio AND :fin AND d.disponible = true")
    List<DisponibilidadModel> findDisponibleByPropiedadAndRangoFechas(
        @Param("idPropiedad") Long idPropiedad,
        @Param("inicio") LocalDate fechaInicio,
        @Param("fin") LocalDate fechaFin
    );
}
