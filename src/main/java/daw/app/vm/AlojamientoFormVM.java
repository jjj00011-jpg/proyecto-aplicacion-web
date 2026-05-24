package daw.app.vm;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.AlojamientoServicioDao;
import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import daw.app.model.AlojamientoServicio;
import daw.app.model.Servicio;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("alojamientoFormVM")
@ViewScoped
public class AlojamientoFormVM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @DAOJData
    private AlojamientoDao dao;

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    @Inject
    @DAOJData
    private AlojamientoServicioDao alojamientoServicioDao;

    private Long id;
    private Alojamiento model = new Alojamiento();

    private List<Servicio> serviciosDisponibles = new ArrayList<>();
    private List<Long> serviciosSeleccionados = new ArrayList<>();

    public void load() {
        serviciosDisponibles = servicioDao.findAll();
        serviciosSeleccionados = new ArrayList<>();

        if (id != null) {
            Alojamiento existing = dao.findById(id);
            if (existing != null) {
                model = new Alojamiento(existing);
            }

            List<AlojamientoServicio> relaciones = alojamientoServicioDao.findByIdAlojamiento(id);
            for (AlojamientoServicio r : relaciones) {
                serviciosSeleccionados.add(r.getIdServicio());
            }
        }
    }

    public String guardar() {
        dao.save(model);

        if (model.getIdAlojamiento() != null) {
            List<AlojamientoServicio> actuales =
                    alojamientoServicioDao.findByIdAlojamiento(model.getIdAlojamiento());

            for (AlojamientoServicio r : actuales) {
                alojamientoServicioDao.delete(r.getId());
            }

            if (serviciosSeleccionados != null) {
                for (Long idServicio : serviciosSeleccionados) {
                    alojamientoServicioDao.save(
                            new AlojamientoServicio(model.getIdAlojamiento(), idServicio)
                    );
                }
            }
        }

        return "admin-alojamientos?faces-redirect=true";
    }

    public Alojamiento.Tipo[] getTipos() {
        return Alojamiento.Tipo.values();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alojamiento getModel() {
        return model;
    }

    public void setModel(Alojamiento model) {
        this.model = model;
    }

    public List<Servicio> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    public List<Long> getServiciosSeleccionados() {
        return serviciosSeleccionados;
    }

    public void setServiciosSeleccionados(List<Long> serviciosSeleccionados) {
        this.serviciosSeleccionados = serviciosSeleccionados;
    }
}