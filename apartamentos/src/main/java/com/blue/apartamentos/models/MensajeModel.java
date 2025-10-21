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
@Table(name = "mensajes")
public class MensajeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;

    // FK: id_remitente 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserModel remitente; 

    // FK: id_destinatario 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserModel destinatario;

    // FK: id_reservacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservacionModel reservacion;

    @Column(name = "asunto", length = 255)
    private String asunto;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    // Callback JPA: Asigna la fecha de envío y el estado inicial (no leído)
    @PrePersist
    public void onPrePersist(){
        if (this.fechaEnvio == null){
            this.fechaEnvio = LocalDateTime.now();
        }
        if (this.leido == null){
            this.leido = false;
        }
    }

    // ------------------------------------------------------------------
    // Constructores y Getters/Setters
    // ------------------------------------------------------------------

    // 1. Constructor vacío (requerido por JPA)
    public MensajeModel() {}
    
    // 2. Constructor para la creación inicial de un mensaje
    public MensajeModel(UserModel remitente, UserModel destinatario, ReservacionModel reservacion, String asunto, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.reservacion = reservacion;
        this.asunto = asunto;
        this.contenido = contenido;
        // Campos 'leido' y 'fechaEnvio' se inicializan en @PrePersist
    }

    // 3. Constructor completo
    public MensajeModel(Long id, UserModel remitente, UserModel destinatario, ReservacionModel reservacion, String asunto, String contenido, Boolean leido, LocalDateTime fechaEnvio) {
        this.id = id;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.reservacion = reservacion;
        this.asunto = asunto;
        this.contenido = contenido;
        this.leido = leido;
        this.fechaEnvio = fechaEnvio;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getRemitente() {
        return remitente;
    }

    public void setRemitente(UserModel remitente) {
        this.remitente = remitente;
    }

    public UserModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UserModel destinatario) {
        this.destinatario = destinatario;
    }

    public ReservacionModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionModel reservacion) {
        this.reservacion = reservacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
