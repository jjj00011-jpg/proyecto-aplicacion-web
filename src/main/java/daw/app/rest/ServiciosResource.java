package daw.app.rest;

import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Servicio;
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

@Path("/servicios")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ServiciosResource {

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    @GET
    public List<Servicio> findAll() {
        return servicioDao.findAll();
    }

    @GET
    @Path("/{id}")
    public Servicio findById(@PathParam("id") Long id) {
        Servicio servicio = servicioDao.findById(id);

        if (servicio == null) {
            throw new NotFoundException("No existe servicio con id " + id);
        }

        return servicio;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Servicio servicio) {
        servicio.setIdServicio(null);
        servicioDao.save(servicio);

        return Response
                .created(URI.create("/api/servicios/" + servicio.getIdServicio()))
                .entity(servicio)
                .build();
    }
}