package daw.app.dao.jdata;

import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Servicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
@DAOJData
public class ServicioDaoJData implements ServicioDao {

    @Inject
    private ServicioRepository repo;

    @Override
    public List<Servicio> findAll() {
        return repo.findAll().toList();
    }

    @Override
    public Servicio findById(Long id) {
        if (id == null) {
            return null;
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Servicio servicio) {
        if (servicio != null) {
            repo.save(servicio);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            repo.deleteById(id);
        }
    }
}