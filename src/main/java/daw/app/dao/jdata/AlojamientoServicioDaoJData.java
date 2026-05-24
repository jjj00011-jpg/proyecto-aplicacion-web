package daw.app.dao.jdata;

import daw.app.dao.AlojamientoServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.AlojamientoServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
@DAOJData
public class AlojamientoServicioDaoJData implements AlojamientoServicioDao {

    @Inject
    private AlojamientoServicioRepository repo;

    @Override
    public List<AlojamientoServicio> findAll() {
        return repo.findAll().toList();
    }

    @Override
    public AlojamientoServicio findById(Long id) {
        if (id == null) {
            return null;
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<AlojamientoServicio> findByIdAlojamiento(Long idAlojamiento) {
        if (idAlojamiento == null) {
            return List.of();
        }
        return repo.findByIdAlojamiento(idAlojamiento);
    }

    @Override
    public void save(AlojamientoServicio relacion) {
        if (relacion != null) {
            repo.save(relacion);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            repo.deleteById(id);
        }
    }
}