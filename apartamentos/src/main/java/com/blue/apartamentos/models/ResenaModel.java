package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne; // CAMBIO CLAVE: OneToOne
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "resenas")
public class ResenaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long id;

    // FK: id_reservacion
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false, unique = true) // 'unique = true' refuerza la relación 1:1 a nivel de columna
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservacionModel reservacion; 
    
    @Column(name = "calificacion_limpieza", nullable = false)
    private Integer calificacionLimpieza;

    @Column(name = "calificacion_ubicacion", nullable = false)
    private Integer calificacionUbicacion;

    @Column(name = "calificacion_comunicacion", nullable = false)
    private Integer calificacionComunicacion;

    @Column(name = "calificacion_general", nullable = false)
    private Integer calificacionGeneral;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_resena", nullable = false)
    private LocalDateTime fechaResena;

    @Column(name = "respuesta_propietario", columnDefinition = "TEXT")
    private String respuestaPropietario;

    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;

    // Callback JPA: Asigna la fecha de creación antes de persistir
    @PrePersist
    public void onPrePersist(){
        if (this.fechaResena == null){
            this.fechaResena = LocalDateTime.now();
        }
    }

    // ------------------------------------------------------------------
    // Constructores y Getters/Setters
    // ------------------------------------------------------------------

    
    public ResenaModel() {}

    public ResenaModel(ReservacionModel reservacion, 
                       Integer calificacionLimpieza, 
                       Integer calificacionUbicacion, 
                       Integer calificacionComunicacion, 
                       Integer calificacionGeneral, 
                       String comentario) {
        this.reservacion = reservacion;
        this.calificacionLimpieza = calificacionLimpieza;
        this.calificacionUbicacion = calificacionUbicacion;
        this.calificacionComunicacion = calificacionComunicacion;
        this.calificacionGeneral = calificacionGeneral;
        this.comentario = comentario;
    }
    

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservacionModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionModel reservacion) {
        this.reservacion = reservacion;
    }

    public Integer getCalificacionLimpieza() {
        return calificacionLimpieza;
    }

    public void setCalificacionLimpieza(Integer calificacionLimpieza) {
        this.calificacionLimpieza = calificacionLimpieza;
    }

    public Integer getCalificacionUbicacion() {
        return calificacionUbicacion;
    }

    public void setCalificacionUbicacion(Integer calificacionUbicacion) {
        this.calificacionUbicacion = calificacionUbicacion;
    }

    public Integer getCalificacionComunicacion() {
        return calificacionComunicacion;
    }

    public void setCalificacionComunicacion(Integer calificacionComunicacion) {
        this.calificacionComunicacion = calificacionComunicacion;
    }

    public Integer getCalificacionGeneral() {
        return calificacionGeneral;
    }

    public void setCalificacionGeneral(Integer calificacionGeneral) {
        this.calificacionGeneral = calificacionGeneral;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDateTime fechaResena) {
        this.fechaResena = fechaResena;
    }

    public String getRespuestaPropietario() {
        return respuestaPropietario;
    }

    public void setRespuestaPropietario(String respuestaPropietario) {
        this.respuestaPropietario = respuestaPropietario;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}
