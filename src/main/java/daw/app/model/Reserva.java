package daw.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva implements Serializable {

    private int idReserva;
    private int usuarioId; // FK -> Usuario
    private int alojamientoId; // FK -> Alojamiento
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int huespedes;
    private String estado; // enum: pendiente | confirmada | cancelada
    private BigDecimal precioTotal;
    private LocalDateTime fechaCreacion;

    // Constructores

    public Reserva() {
        this.estado = "pendiente";
        this.fechaCreacion = LocalDateTime.now();
        this.precioTotal = BigDecimal.ZERO;
    }

    public Reserva(Reserva otro) {
        this.idReserva = otro.idReserva;
        this.usuarioId = otro.usuarioId;
        this.alojamientoId = otro.alojamientoId;
        this.fechaEntrada = otro.fechaEntrada;
        this.fechaSalida = otro.fechaSalida;
        this.huespedes = otro.huespedes;
        this.estado = otro.estado;
        this.precioTotal = otro.precioTotal;
        this.fechaCreacion = otro.fechaCreacion;
    }

    // Getters / Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getAlojamientoId() { return alojamientoId; }
    public void setAlojamientoId(int alojamientoId) { this.alojamientoId = alojamientoId; }

    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }

    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public int getHuespedes() { return huespedes; }
    public void setHuespedes(int huespedes) { this.huespedes = huespedes; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

}