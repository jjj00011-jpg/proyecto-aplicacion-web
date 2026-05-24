package daw.app.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Resena implements Serializable {
    private int idResena;
    private int reservaId; // FK -> reserva
    private int puntuacion;
    private String comentario;
    private LocalDate fecha;

    //constructores
    public Resena(){
        this.fecha = LocalDate.now();
        this.puntuacion = 5;
    }

    public Resena(Resena otro){
        this.idResena = otro.idResena;
        this.reservaId = otro.reservaId;
        this.puntuacion = otro.puntuacion;
        this.comentario = otro.comentario;;
        this.fecha = otro.fecha;
    }

    //Getters / Setters
    public int getIdResena() { return idResena; }
    public void setIdResena(int idResena) { this.idResena = idResena;}

    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId;}

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

}
