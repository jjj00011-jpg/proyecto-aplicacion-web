package daw.app.vm;

import daw.app.dao.AlojamientoDao;
import daw.app.dao.AlojamientoServicioDao;
import daw.app.dao.ServicioDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import daw.app.model.Servicio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Named("dashboardVM")
@RequestScoped
public class DashboardVM {

    @Inject
    @DAOJData
    private AlojamientoDao alojamientoDao;

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    @Inject
    @DAOJData
    private AlojamientoServicioDao alojamientoServicioDao;

    private List<Alojamiento> alojamientos() {
        return alojamientoDao.findAll();
    }

    public long getTotalAlojamientos() {
        return alojamientos().size();
    }

    public long getTotalHoteles() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.HOTEL)
                .count();
    }

    public long getTotalApartamentos() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.APARTAMENTO)
                .count();
    }

    public long getTotalServicios() {
        return servicioDao.findAll().size();
    }

    public double getPrecioMedioGeneral() {
        return alojamientos().stream()
                .mapToInt(Alojamiento::getPrecioNoche)
                .average()
                .orElse(0);
    }

    public double getValoracionMediaGeneral() {
        return alojamientos().stream()
                .mapToInt(Alojamiento::getValoracionMedia)
                .average()
                .orElse(0);
    }

    public double getPrecioMedioHoteles() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.HOTEL)
                .mapToInt(Alojamiento::getPrecioNoche)
                .average()
                .orElse(0);
    }

    public double getPrecioMedioApartamentos() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.APARTAMENTO)
                .mapToInt(Alojamiento::getPrecioNoche)
                .average()
                .orElse(0);
    }

    public double getCapacidadMediaHoteles() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.HOTEL)
                .mapToInt(Alojamiento::getCapacidad)
                .average()
                .orElse(0);
    }

    public double getCapacidadMediaApartamentos() {
        return alojamientos().stream()
                .filter(a -> a.getTipo() == Alojamiento.Tipo.APARTAMENTO)
                .mapToInt(Alojamiento::getCapacidad)
                .average()
                .orElse(0);
    }

    public String getEtiquetasServiciosChart() {
        return servicioDao.findAll().stream()
                .map(Servicio::getNombre)
                .map(this::jsString)
                .collect(Collectors.joining(","));
    }

    public String getDatosServiciosChart() {
        var relaciones = alojamientoServicioDao.findAll();

        return servicioDao.findAll().stream()
                .map(servicio -> {
                    long total = relaciones.stream()
                            .filter(r -> r.getIdServicio().equals(servicio.getIdServicio()))
                            .count();
                    return String.valueOf(total);
                })
                .collect(Collectors.joining(","));
    }

    public String getEtiquetasValoracionesChart() {
        return alojamientos().stream()
                .sorted(Comparator.comparing(Alojamiento::getNombre))
                .map(Alojamiento::getNombre)
                .map(this::jsString)
                .collect(Collectors.joining(","));
    }

    public String getDatosValoracionesChart() {
        return alojamientos().stream()
                .sorted(Comparator.comparing(Alojamiento::getNombre))
                .map(a -> String.valueOf(a.getValoracionMedia()))
                .collect(Collectors.joining(","));
    }

    public String getEtiquetasCiudadesChart() {
        return alojamientos().stream()
                .map(Alojamiento::getCiudad)
                .filter(ciudad -> ciudad != null && !ciudad.isBlank())
                .distinct()
                .sorted()
                .map(this::jsString)
                .collect(Collectors.joining(","));
    }

    public String getDatosCiudadesChart() {
        List<Alojamiento> alojamientos = alojamientos();

        Map<String, Long> porCiudad = alojamientos.stream()
                .filter(a -> a.getCiudad() != null && !a.getCiudad().isBlank())
                .collect(Collectors.groupingBy(Alojamiento::getCiudad, Collectors.counting()));

        return porCiudad.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> String.valueOf(e.getValue()))
                .collect(Collectors.joining(","));
    }

    public String getEtiquetasPreciosAlojamientoChart() {
        return alojamientos().stream()
                .sorted(Comparator.comparing(Alojamiento::getPrecioNoche))
                .map(Alojamiento::getNombre)
                .map(this::jsString)
                .collect(Collectors.joining(","));
    }

    public String getDatosPreciosAlojamientoChart() {
        return alojamientos().stream()
                .sorted(Comparator.comparing(Alojamiento::getPrecioNoche))
                .map(a -> String.valueOf(a.getPrecioNoche()))
                .collect(Collectors.joining(","));
    }

    public String getPrecioMedioGeneralFormateado() {
        return String.format(Locale.US, "%.2f", getPrecioMedioGeneral());
    }

    public String getValoracionMediaGeneralFormateada() {
        return String.format(Locale.US, "%.2f", getValoracionMediaGeneral());
    }

    public String getPrecioMedioHotelesFormateado() {
        return String.format(Locale.US, "%.2f", getPrecioMedioHoteles());
    }

    public String getPrecioMedioApartamentosFormateado() {
        return String.format(Locale.US, "%.2f", getPrecioMedioApartamentos());
    }

    public String getCapacidadMediaHotelesFormateada() {
        return String.format(Locale.US, "%.2f", getCapacidadMediaHoteles());
    }

    public String getCapacidadMediaApartamentosFormateada() {
        return String.format(Locale.US, "%.2f", getCapacidadMediaApartamentos());
    }

    private String jsString(String value) {
        if (value == null) {
            return "''";
        }

        return "'" + value
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\n", " ")
                .replace("\r", " ")
                + "'";
    }
}