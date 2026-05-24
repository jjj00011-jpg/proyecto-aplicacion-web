package daw.app.model;

import java.io.Serializable;

public class Foto implements Serializable {

    private Long idFoto;
    private String url;
    private String descripcion;
    private Long idAlojamiento;


    public Foto(Long idFoto, String url, String descripcion, Long idAlojamiento) {
        this.idFoto = idFoto;
        this.url = url;
        this.descripcion = descripcion;
        this.idAlojamiento = idAlojamiento;
    }

    public Foto(Foto other) {
        if (other == null) return;
        this.idFoto = other.idFoto;
        this.url = other.url;
        this.descripcion = other.descripcion;
        this.idAlojamiento = other.idAlojamiento;
    }

    public Long getIdFoto(){
        return idFoto;
    }
    public void setIdFoto(Long idFoto){
        this.idFoto = idFoto;
    }

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public Long getIdAlojamiento(){
        return idAlojamiento;
    }
    public void setIdAlojamiento(Long idAlojamiento){
        this.idAlojamiento = idAlojamiento;
    }
}