package daw.app.dao.jdata;

import daw.app.model.Servicio;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Long> {
}