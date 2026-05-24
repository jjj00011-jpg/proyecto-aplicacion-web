package daw.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
public class Alojamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlojamiento;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres.")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los {max} caracteres.")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotBlank(message = "La dirección es obligatoria.")
    @Size(min = 2, max = 150, message = "La dirección debe tener entre {min} y {max} caracteres.")
    private String direccion;

    @NotBlank(message = "La ciudad es obligatoria.")
    @Size(min = 2, max = 80, message = "La ciudad debe tener entre {min} y {max} caracteres.")
    private String ciudad;

    @Min(value = 1, message = "El precio por noche debe ser al menos {value}.")
    @Max(value = 10000, message = "El precio por noche no puede superar {value}.")
    private int precioNoche;

    @Min(value = 1, message = "La valoración debe estar entre 1 y 5.")
    @Max(value = 5, message = "La valoración debe estar entre 1 y 5.")
    private int valoracionMedia;

    @Min(value = 1, message = "La capacidad debe ser al menos {value}.")
    @Max(value = 50, message = "La capacidad no puede superar {value}.")
    private int capacidad;

    @Size(max = 500, message = "La URL de la foto no puede superar los {max} caracteres.")
    private String fotoUrl;

    private Double latitud;

    private Double longitud;

    private Long idAnfitrion;

    public enum Tipo {
        HOTEL, APARTAMENTO
    }

    public Alojamiento() {}

    public Alojamiento(String nombre, String descripcion, Tipo tipo, String direccion, String ciudad,
                       int precioNoche, int valoracionMedia, int capacidad,
                       String fotoUrl, Double latitud, Double longitud, Long idAnfitrion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.precioNoche = precioNoche;
        this.valoracionMedia = valoracionMedia;
        this.capacidad = capacidad;
        this.fotoUrl = fotoUrl;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idAnfitrion = idAnfitrion;
    }

    public Alojamiento(Long idAlojamiento, String nombre, String descripcion, Tipo tipo, String direccion, String ciudad,
                       int precioNoche, int valoracionMedia, int capacidad,
                       String fotoUrl, Double latitud, Double longitud, Long idAnfitrion) {
        this.idAlojamiento = idAlojamiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.precioNoche = precioNoche;
        this.valoracionMedia = valoracionMedia;
        this.capacidad = capacidad;
        this.fotoUrl = fotoUrl;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idAnfitrion = idAnfitrion;
    }

    public Alojamiento(Alojamiento other) {
        if (other != null) {
            this.idAlojamiento = other.idAlojamiento;
            this.nombre = other.nombre;
            this.descripcion = other.descripcion;
            this.tipo = other.tipo;
            this.direccion = other.direccion;
            this.ciudad = other.ciudad;
            this.precioNoche = other.precioNoche;
            this.valoracionMedia = other.valoracionMedia;
            this.capacidad = other.capacidad;
            this.fotoUrl = other.fotoUrl;
            this.latitud = other.latitud;
            this.longitud = other.longitud;
            this.idAnfitrion = other.idAnfitrion;
        }
    }

    public Long getIdAlojamiento() { return idAlojamiento; }
    public void setIdAlojamiento(Long idAlojamiento) { this.idAlojamiento = idAlojamiento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public int getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(int precioNoche) { this.precioNoche = precioNoche; }

    public int getValoracionMedia() { return valoracionMedia; }
    public void setValoracionMedia(int valoracionMedia) { this.valoracionMedia = valoracionMedia; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public Long getIdAnfitrion() { return idAnfitrion; }
    public void setIdAnfitrion(Long idAnfitrion) { this.idAnfitrion = idAnfitrion; }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}