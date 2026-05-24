package daw.app.dao;

import daw.app.model.Servicio;
import java.util.List;

public interface ServicioDao {
    List<Servicio>findAll();
    Servicio findById(Long id);
    void save(Servicio s);
    void delete(Long id);
}