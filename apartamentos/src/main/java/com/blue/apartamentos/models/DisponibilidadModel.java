package com.blue.apartamentos.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disponibilidad")
public class DisponibilidadModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long id;

    // FK: id_propiedad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad; 

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha; // Tipo 'date' en SQL

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    @Column(name = "precio_especial", precision = 10, scale = 2)
    private Double precioEspecial; // Tipo 'decimal' en SQL

    // ------------------------------------------------------------------
    // Constructores y Getters/Setters
    // ------------------------------------------------------------------

    public DisponibilidadModel() {}

    public DisponibilidadModel(PropiedadModel propiedad, LocalDate fecha, Boolean disponible, Double precioEspecial) {
        this.propiedad = propiedad;
        this.fecha = fecha;
        this.disponible = disponible;
        this.precioEspecial = precioEspecial;
    }

    // --- Getters y Setters ---

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Double getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(Double precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
}
