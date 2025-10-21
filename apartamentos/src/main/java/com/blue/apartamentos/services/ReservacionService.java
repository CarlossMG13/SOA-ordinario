package com.blue.apartamentos.services;

import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.repositories.IReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService { 

    @Autowired
    private IReservacionRepository reservacionRepository;

    public ReservacionModel save(ReservacionModel reservacion) {
        return reservacionRepository.save(reservacion);
    }

    public Optional<ReservacionModel> findById(Long id) {
        return reservacionRepository.findById(id);
    }
    
    public List<ReservacionModel> findAll() {
        return reservacionRepository.findAll();
    }

    public void deleteById(Long id) {
        reservacionRepository.deleteById(id);
    }
    
    public List<ReservacionModel> findByPropiedadId(Long idPropiedad) {
        return reservacionRepository.findByPropiedadId(idPropiedad);
    }

    public List<ReservacionModel> findByUserId(Long idUsuario) {
        return reservacionRepository.findByUserId(idUsuario);
    }
    
    public Optional<ReservacionModel> findByCodigoReserva(String codigoReserva) {
        return reservacionRepository.findByCodigoReserva(codigoReserva);
    }
}
