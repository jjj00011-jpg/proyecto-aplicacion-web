package daw.app.rest;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import daw.app.dao.AlojamientoServicioDao;
import daw.app.dao.ServicioDao;
import daw.app.model.Servicio;

import java.util.ArrayList;
@Path("/alojamientos")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AlojamientosResource {

    @Inject
    @DAOJData
    private AlojamientoDao alojamientoDao;

    @Inject
    @DAOJData
    private AlojamientoServicioDao alojamientoServicioDao;

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    @GET
    public List<Alojamiento> findAll() {
        return alojamientoDao.findAll();
    }

    @GET
    @Path("/{id}")
    public Alojamiento findById(@PathParam("id") Long id) {
        Alojamiento alojamiento = alojamientoDao.findById(id);

        if (alojamiento == null) {
            throw new NotFoundException("No existe alojamiento con id " + id);
        }

        return alojamiento;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Alojamiento alojamiento) {
        alojamiento.setIdAlojamiento(null);
        alojamientoDao.save(alojamiento);

        return Response
                .created(URI.create("/api/alojamientos/" + alojamiento.getIdAlojamiento()))
                .entity(alojamiento)
                .build();
    }

    @GET
    @Path("/{id}/servicios")
    public List<Servicio> findServiciosByAlojamiento(@PathParam("id") Long id) {
        Alojamiento alojamiento = alojamientoDao.findById(id);

        if (alojamiento == null) {
            throw new NotFoundException("No existe alojamiento con id " + id);
        }

        List<Servicio> servicios = new ArrayList<>();

        var relaciones = alojamientoServicioDao.findByIdAlojamiento(id);
        for (var relacion : relaciones) {
            Servicio servicio = servicioDao.findById(relacion.getIdServicio());
            if (servicio != null) {
                servicios.add(servicio);
            }
        }

        return servicios;
    }
}