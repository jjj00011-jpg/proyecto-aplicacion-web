package daw.app.dao.mem;

import daw.app.dao.AlojamientoDao;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import daw.app.dao.qualifier.DAOMem;

@ApplicationScoped
@DAOMem
public class AlojamientoDaoMem implements AlojamientoDao {

    private final Map<Long, Alojamiento> data = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public AlojamientoDaoMem() {
        Alojamiento h = new Alojamiento();
        h.setNombre("Hotel Centro Jaén");
        h.setCiudad("Jaén");
        h.setDireccion("Calle Centro 1");
        h.setDescripcion("Hotel céntrico");
        h.setTipo(Alojamiento.Tipo.HOTEL);
        h.setPrecioNoche(85);
        h.setValoracionMedia(4);
        h.setCapacidad(2);
        save(h);

        Alojamiento ap = new Alojamiento();
        ap.setNombre("Apartamento Catedral");
        ap.setCiudad("Jaén");
        ap.setDireccion("Plaza Catedral 2");
        ap.setDescripcion("Apartamento cómodo y bien situado");
        ap.setTipo(Alojamiento.Tipo.APARTAMENTO);
        ap.setPrecioNoche(72);
        ap.setValoracionMedia(5);
        ap.setCapacidad(3);
        save(ap);
    }

    @Override
    public List<Alojamiento> findAll() {
        List<Alojamiento> res = new ArrayList<>();
        for (Alojamiento a : data.values()) {
            res.add(new Alojamiento(a));
        }
        return res;
    }

    @Override
    public Alojamiento findById(Long id) {
        Alojamiento a = data.get(id);
        return (a != null) ? new Alojamiento(a) : null;
    }

    @Override
    public void save(Alojamiento alojamiento) {
        if (alojamiento == null) {
            throw new IllegalArgumentException("El alojamiento no puede ser null");
        }

        if (alojamiento.getIdAlojamiento() == null) {
            alojamiento.setIdAlojamiento(seq.incrementAndGet());
        }

        data.put(alojamiento.getIdAlojamiento(), new Alojamiento(alojamiento));
    }

    @Override
    public void delete(Long id) {
        data.remove(id);
    }
}