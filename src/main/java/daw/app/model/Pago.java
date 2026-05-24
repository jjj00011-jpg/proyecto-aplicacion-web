package daw.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago implements Serializable {
    private int idPago;
    private int reservaId; //FK -> Reserva
    private String metodo; // enum: tarjeta | paypal | transferencia
    private String estado; // enum: pendiente | aprobado | rechazado
    private BigDecimal importe;
    private LocalDateTime fechaPago;

    // Constructores
    public Pago() {
        this.metodo =  "tarjeta";
        this.estado = "pendiente";
        this.importe = BigDecimal.ZERO;
        this.fechaPago = LocalDateTime.now();
    }

    public Pago(Pago otro){
        this.idPago = otro.idPago;
        this.reservaId = otro.reservaId;
        this.metodo = otro.metodo;
        this.estado = otro.estado;
        this.importe = otro.importe;
        this.fechaPago = otro.fechaPago;
    }

    // Getters / Setters
    public int getIdPago() { return idPago; }
    public void setIdPago() { this.idPago = idPago; }

    public int gertReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }

    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo ) { this.metodo = metodo; }

    public String getEstado() { return estado; }
    public void setEstado (String estado) { this.estado =  estado; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) {this.importe = importe; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFachaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
}
