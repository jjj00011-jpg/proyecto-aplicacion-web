package daw.app.vm;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.AlojamientoServicioDao;
import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import daw.app.model.Servicio;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("detailsVM")
@ViewScoped
public class DetailsVM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @DAOJData
    private AlojamientoDao dao;

    @Inject
    @DAOJData
    private AlojamientoServicioDao alojamientoServicioDao;

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    private Long id;
    private Alojamiento alojamiento;
    private List<Servicio> servicios = new ArrayList<>();

    public void load() {
        servicios = new ArrayList<>();

        if (id != null) {
            alojamiento = dao.findById(id);

            if (alojamiento != null) {
                var relaciones = alojamientoServicioDao.findByIdAlojamiento(id);

                for (var relacion : relaciones) {
                    Servicio servicio = servicioDao.findById(relacion.getIdServicio());
                    if (servicio != null) {
                        servicios.add(servicio);
                    }
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }
}