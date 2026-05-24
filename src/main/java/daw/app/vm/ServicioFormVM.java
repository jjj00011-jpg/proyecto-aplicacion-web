package daw.app.vm;
import daw.app.dao.qualifier.DAOJData;

import daw.app.dao.ServicioDao;
import daw.app.model.Servicio;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("servicioFormVM")
@ViewScoped
public class ServicioFormVM implements Serializable{

    @Inject
    @DAOJData
    private ServicioDao dao;

    private Long id;
    private Servicio model = new Servicio();

    public void load(){
        if(id!=null){
            Servicio existing = dao.findById(id);
            if(existing!=null){
                model = new Servicio(existing); //usa el constructor de copia
                model.setIdServicio(id);
            }
        }
    }

    public String guardar(){
        dao.save(model);
        return "admin-servicios?faces-redirect=true";
    }

    //Get y set
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public Servicio getModel(){return model;}
    public void setModel(Servicio model){this.model=model;}
}