package daw.app.vm;

import daw.app.dao.AlojamientoDao;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import daw.app.dao.qualifier.DAOJData;

import java.util.List;

@Named("alojamientosVM")
@RequestScoped
public class AlojamientosVM {

    @Inject
    @DAOJData
    private AlojamientoDao dao;

    public List<Alojamiento> getLista() {
        return dao.findAll();
    }

    public String borrar(Long id) {
        if (id != null) {
            dao.delete(id);
        }
        return "admin-alojamientos?faces-redirect=true";
    }
}