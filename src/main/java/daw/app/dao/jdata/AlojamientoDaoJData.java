package daw.app.dao.jdata;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
@DAOJData
public class AlojamientoDaoJData implements AlojamientoDao {

    @Inject
    private AlojamientoRepository repo;

    @Override
    public List<Alojamiento> findAll() {
        return repo.findAll().toList();
    }

    @Override
    public Alojamiento findById(Long id) {
        if (id == null) {
            return null;
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Alojamiento alojamiento) {
        repo.save(alojamiento);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            repo.deleteById(id);
        }
    }
}