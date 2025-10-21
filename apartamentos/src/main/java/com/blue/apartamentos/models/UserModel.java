package com.blue.apartamentos.models;

import com.blue.apartamentos.models.enums.EstadoUsuario;
import com.blue.apartamentos.models.enums.TipoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private TipoUsuario tipoUsuario;

    @Column(name = "nombre", nullable = false, length = 35)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 35)
    private String apellido;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "documento_identidad", unique = true, length = 20)
    private String documentoIdentidad;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_usuario", nullable = false, length = 15)
    private EstadoUsuario estadoUsuario;

    @Column(name = "contrasena_hash", nullable = false, length = 100)
    private String contrasenaHash;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    // Constructores

    // Constructor vacío
    public UserModel() {
    }

    // Constructor completo
    public UserModel(Long id, TipoUsuario tipoUsuario, String nombre, String apellido, String email, String telefono,
            LocalDate fechaNacimiento, String documentoIdentidad, String direccion, LocalDateTime fechaRegistro,
            EstadoUsuario estadoUsuario, String contrasenaHash) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoIdentidad = documentoIdentidad;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estadoUsuario = estadoUsuario;
        this.contrasenaHash = contrasenaHash;
    }

    // Constructor con atributos esenciales
    public UserModel(TipoUsuario tipoUsuario, String nombre, String apellido, String email, String telefono, LocalDate fechaNacimiento, String documentoIdentidad, String direccion, EstadoUsuario estadoUsuario, String contrasenaHash) {
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoIdentidad = documentoIdentidad;
        this.direccion = direccion;
        this.fechaRegistro = LocalDateTime.now(); // Asignacion de la fecha actual automaticamente
        this.estadoUsuario = estadoUsuario;
        this.contrasenaHash = contrasenaHash;
    }

}
