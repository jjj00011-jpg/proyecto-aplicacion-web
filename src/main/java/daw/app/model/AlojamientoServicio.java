package daw.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;

import java.io.Serializable;

@Entity
@Table(
        name = "alojamiento_servicio",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_alojamiento", "id_servicio"})
)
public class AlojamientoServicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El id del alojamiento es obligatorio.")
    @Column(name = "id_alojamiento")   // ← añadir esto
    private Long idAlojamiento;

    @NotNull(message = "El id del servicio es obligatorio.")
    @Column(name = "id_servicio")      // ← añadir esto
    private Long idServicio;
    public AlojamientoServicio() {
    }

    public AlojamientoServicio(Long idAlojamiento, Long idServicio) {
        this.idAlojamiento = idAlojamiento;
        this.idServicio = idServicio;
    }

    public AlojamientoServicio(AlojamientoServicio other) {
        if (other == null) return;
        this.id = other.id;
        this.idAlojamiento = other.idAlojamiento;
        this.idServicio = other.idServicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(Long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }
}