package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.enums.EstadoPropiedad;
import com.blue.apartamentos.models.enums.TipoPropiedad;

public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {

    // Filtra por el atributo 'tipo' (que usa el enum TipoPropiedad)
    List<PropiedadModel> findByTipo(TipoPropiedad tipo);

    // Método para buscar todas las propiedades por el ID del propietario
    List<PropiedadModel> findByPropietarioId(Long propietarioId);

    // Filtra por el atributo 'estado'
    List<PropiedadModel> findByEstado(EstadoPropiedad estado);
}
