package daw.app.dao.jdata;

import daw.app.model.AlojamientoServicio;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

import java.util.List;

@Repository
public interface AlojamientoServicioRepository extends CrudRepository<AlojamientoServicio, Long> {

    @Find
    List<AlojamientoServicio> findByIdAlojamiento(@By("idAlojamiento") Long idAlojamiento);
}