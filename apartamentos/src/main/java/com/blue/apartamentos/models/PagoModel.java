package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.blue.apartamentos.models.enums.MetodoPago;
import com.blue.apartamentos.models.enums.EstadoPago;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class PagoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    // FK: id_reservacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservacionModel reservacion; 

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPago estado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "referencia_pago", length = 255)
    private String referenciaPago;

    @Column(name = "datos_pago", columnDefinition = "TEXT")
    private String datosPago;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Callback JPA: Asigna la fecha de creación y estado inicial antes de persistir
    @PrePersist
    public void onPrePersist(){
        if (this.fechaCreacion == null){
            this.fechaCreacion = LocalDateTime.now();
        }
        if (this.estado == null){
            this.estado = EstadoPago.PENDIENTE;
        }
    }

    // ------------------------------------------------------------------
    // Constructores y Getters/Setters
    // ------------------------------------------------------------------

    public PagoModel() {}

    // Constructor para la creación inicial de un pago
    public PagoModel(ReservacionModel reservacion, Double monto, MetodoPago metodoPago, String referenciaPago) {
        this.reservacion = reservacion;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.referenciaPago = referenciaPago;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(String datosPago) {
        this.datosPago = datosPago;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
