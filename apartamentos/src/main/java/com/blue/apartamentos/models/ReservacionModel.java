package com.blue.apartamentos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.blue.apartamentos.models.enums.EstadoReservacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "reservaciones")
public class ReservacionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ide_reservacion")
    private Long id;

    // FK Propiedad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad;

    // FK User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserModel usuario; 

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "numero_huespedes", nullable = false)
    private Integer numeroHuespedes;

    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private Double precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReservacion estado;

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDateTime fechaReservacion;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "codigo_reserva", length = 50)
    private String codigoReserva;

    @Column(name = "fecha_checkin")
    private LocalDateTime fechaCheckin;

    @Column(name = "fecha_checkout")
    private LocalDateTime fechaCheckout;

    // Callback JPA: Asigna la fecha de creación y estado inicial antes de persistir
    @PrePersist
    public void onPrePersist(){
        if (this.fechaReservacion == null){
            this.fechaReservacion = LocalDateTime.now();
        }
        if (this.estado == null){
            this.estado = EstadoReservacion.PENDIENTE; 
        }
    }

    // ------------------------------------------------------------------
    // Constructores y Getters/Setters
    // ------------------------------------------------------------------

    // Constructor vacío
    public ReservacionModel() {}

    // Constructor parcial (se puede añadir uno completo si es necesario)
    public ReservacionModel(PropiedadModel propiedad, UserModel usuario, LocalDate fechaEntrada, LocalDate fechaSalida, Integer numeroHuespedes, Double precioTotal) {
        this.propiedad = propiedad;
        this.usuario = usuario;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numeroHuespedes = numeroHuespedes;
        this.precioTotal = precioTotal;
    }

    // Getters y Setters

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

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(Integer numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReservacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(LocalDateTime fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public LocalDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDateTime fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public LocalDateTime getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(LocalDateTime fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    

}
