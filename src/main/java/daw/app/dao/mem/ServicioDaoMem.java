package daw.app.dao.mem;

import daw.app.dao.ServicioDao;
import daw.app.model.Servicio;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ServicioDaoMem implements ServicioDao {

    private final Map<Long, Servicio> data = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public ServicioDaoMem() {
        save(new Servicio("WiFi", "Internet inalámbrico"));
        save(new Servicio("Parking", "Aparcamiento disponible"));
        save(new Servicio("Desayuno", "Desayuno incluido"));
    }

    @Override public List<Servicio> findAll() { return new ArrayList<>(data.values()); }
    @Override public Servicio findById(Long id) { return data.get(id); }

    @Override
    public void save(Servicio s) {
        if (s.getIdServicio() == null) s.setIdServicio(seq.incrementAndGet());
        data.put(s.getIdServicio(), s);
    }

    @Override
    public void delete(Long id) { data.remove(id); }
}