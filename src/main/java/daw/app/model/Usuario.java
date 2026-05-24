package daw.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private LocalDate fechaRegistro;


    public enum Rol {
        HUESPED, ANFITRION, ADMIN
    }

    public Usuario() {

    }

    public Usuario(Long idUsuario, String nombre, String email, String passwordHash, Rol rol, LocalDate fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(Usuario other) {
        if (other == null) return;
        this.idUsuario = other.idUsuario;
        this.nombre = other.nombre;
        this.email = other.email;
        this.passwordHash = other.passwordHash;
        this.rol = other.rol;
        this.fechaRegistro = other.fechaRegistro;
    }


    public Long getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPasswordHash(){
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public Rol getRol(){
        return rol;
    }
    public void setRol(Rol rol){
        this.rol = rol;
    }

    public LocalDate getFechaRegistro(){
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro){
        this.fechaRegistro = fechaRegistro;
    }
}