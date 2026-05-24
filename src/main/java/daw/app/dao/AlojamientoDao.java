package daw.app.dao;

import daw.app.model.Alojamiento;
import java.util.List;

public interface AlojamientoDao {
    List<Alojamiento> findAll();
    Alojamiento findById(Long id);
    void save(Alojamiento alojamiento);
    void delete(Long id);
}