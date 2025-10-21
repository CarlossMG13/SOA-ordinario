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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "propiedad_imagenes")
public class PropiedadImgModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;

    // FK Propiedad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false) 
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad; // Campo de relación

    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

    // Callback JPA: antes de insertar
    @PrePersist
    public void onPrePersist(){
        if (this.fechaSubida == null){
            this.fechaSubida = LocalDateTime.now();
        }
    }

    // ------------------------------------------------------------------
    // Constructores
    // ------------------------------------------------------------------

    public PropiedadImgModel(){
    }

    public PropiedadImgModel(PropiedadModel propiedad, String urlImagen, Integer orden, Boolean esPrincipal) {
        this.propiedad = propiedad;
        this.urlImagen = urlImagen;
        this.orden = orden;
        this.esPrincipal = esPrincipal;
    }

    // ------------------------------------------------------------------
    // Getters y Setters 
    // ------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}