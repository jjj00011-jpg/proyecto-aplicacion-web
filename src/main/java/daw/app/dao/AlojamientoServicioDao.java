package daw.app.dao;

import daw.app.model.AlojamientoServicio;

import java.util.List;

public interface AlojamientoServicioDao {
    List<AlojamientoServicio> findAll();
    AlojamientoServicio findById(Long id);
    List<AlojamientoServicio>findByIdAlojamiento(Long idAlojamiento);

    void save(AlojamientoServicio relacion);

    void delete(Long id);
}