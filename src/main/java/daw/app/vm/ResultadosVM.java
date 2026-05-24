package daw.app.vm;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("resultadosVM")
@RequestScoped
public class ResultadosVM {

    @Inject
    @DAOJData
    private AlojamientoDao dao;

    public List<Alojamiento> getResultados() {
        return dao.findAll();
    }
}