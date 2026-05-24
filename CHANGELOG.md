# Changelog
## Trabajo dirigido - Dashboard Chart.js - 2026-05-XX

### Added
- Añadido dashboard analítico de administración con Chart.js para visualizar indicadores de alojamientos, servicios, precios, capacidades y valoraciones.
- Añadida vista `admin-dashboard.xhtml` protegida mediante `AdminGuardVM` para usuarios con rol `ADMIN`.
- Añadido `DashboardVM` para preparar datos estadísticos desde los DAOs persistentes.
- Añadido `assets/js/admin-dashboard.js` para generar las gráficas en cliente con Chart.js.

### Changed
- Integrado el acceso al dashboard en la navegación de administración para usuarios con rol `ADMIN`.
- Adaptado el dashboard del trabajo dirigido al estado final del proyecto, usando el catálogo ampliado de alojamientos y los datos persistentes actuales.

## Iteración 4 - 2026-05-05

### Added
- Configuración de JAX-RS mediante `JAXRSConfiguration` para habilitar servicios REST bajo la ruta `/api`.
- Creado endpoint REST `AlojamientosResource` con operaciones para listar alojamientos, consultar detalle por id y crear alojamientos en formato JSON.
- Creado endpoint REST `ServiciosResource` con operaciones para listar servicios, consultar detalle por id y crear servicios en formato JSON.
- Añadida operación REST para consultar los servicios asociados a un alojamiento mediante `/api/alojamientos/{id}/servicios`.
- Creada carpeta `assets/js` y utilidades JavaScript comunes para validación dinámica, mensajes de error y cálculo de días.
- Sustituido el JavaScript embebido de `index.xhtml` por `assets/js/search.js`, incorporando validación dinámica en cliente y mensajes de error interactivos.
- Añadido assets/js/results.js con filtros dinámicos de precio, tipo, valoración y ordenación de alojamientos.
- Añadido assets/js/booking.js para validar reservas en cliente, calcular noches y actualizar el precio total dinámicamente.
- Añadido assets/js/review.js para validar reseñas en cliente, mostrar contador de caracteres y actualizar el resumen dinámicamente.
- Añadido manejador REST ValidationExceptionMapper para devolver errores de Bean Validation en formato JSON.
- Añadido campos fotourl,latitud y longitud a la entidad Alojamiento para soportar imágenes y mapas por alojamiento.
- Añadido `assets/js/details.js` para generar dinámicamente el mapa de cada alojamiento mediante sus coordenadas.
- Añadido mapa real en la portada mediante OpenStreetMap, generado dinámicamente desde `assets/js/home-map.js`.
- Añadida vista `vue-alojamientos.xhtml` implementada con VueJS para listar alojamientos desde el API REST, aplicar filtros reactivos y validar datos en cliente.
- Actualizado `README.md` con la memoria final de la iteración 4, incluyendo API REST, JavaScript cliente, VueJS, mapas, catálogo de alojamientos, autenticación y despliegue.

### Changed
- Actualizada results.xhtml para cargar alojamientos de forma asíncrona desde /api/alojamientos y renderizar resultados dinámicamente en cliente.
- Actualizada booking.xhtml para sustituir JavaScript embebido por lógica externa y mostrar mensajes de validación interactivos.
- Actualizados los enlaces de details.xhtml para enviar el identificador del alojamiento a las vistas de reserva y reseña.
- Actualizadas las vistas de reserva y reseña para recibir el identificador del alojamiento seleccionado desde la página de detalle.
- Ajustados estilos CSS para mejorar la visualización de validaciones dinámicas, resúmenes y tarjetas de resultados generadas en cliente.
- Ampliado el catálogo inicial con 30 alojamientos distribuidos por Jaén y municipios destacados de la provincia, incorporando fotos, coordenadas, precios, valoraciones y capacidades.
- Actualizadas las tarjetas dinámicas de resultados para mostrar la imagen principal de cada alojamiento.
- Actualizada `details.xhtml` para mostrar imagen principal, mapa real, ubicación y datos ampliados del alojamiento.
- Actualizada la reserva para cargar mediante `fetch` el alojamiento seleccionado y calcular el precio usando su precio real por noche.
- Actualizada la reseña para cargar mediante `fetch` el alojamiento seleccionado y mostrar su información real en el formulario.
- Revisados textos visibles de la interfaz para eliminar referencias a borradores, prototipos y placeholders.
- Corregidos enlaces de navegación para usar outcomes coherentes y evitar accesos sin identificador a páginas de detalle.
- Mejorada la presentación de la portada con un resumen visual de búsqueda más compacto y una integración más cuidada del bloque de mapa.
- Pulida la portada final eliminando el acceso visible de login, añadiendo subtítulo, mejorando textos y centrando las acciones principales.
- Añadido acceso de navegación a la vista Vue de alojamientos desde la barra principal.

### Fixed
- Corregida review.xhtml para eliminar la dependencia de ReviewBean, que no existía en el proyecto.
- Corregido el inicio de sesión sustituyendo el envío a `j_security_check` por autenticación mediante `HttpServletRequest.login()` desde `AuthVM`.

### Removed


## Iteración 3 - 2026-04-07

### Changed
- Configuración DataSource H2 en web.xml
- Configuración persistente con JTA y DataSource 
- Convertir Alojamiento en entidad JPA con Bean Validation
- Creado qualifier DAOJData para seleccionar DAOs Jakarta Data
- Creado qualifier DAOMem para distinguir DAOs en memoria
- AlojamientoDaoMem actualizado para escoger entre mem o bbdd
- Actualización de persistente.xml para usar el modo create y conservar datos
- Se añade un botón de cierre de sesión visible para usuarios autenticados en la plantilla principal
- Se elimina el cierre de sesión duplicado para dejarlo solo en el menú principal.
- El menú principal se ajusta para mostrar login o cierre de sesión según el estado de acceso del usuario.

### Added
- creando el repositorio Jakarta Data mínimo para Alojamiento.
- AlojamientoDaoJData implementado
- Carga automática de alojamientos en la base de datos
- Creada interfaz UsuarioDao
- Creación de usuario repositorio, para búsqueda de usuarios por email
- Creación de UsuarioDaoJData como implementación persistente del acceso a usuarios mediante UsuarioRepository
- Carga automática de usuarios de prueba en la base de datos al arrancar la aplicación mediante AppConfig
- Implemetación de ReservaIentityStore para validar credenciales de usuarios contra la base de datos y recuperar sus roles
- Configuración de autenticación por formulario en AppConfig
- Restricción de acceso para permitir el acceso solo a usuarios con rol ADMIN
- Sustitución del login 
- Se añade AuthVM para gestionar el cierre de sesión de usuarios autenticados
- Se añade ResultadosVM para mostrar en la vista pública los alojamientos almacenados en la base de datos.
- Se añade DetailsVM para cargar el detalle de un alojamiento concreto desde la base de datos.
- Las vistas results.xhtml y details.xhtml pasan a mostrar alojamientos reales almacenados en la base de datos, sustituyendo el contenido estático de prueba.
- Servicio listo en entidad JPA
- Añado ServicioRepository para gestionar servicios mediante Jakarta
- Los servicios dejan de guardarse solo en memoria y pasan a almacenarse en la base de datos.
- El formulario de servicios deja de trabajar con datos temporales y pasa a usar la base de datos.
- El listado de servicios deja de trabajar con datos temporales y pasa a usar la base de datos
- Se registra la entidad Servicio en la configuración de persistencia
- Añadidos servicios de prueba en la base de datos al iniciar la aplicación
- La relación entre alojamientos y servicios pasa a estar preparada para guardarse en la base de datos.
- Se crea la base para gestionar la relación entre alojamientos y servicios.
- Se añade el acceso a base de datos para la relación entre alojamientos y servicios.
- La relación entre alojamientos y servicios se guarda y puede consultarse en la base de datos.
- Se añaden relaciones de prueba entre alojamientos y servicios en la base de datos.
- El detalle de alojamiento pasa a preparar también los servicios asociados guardados en la base de datos.
- El detalle de alojamientos muestro los servicios guardados en la base de datos
- El formulario de alojamientos permite guardar y seleccionar servicios
- El formulario de alojamientos permite seleccionar y guardar los servicios asociados.

## Iteración 2 - 2026-03-10

### Changed

- Borrado de los archivos .html. Solo se quedan los .xhtml


### Added
- Paquete de modelos para las clases Java de la aplicación
- Esquema de las vistas de nuestra web:  
  ![Dibujo diagrama](+doc/dibujo.jpeg)

- Entidad `Usuario`.
- Entidad `Foto`.
- Entidad `Servicio`.
- Entidad `Reserva`.
- Entidad `Pago`.
- Entidad `Reseña`.
- Entidad `Alojamiento`.
- Entidad `AlojamientoServicio`.

- Creación del archivo `AlojamientoDao` dentro del paquete DAO.
- Creación de la clase `AlojamientoDaoMem` dentro del paquete MEM.
- ViewModel/controlador MVVM en Jakarta Faces: creación del paquete `daw.app.vm` y de la clase `AlojamientosVM` para listado y borrado de alojamientos. (Juan Jiménez Jaraba)
- Vista de administración `admin-alojamientos.xhtml` con listado de alojamientos y acción de borrado usando `AlojamientosVM` y `AlojamientoDaoMem`. (Juan Jiménez Jaraba)
- ViewModel `AlojamientoFormVM` para alta y edición de alojamientos, con carga por id y guardado en `AlojamientoDaoMem`. (Juan Jiménez Jaraba)
- Formulario JSF `admin-alojamiento-form.xhtml` para alta y edición de alojamientos con validación en servidor. (Juan Jiménez Jaraba)

- Creación del archivo `ServicioDao` (CRUD) dentro del paquete DAO. (Alberto Foronda)
- Creación del ViewModel `ServicioFormVM` dentro del paquete `vm`. (Alberto Foronda)
- Vista de administración `admin-servicios.xhtml` para la gestión de servicios. (Alberto Foronda)
- Formulario JSF `admin-servicio-form.xhtml` para alta y edición de servicios con validación en servidor. (Alberto Foronda)
## Iteración 1 - 2026-03-04

### Added
- Plantilla Facelets `WEB-INF/template/plantilla.xhtml` (layout dinámico) y fragmento reutilizable `WEB-INF/inc/navbar.xhtml`. (Equipo)
- Vistas JSF (Facelets) creadas:
    - `index.xhtml` (Home) (Equipo)
    - `results.xhtml` (Resultados) (Juan Jiménez Jaraba)
    - `details.xhtml` (Detalles con secciones `#hotel` y `#apto`) (Juan Jiménez Jaraba)
    - `booking.xhtml` (Reserva con validación JSF en servidor) (Ainhoa Piñar Amores)
    - `review.xhtml` (Reseña con validación JSF en servidor) (Ainhoa Piñar Amores)
    - `my-reservations.xhtml` (Mis reservas con modal Bootstrap) (Ainhoa Piñar Amores)
    - `login.xhtml` (solo vista, sin procesar envío) (Equipo)
- Beans JSF para soportar validación y mostrar datos sin persistencia:
    - `BookingBean` (Ainhoa Piñar Amores)
    - `ReviewBean` (Ainhoa Piñar Amores)

### Changed
- Navegación migrada a enlaces JSF (`h:link`/`outcome`) en las vistas JSF. (Equipo)
- Ajuste de rutas de recursos para servir `assets/css/styles.css` desde `src/main/webapp/assets/`. (Equipo)

### Fixed
- Nada

### Removed
- Nada


## Iteración 0 - 2026-02-18

### Added
- Borrador de la página de inicio `index.html` con estructura de búsqueda (ubicación/fechas/huéspedes), mapa placeholder y navegación básica. (Alberto Foronda)
- Borrador de la vista de login `pages/login.html` con formulario (email/contraseña) y enlace de vuelta al inicio. (Alberto Foronda)
- Borrador de la página de resultados `pages/results.html` con listado de alojamientos y filtros (precio, tipo y valoración). (Juan Jiménez Jaraba)
- Borrador de la página de detalle `pages/detail.html` con información del alojamiento (servicios, normas) y enlaces hacia reserva/reseña (borrador). (Juan Jiménez Jaraba)
- Borrador de la página de reserva/checkout `pages/booking.html` con formulario de datos y resumen. (Ainhoa Piñar Amores)
- Borrador de la página “Mis reservas” `pages/my-reservations.html` con listado y cancelación mediante modal. (Ainhoa Piñar Amores)
- Borrador de la página de reseña `pages/review.html` con puntuación y comentario. (Ainhoa Piñar Amores)
- Formulario en `index.html` (destino, fechas y huéspedes) con visualización de datos al pulsar “Buscar” y botón “Borrar” (sin persistencia). (Juan Jiménez Jaraba)
- Formulario en `booking.html` (datos de reserva) con visualización de datos al pulsar “Confirmar” y botón “Borrar”. (Ainhoa Piñar Amores)
- Formulario en `review.html` (nombre, puntuación y comentario) con visualización de datos al pulsar “Enviar reseña” y botón “Borrar”. (Alberto Foronda Gimena)

### Changed
- Integración de Bootstrap y aplicación de estilos base en `assets/css/styles.css` para unificar la apariencia (cabecera, botones...). (Alberto Foronda)

### Fixed
- Nada

### Removed
- Nada