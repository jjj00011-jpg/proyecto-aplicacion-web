package daw.app.vm;

import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Servicio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("serviciosVM")
@RequestScoped
public class ServiciosVM {

    @Inject
    @DAOJData
    private ServicioDao dao;

    public List<Servicio> getLista() {
        return dao.findAll();
    }

    public String borrar(Long id) {
        if (id != null) {
            dao.delete(id);
        }
        return "admin-servicios?faces-redirect=true";
    }
}