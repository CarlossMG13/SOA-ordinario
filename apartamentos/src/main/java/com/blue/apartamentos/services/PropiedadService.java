package com.blue.apartamentos.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.UserModel;
import com.blue.apartamentos.models.enums.EstadoPropiedad;
import com.blue.apartamentos.models.enums.TipoPropiedad;
import com.blue.apartamentos.models.enums.TipoUsuario;
import com.blue.apartamentos.repositories.IPropiedadRepository;
import com.blue.apartamentos.repositories.IUserRepository;

@Service
public class PropiedadService {
    
    @Autowired
    private IPropiedadRepository propiedadRepository;

    // IUserRepository para la validacion
    @Autowired
    private IUserRepository userRepository;

    public List<PropiedadModel> getAllPropiedades(){
        return propiedadRepository.findAll();
    }

    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    } 
    
    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }

    // Propiedad por tipo
    public List<PropiedadModel> getPropiedadesByTipo(TipoPropiedad tipo) {
        return propiedadRepository.findByTipo(tipo);
    }   

    // Recuperar todas las propiedades asignadas a un usuario de tipo PROPIETARIO
    public List<PropiedadModel> getPropiedadesByPropietarioId(Long propietarioId){

        // Buscar User por Id
        Optional<UserModel> userOptional = userRepository.findById(propietarioId);

        if(userOptional.isPresent()){
            UserModel propietario = userOptional.get();

            // Validar que el tipo de usuario sea PROPIETARIO
            if (propietario.getTipoUsuario() == TipoUsuario.PROPIETARIO) {
                
                // Si la validacion pasa, llama al metodo del repositorio para la consulta
                return propiedadRepository.findByPropietarioId(propietarioId);
            }
        }

        // Si el usuario no existe o no es PROPIETARIO, devuelve una lista vacia
        return Collections.emptyList();
    }

    public List<PropiedadModel> getPropiedadesByEstado(EstadoPropiedad estado) {
        return propiedadRepository.findByEstado(estado);
    }
}
