package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blue.apartamentos.models.PropiedadImgModel;

public interface IPropiedadImgRepository extends JpaRepository<PropiedadImgModel, Long> {
    @Query("SELECT i FROM PropiedadImgModel i WHERE i.propiedad.id = :idPropiedad")
    List<PropiedadImgModel> findByPropiedadId(@Param("idPropiedad") Long idPropiedad);
}
