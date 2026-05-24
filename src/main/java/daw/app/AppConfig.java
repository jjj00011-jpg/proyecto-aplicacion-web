package daw.app;

import daw.app.dao.AlojamientoServicioDao;
import daw.app.model.AlojamientoServicio;
import daw.app.dao.AlojamientoDao;
import daw.app.dao.qualifier.DAOJData;
import daw.app.model.Alojamiento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

import daw.app.dao.UsuarioDao;
import daw.app.model.Usuario;

import daw.app.dao.ServicioDao;
import daw.app.model.Servicio;

import java.time.LocalDate;

@FacesConfig
@Named("app")
@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error",
                useForwardToLogin = false
        )
)
@ApplicationScoped
public class AppConfig {

    private final Logger log = getLogger(AppConfig.class.getName());

    private final String message = "Welcome DAW!";

    @Inject
    @DAOJData
    private AlojamientoDao alojamientoDao;

    @Inject
    @DAOJData
    private UsuarioDao usuarioDao;

    @Inject
    @DAOJData
    private ServicioDao servicioDao;

    @Inject
    @DAOJData
    private AlojamientoServicioDao alojamientoServicioDao;

    public AppConfig() {
        log.info(">>> Application starting...");
    }

    public void onStartup(@Observes Startup event) {
        log.info(">>> Application ready");

        if (alojamientoDao.findAll().isEmpty()) {
            log.info(">>> Creando catálogo inicial de alojamientos en BD");

            crearAlojamiento(
                    "Hotel Centro Jaén",
                    "Jaén",
                    "Calle Bernabé Soriano 12",
                    "Hotel urbano situado en pleno centro de Jaén, ideal para visitar la Catedral, la zona comercial y los principales monumentos.",
                    Alojamiento.Tipo.HOTEL,
                    85,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=1200&q=80",
                    37.7652,
                    -3.7896
            );

            crearAlojamiento(
                    "Apartamento Catedral",
                    "Jaén",
                    "Plaza de Santa María 4",
                    "Apartamento cómodo junto a la Catedral de Jaén, con salón amplio, cocina equipada y acceso rápido al casco histórico.",
                    Alojamiento.Tipo.APARTAMENTO,
                    72,
                    5,
                    3,
                    "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=1200&q=80",
                    37.7658,
                    -3.7904
            );

            crearAlojamiento(
                    "Hotel Castillo de Santa Catalina",
                    "Jaén",
                    "Carretera del Castillo s/n",
                    "Alojamiento con vistas panorámicas de Jaén, situado en el entorno del Castillo de Santa Catalina.",
                    Alojamiento.Tipo.HOTEL,
                    125,
                    5,
                    2,
                    "https://images.unsplash.com/photo-1571896349842-33c89424de2d?auto=format&fit=crop&w=1200&q=80",
                    37.7669,
                    -3.8068
            );

            crearAlojamiento(
                    "Apartamentos Baños Árabes",
                    "Jaén",
                    "Calle Martínez Molina 18",
                    "Apartamentos turísticos cerca de los Baños Árabes, pensados para estancias culturales y escapadas de fin de semana.",
                    Alojamiento.Tipo.APARTAMENTO,
                    68,
                    4,
                    4,
                    "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=1200&q=80",
                    37.7691,
                    -3.7918
            );

            crearAlojamiento(
                    "Hotel Universidad UJA",
                    "Jaén",
                    "Avenida Antonio Pascual Acosta 5",
                    "Hotel funcional próximo al campus de Las Lagunillas de la Universidad de Jaén, adecuado para viajes académicos y profesionales.",
                    Alojamiento.Tipo.HOTEL,
                    79,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?auto=format&fit=crop&w=1200&q=80",
                    37.7872,
                    -3.7771
            );

            crearAlojamiento(
                    "Apartamento Alameda",
                    "Jaén",
                    "Calle Alameda de Capuchinos 9",
                    "Apartamento luminoso junto a la Alameda, con buena conexión con el centro y zonas verdes cercanas.",
                    Alojamiento.Tipo.APARTAMENTO,
                    64,
                    4,
                    3,
                    "https://images.unsplash.com/photo-1493809842364-78817add7ffb?auto=format&fit=crop&w=1200&q=80",
                    37.7711,
                    -3.7967
            );

            crearAlojamiento(
                    "Apartamento Bulevar Jaén",
                    "Jaén",
                    "Paseo de España 18",
                    "Apartamento moderno en la zona del Bulevar, con fácil acceso a comercios, restaurantes y transporte urbano.",
                    Alojamiento.Tipo.APARTAMENTO,
                    70,
                    4,
                    4,
                    "https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?auto=format&fit=crop&w=1200&q=80",
                    37.7812,
                    -3.7890
            );

            crearAlojamiento(
                    "Hostal San Ildefonso",
                    "Jaén",
                    "Calle Ignacio Figueroa 6",
                    "Alojamiento sencillo y céntrico, orientado a estancias cortas y visitas turísticas a la capital.",
                    Alojamiento.Tipo.HOTEL,
                    49,
                    3,
                    2,
                    "https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=1200&q=80",
                    37.7687,
                    -3.7865
            );

            crearAlojamiento(
                    "Loft Museo Íbero",
                    "Jaén",
                    "Paseo de la Estación 31",
                    "Loft moderno próximo al Museo Íbero y a la estación, ideal para parejas o viajes de trabajo.",
                    Alojamiento.Tipo.APARTAMENTO,
                    66,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1560185127-6ed189bf02f4?auto=format&fit=crop&w=1200&q=80",
                    37.7748,
                    -3.7908
            );

            crearAlojamiento(
                    "Hotel Palacio de Úbeda",
                    "Úbeda",
                    "Plaza Vázquez de Molina 6",
                    "Hotel de estilo clásico situado en el entorno monumental de Úbeda, orientado a escapadas culturales.",
                    Alojamiento.Tipo.HOTEL,
                    118,
                    5,
                    2,
                    "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?auto=format&fit=crop&w=1200&q=80",
                    38.0082,
                    -3.3694
            );

            crearAlojamiento(
                    "Apartamento Renacimiento Úbeda",
                    "Úbeda",
                    "Calle Real 22",
                    "Apartamento turístico en una de las zonas más representativas de Úbeda, perfecto para recorrer el casco histórico.",
                    Alojamiento.Tipo.APARTAMENTO,
                    82,
                    5,
                    4,
                    "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=1200&q=80",
                    38.0110,
                    -3.3712
            );

            crearAlojamiento(
                    "Casa Patio de Úbeda",
                    "Úbeda",
                    "Calle Valencia 14",
                    "Casa con patio interior y decoración tradicional, adecuada para familias que quieran alojarse cerca del centro monumental.",
                    Alojamiento.Tipo.APARTAMENTO,
                    96,
                    4,
                    5,
                    "https://images.unsplash.com/photo-1518780664697-55e3ad937233?auto=format&fit=crop&w=1200&q=80",
                    38.0095,
                    -3.3678
            );

            crearAlojamiento(
                    "Apartamento Renacimiento Baeza",
                    "Baeza",
                    "Calle San Pablo 21",
                    "Apartamento con encanto en el centro histórico de Baeza, próximo a edificios renacentistas y zonas de restauración.",
                    Alojamiento.Tipo.APARTAMENTO,
                    74,
                    5,
                    4,
                    "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=1200&q=80",
                    37.9956,
                    -3.4689
            );

            crearAlojamiento(
                    "Hotel Puerta de Baeza",
                    "Baeza",
                    "Avenida de Andalucía 10",
                    "Hotel cómodo para visitar Baeza y su entorno patrimonial, con buenas conexiones de entrada y salida.",
                    Alojamiento.Tipo.HOTEL,
                    88,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1564501049412-61c2a3083791?auto=format&fit=crop&w=1200&q=80",
                    37.9949,
                    -3.4715
            );

            crearAlojamiento(
                    "Casa Mirador de Baeza",
                    "Baeza",
                    "Calle Compañía 7",
                    "Casa turística con vistas al entorno de la ciudad, indicada para viajes tranquilos y estancias culturales.",
                    Alojamiento.Tipo.APARTAMENTO,
                    92,
                    4,
                    5,
                    "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=1200&q=80",
                    37.9968,
                    -3.4669
            );

            crearAlojamiento(
                    "Casa Rural Cazorla Natural",
                    "Cazorla",
                    "Camino del Río Cerezuelo 14",
                    "Casa rural ubicada en Cazorla, pensada para turismo activo, rutas naturales y estancias familiares.",
                    Alojamiento.Tipo.APARTAMENTO,
                    105,
                    5,
                    6,
                    "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?auto=format&fit=crop&w=1200&q=80",
                    37.9140,
                    -3.0040
            );

            crearAlojamiento(
                    "Hotel Sierra de Cazorla",
                    "Cazorla",
                    "Carretera de la Sierra km 4",
                    "Hotel de montaña para disfrutar del Parque Natural de Cazorla, Segura y Las Villas.",
                    Alojamiento.Tipo.HOTEL,
                    110,
                    5,
                    2,
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80",
                    37.9206,
                    -2.9975
            );

            crearAlojamiento(
                    "Apartamento Balcón del Cerezuelo",
                    "Cazorla",
                    "Calle del Río Cerezuelo 8",
                    "Apartamento con encanto cerca del casco antiguo de Cazorla, ideal para parejas y escapadas de naturaleza.",
                    Alojamiento.Tipo.APARTAMENTO,
                    76,
                    4,
                    3,
                    "https://images.unsplash.com/photo-1493809842364-78817add7ffb?auto=format&fit=crop&w=1200&q=80",
                    37.9127,
                    -3.0018
            );

            crearAlojamiento(
                    "Hotel Balneario Linares",
                    "Linares",
                    "Avenida de Andalucía 28",
                    "Hotel urbano en Linares, orientado a visitas culturales, eventos y desplazamientos profesionales.",
                    Alojamiento.Tipo.HOTEL,
                    78,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=1200&q=80",
                    38.0951,
                    -3.6360
            );

            crearAlojamiento(
                    "Apartamento Minero Linares",
                    "Linares",
                    "Calle Julio Burell 15",
                    "Apartamento céntrico en Linares, cerca de zonas comerciales y espacios culturales de la ciudad.",
                    Alojamiento.Tipo.APARTAMENTO,
                    61,
                    4,
                    3,
                    "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=1200&q=80",
                    38.0967,
                    -3.6354
            );

            crearAlojamiento(
                    "Hotel Andújar Centro",
                    "Andújar",
                    "Plaza de España 3",
                    "Hotel situado en el centro de Andújar, adecuado para visitar la ciudad y el entorno de Sierra Morena.",
                    Alojamiento.Tipo.HOTEL,
                    73,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?auto=format&fit=crop&w=1200&q=80",
                    38.0392,
                    -4.0508
            );

            crearAlojamiento(
                    "Casa Sierra Morena",
                    "Andújar",
                    "Camino del Santuario km 6",
                    "Casa rural en el entorno de Sierra Morena, pensada para grupos, naturaleza y rutas al santuario.",
                    Alojamiento.Tipo.APARTAMENTO,
                    112,
                    5,
                    6,
                    "https://images.unsplash.com/photo-1518780664697-55e3ad937233?auto=format&fit=crop&w=1200&q=80",
                    38.0914,
                    -4.0475
            );

            crearAlojamiento(
                    "Casa Rural Los Olivos",
                    "Martos",
                    "Camino de la Peña 3",
                    "Alojamiento rural rodeado de olivos, ideal para familias y grupos que buscan tranquilidad cerca de Jaén.",
                    Alojamiento.Tipo.APARTAMENTO,
                    95,
                    5,
                    6,
                    "https://images.unsplash.com/photo-1518780664697-55e3ad937233?auto=format&fit=crop&w=1200&q=80",
                    37.7214,
                    -3.9712
            );

            crearAlojamiento(
                    "Hotel Peña de Martos",
                    "Martos",
                    "Avenida Pierre Cibié 11",
                    "Hotel cómodo para estancias en Martos, con acceso rápido al centro y a zonas de interés local.",
                    Alojamiento.Tipo.HOTEL,
                    69,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1564501049412-61c2a3083791?auto=format&fit=crop&w=1200&q=80",
                    37.7222,
                    -3.9689
            );

            crearAlojamiento(
                    "Hotel Sierra Mágina",
                    "Torres",
                    "Carretera de Torres a Albanchez km 2",
                    "Hotel de montaña en el entorno de Sierra Mágina, perfecto para senderismo y turismo de naturaleza.",
                    Alojamiento.Tipo.HOTEL,
                    89,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80",
                    37.7878,
                    -3.5091
            );

            crearAlojamiento(
                    "Casa Rural Mágina Alta",
                    "Mancha Real",
                    "Camino de Pegalajar 5",
                    "Casa rural con vistas a Sierra Mágina, adecuada para familias y grupos que buscan descanso y naturaleza.",
                    Alojamiento.Tipo.APARTAMENTO,
                    99,
                    4,
                    6,
                    "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=1200&q=80",
                    37.7860,
                    -3.6126
            );

            crearAlojamiento(
                    "Apartamento Fuente de la Villa",
                    "Alcalá la Real",
                    "Calle Real 32",
                    "Apartamento en Alcalá la Real, cerca del casco histórico y de la Fortaleza de la Mota.",
                    Alojamiento.Tipo.APARTAMENTO,
                    67,
                    4,
                    4,
                    "https://images.unsplash.com/photo-1560185127-6ed189bf02f4?auto=format&fit=crop&w=1200&q=80",
                    37.4636,
                    -3.9231
            );

            crearAlojamiento(
                    "Hotel Fortaleza de la Mota",
                    "Alcalá la Real",
                    "Avenida de Europa 9",
                    "Hotel tranquilo para visitar Alcalá la Real y su entorno monumental, con buenas comunicaciones.",
                    Alojamiento.Tipo.HOTEL,
                    84,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=1200&q=80",
                    37.4618,
                    -3.9246
            );

            crearAlojamiento(
                    "Hotel Puerta de Segura",
                    "La Puerta de Segura",
                    "Avenida de Andalucía 17",
                    "Hotel situado en la comarca de la Sierra de Segura, ideal para rutas naturales y turismo rural.",
                    Alojamiento.Tipo.HOTEL,
                    76,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=1200&q=80",
                    38.3520,
                    -2.7397
            );

            crearAlojamiento(
                    "Casa Rural Segura Verde",
                    "Segura de la Sierra",
                    "Calle Castillo 5",
                    "Casa rural en uno de los pueblos más representativos de la Sierra de Segura, con vistas al paisaje natural.",
                    Alojamiento.Tipo.APARTAMENTO,
                    108,
                    5,
                    5,
                    "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429?auto=format&fit=crop&w=1200&q=80",
                    38.2974,
                    -2.6534
            );

            crearAlojamiento(
                    "Apartamento Navas de Tolosa",
                    "La Carolina",
                    "Calle Real 19",
                    "Apartamento práctico en La Carolina, adecuado para viajes de paso, turismo histórico y visitas a Sierra Morena.",
                    Alojamiento.Tipo.APARTAMENTO,
                    58,
                    3,
                    3,
                    "https://images.unsplash.com/photo-1560448204-e02f11c3d0e2?auto=format&fit=crop&w=1200&q=80",
                    38.2757,
                    -3.6155
            );

            crearAlojamiento(
                    "Hotel Despeñaperros",
                    "Santa Elena",
                    "Carretera de Andalucía km 258",
                    "Hotel en el entorno de Despeñaperros, perfecto para descanso en ruta y turismo de naturaleza.",
                    Alojamiento.Tipo.HOTEL,
                    71,
                    4,
                    2,
                    "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=1200&q=80",
                    38.3418,
                    -3.5392
            );
        }

        if (usuarioDao.findByEmail("admin@reservauja.com") == null) {
            log.info(">>> Creando usuario administrador inicial en BD");

            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setEmail("admin@reservauja.com");
            admin.setPasswordHash("admin1234");
            admin.setRol(Usuario.Rol.ADMIN);
            admin.setFechaRegistro(LocalDate.now());
            usuarioDao.save(admin);
        }

        if (usuarioDao.findByEmail("host@reservauja.com") == null) {
            log.info(">>> Creando usuario anfitrión inicial en BD");

            Usuario anfitrion = new Usuario();
            anfitrion.setNombre("Anfitrión Demo");
            anfitrion.setEmail("host@reservauja.com");
            anfitrion.setPasswordHash("host1234");
            anfitrion.setRol(Usuario.Rol.ANFITRION);
            anfitrion.setFechaRegistro(LocalDate.now());
            usuarioDao.save(anfitrion);
        }

        if (servicioDao.findAll().isEmpty()) {
            log.info(">>> Creando servicios de prueba en BD");

            servicioDao.save(new Servicio("WiFi", "Internet inalámbrico"));
            servicioDao.save(new Servicio("Parking", "Aparcamiento disponible"));
            servicioDao.save(new Servicio("Desayuno", "Desayuno incluido"));
        }
        if (alojamientoServicioDao.findAll().isEmpty()) {
            log.info(">>> Creando relaciones de prueba entre alojamientos y servicios");

            var alojamientos = alojamientoDao.findAll();
            var servicios = servicioDao.findAll();

            Long hotelId = null;
            Long apartamentoId = null;
            Long wifiId = null;
            Long parkingId = null;
            Long desayunoId = null;

            for (var a : alojamientos) {
                if ("Hotel Centro Jaén".equals(a.getNombre())) {
                    hotelId = a.getIdAlojamiento();
                }
                if ("Apartamento Catedral".equals(a.getNombre())) {
                    apartamentoId = a.getIdAlojamiento();
                }
            }

            for (var s : servicios) {
                if ("WiFi".equals(s.getNombre())) {
                    wifiId = s.getIdServicio();
                }
                if ("Parking".equals(s.getNombre())) {
                    parkingId = s.getIdServicio();
                }
                if ("Desayuno".equals(s.getNombre())) {
                    desayunoId = s.getIdServicio();
                }
            }

            if (hotelId != null && wifiId != null) {
                alojamientoServicioDao.save(new AlojamientoServicio(hotelId, wifiId));
            }
            if (hotelId != null && parkingId != null) {
                alojamientoServicioDao.save(new AlojamientoServicio(hotelId, parkingId));
            }
            if (hotelId != null && desayunoId != null) {
                alojamientoServicioDao.save(new AlojamientoServicio(hotelId, desayunoId));
            }
            if (apartamentoId != null && wifiId != null) {
                alojamientoServicioDao.save(new AlojamientoServicio(apartamentoId, wifiId));
            }
        }
    }

    private void crearAlojamiento(String nombre,
                                  String ciudad,
                                  String direccion,
                                  String descripcion,
                                  Alojamiento.Tipo tipo,
                                  int precioNoche,
                                  int valoracionMedia,
                                  int capacidad,
                                  String fotoUrl,
                                  Double latitud,
                                  Double longitud) {
        Alojamiento alojamiento = new Alojamiento();
        alojamiento.setNombre(nombre);
        alojamiento.setCiudad(ciudad);
        alojamiento.setDireccion(direccion);
        alojamiento.setDescripcion(descripcion);
        alojamiento.setTipo(tipo);
        alojamiento.setPrecioNoche(precioNoche);
        alojamiento.setValoracionMedia(valoracionMedia);
        alojamiento.setCapacidad(capacidad);
        alojamiento.setFotoUrl(fotoUrl);
        alojamiento.setLatitud(latitud);
        alojamiento.setLongitud(longitud);

        alojamientoDao.save(alojamiento);
    }

    public String getWelcomeMessage() {
        return message;
    }
}