package daw.app.dao.jdata;

import daw.app.model.Alojamiento;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface AlojamientoRepository extends CrudRepository<Alojamiento, Long> {
}