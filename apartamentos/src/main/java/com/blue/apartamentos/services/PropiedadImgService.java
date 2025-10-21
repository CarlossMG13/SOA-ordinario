package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.PropiedadImgModel;
import com.blue.apartamentos.repositories.IPropiedadImgRepository;

@Service
public class PropiedadImgService {
    
    @Autowired
    private IPropiedadImgRepository propiedadImgRepository;

    public List<PropiedadImgModel> findByPropiedadId(Long idPropiedad) {
        return propiedadImgRepository.findByPropiedadId(idPropiedad); 
    }

    public PropiedadImgModel save(PropiedadImgModel imagen) {
        return propiedadImgRepository.save(imagen);
    }
    
    public void deleteById(Long id) {
        propiedadImgRepository.deleteById(id);
    }

    public Optional<PropiedadImgModel> findById(Long id) {
        return propiedadImgRepository.findById(id);
    }

}
