package com.blue.apartamentos.models;

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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;

import com.blue.apartamentos.models.enums.EstadoPropiedad;
import com.blue.apartamentos.models.enums.TipoPropiedad;

@Entity
@Table(name = "propiedades")
public class PropiedadModel {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id_propiedad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserModel propietario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedad", nullable = false)
    private TipoPropiedad tipo;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;

    @Column(name = "codigo_postal", nullable = false, length = 5)
    private String codigoPostal;

    @Column(name = "pais", nullable = false, length = 50)
    private String pais;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "precio_noche", nullable = false)
    private double precio_noche;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "num_habitaciones", nullable = false)
    private int num_habitaciones;

    @Column(name = "num_banos", nullable = false)
    private int num_banos;
    
    @Column(name = "metro_cuadrados", nullable = false)
    private int metro_cuadrados;

    @Column(name = "comodidades", length = 300)
    private String comodidades;
    
    @Column(name = "normas_casa", length = 300)
    private String normas_casa;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPropiedad estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fecha_actualizacion;

    //Callback JPA: antes de insertar
    @PrePersist
    public void onPrePersist(){
        if (this.fecha_creacion == null){
            this.fecha_creacion = LocalDateTime.now();
        }
        this.fecha_actualizacion = LocalDateTime.now();
    }

    //Callback JPA: antes de actualizar
    @PreUpdate
    public void onPreUpdate(){
        this.fecha_actualizacion = LocalDateTime.now();       
    }

    // Constructores

    // Constructor vacio
    public PropiedadModel(){

    }

    // Constructor solo con ID
    public PropiedadModel(Long id) {
        this.id = id;
    }

    // Constructor completo

    //Excluye: 'id' (autogenerado) y las fechas (gestionadas por @PrePersist/@PreUpdate)
    public PropiedadModel(UserModel propietario, TipoPropiedad tipo, String titulo, String descripcion, String direccion,
        String ciudad, String codigoPostal, String pais, double latitud, double longitud, double precio_noche,
        Integer capacidad, int num_habitaciones, int num_banos, int metro_cuadrados, String comodidades,
        String normas_casa, EstadoPropiedad estado){
            this.propietario = propietario;
            this.tipo = tipo;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.direccion = direccion;
            this.ciudad = ciudad;
            this.codigoPostal = codigoPostal;
            this.pais = pais;
            this.latitud = latitud;
            this.longitud = longitud;
            this.precio_noche = precio_noche;
            this.capacidad = capacidad;
            this.num_habitaciones = num_habitaciones;
            this.num_banos = num_banos;
            this.metro_cuadrados = metro_cuadrados;
            this.comodidades = comodidades;
            this.normas_casa = normas_casa;
            this.estado = estado;
    }

    // Constructor con requerimientos minimos

    //Incluye solo los campos marcados como 'nullable = false', excluyendo 'id' y las fechas.
    public PropiedadModel(UserModel propietario, TipoPropiedad tipo, String titulo, String descripcion, String direccion,
        String ciudad, String codigoPostal, String pais, double latitud, double longitud, double precio_noche,
        Integer capacidad, int num_habitaciones, int num_banos, int metro_cuadrados, EstadoPropiedad estado){
            this.propietario = propietario;
            this.tipo = tipo;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.direccion = direccion;
            this.ciudad = ciudad;
            this.codigoPostal = codigoPostal;
            this.pais = pais;
            this.latitud = latitud;
            this.longitud = longitud;
            this.precio_noche = precio_noche;
            this.capacidad = capacidad;
            this.num_habitaciones = num_habitaciones;
            this.num_banos = num_banos;
            this.metro_cuadrados = metro_cuadrados;
            this.estado = estado;               
    }


    // Getters y setters

    public Long getId_propiedad() {
        return id;
    }

    public void setId_propiedad(Long id) {
        this.id = id;
    }

    public UserModel getPropietario() {
        return propietario;
    }

    public void setPropietario(UserModel propietario) {
        this.propietario = propietario;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public int getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(int num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public int getNum_banos() {
        return num_banos;
    }

    public void setNum_banos(int num_banos) {
        this.num_banos = num_banos;
    }

    public int getMetro_cuadrados() {
        return metro_cuadrados;
    }

    public void setMetro_cuadrados(int metro_cuadrados) {
        this.metro_cuadrados = metro_cuadrados;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getNormas_casa() {
        return normas_casa;
    }

    public void setNormas_casa(String normas_casa) {
        this.normas_casa = normas_casa;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    

}
 
