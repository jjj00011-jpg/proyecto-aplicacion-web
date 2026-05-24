window.addEventListener("load", () => {
    const form = rujaById("reviewForm");
    if (!form) return;

    const alojamiento = rujaById("rAlojamiento");
    const nombre = rujaById("rNombre");
    const puntuacion = rujaById("rPuntuacion");
    const comentario = rujaById("rComentario");
    const clearBtn = rujaById("clearReviewBtn");
    const summary = rujaById("reviewSummary");
    const contador = rujaById("comentarioContador");
    const reviewError = rujaById("reviewError");
    const reviewAlojamientoInfo = rujaById("reviewAlojamientoInfo");
    const reviewAlojamientoImg = rujaById("reviewAlojamientoImg");

    const params = new URLSearchParams(window.location.search);
    const idAlojamiento = params.get("id");

    let alojamientoActual = null;

    function contextPath() {
        const path = window.location.pathname;
        const index = path.indexOf("/", 1);
        return index > 0 ? path.substring(0, index) : "";
    }

    function mostrarError(mensaje) {
        if (!reviewError) return;
        reviewError.textContent = mensaje;
        reviewError.classList.remove("d-none");
    }

    function ocultarError() {
        if (!reviewError) return;
        reviewError.textContent = "";
        reviewError.classList.add("d-none");
    }

    function actualizarContador() {
        const longitud = comentario.value.length;
        contador.textContent = `${longitud}/500 caracteres`;
    }

    function pintarAlojamiento() {
        if (!alojamientoActual) {
            alojamiento.value = "";
            reviewAlojamientoInfo.textContent = "Selecciona un alojamiento desde la página de detalle para reseñarlo.";
            reviewAlojamientoImg.src = `${contextPath()}/assets/img/alojamiento-default.jpg`;
            reviewAlojamientoImg.alt = "Imagen del alojamiento";
            return;
        }

        alojamiento.value = alojamientoActual.nombre;
        reviewAlojamientoInfo.textContent =
            `${alojamientoActual.ciudad} · ${alojamientoActual.tipo} · ${alojamientoActual.valoracionMedia} ★`;

        reviewAlojamientoImg.src =
            alojamientoActual.fotoUrl || `${contextPath()}/assets/img/alojamiento-default.jpg`;
        reviewAlojamientoImg.alt = `Imagen de ${alojamientoActual.nombre}`;
    }

    async function cargarAlojamiento() {
        ocultarError();

        if (!idAlojamiento) {
            pintarAlojamiento();
            return;
        }

        try {
            const response = await fetch(`${contextPath()}/api/alojamientos/${idAlojamiento}`);

            if (!response.ok) {
                throw new Error(`Error HTTP ${response.status}`);
            }

            alojamientoActual = await response.json();
            pintarAlojamiento();
        } catch (error) {
            console.error(error);
            mostrarError("No se ha podido cargar el alojamiento seleccionado para la reseña.");
            pintarAlojamiento();
        }
    }

    function validarReview() {
        let valido = true;

        [alojamiento, nombre, puntuacion, comentario].forEach(rujaClearValidation);

        if (!alojamiento.value.trim() || alojamiento.value.trim().length < 2) {
            rujaSetInvalid(alojamiento, "Indica el alojamiento valorado.");
            valido = false;
        } else {
            rujaSetValid(alojamiento);
        }

        if (nombre.value.trim() && nombre.value.trim().length < 2) {
            rujaSetInvalid(nombre, "El nombre debe tener al menos 2 caracteres o dejarse vacío.");
            valido = false;
        } else if (nombre.value.trim()) {
            rujaSetValid(nombre);
        }

        if (!puntuacion.value) {
            rujaSetInvalid(puntuacion, "Selecciona una puntuación.");
            valido = false;
        } else {
            rujaSetValid(puntuacion);
        }

        const texto = comentario.value.trim();
        if (texto.length < 10) {
            rujaSetInvalid(comentario, "El comentario debe tener al menos 10 caracteres.");
            valido = false;
        } else if (texto.length > 500) {
            rujaSetInvalid(comentario, "El comentario no puede superar 500 caracteres.");
            valido = false;
        } else {
            rujaSetValid(comentario);
        }

        actualizarContador();
        return valido;
    }

    function pintarResumen() {
        rujaById("sReviewAlojamiento").textContent = alojamiento.value.trim();
        rujaById("sReviewNombre").textContent = nombre.value.trim() || "(anónimo)";
        rujaById("sReviewPuntuacion").textContent = `${puntuacion.value}/5`;
        rujaById("sReviewComentario").textContent = comentario.value.trim();

        summary.classList.remove("d-none");
    }

    [alojamiento, nombre, puntuacion, comentario].forEach((input) => {
        input.addEventListener("input", () => {
            validarReview();
            summary.classList.add("d-none");
        });

        input.addEventListener("change", () => {
            validarReview();
            summary.classList.add("d-none");
        });
    });

    clearBtn.addEventListener("click", () => {
        nombre.value = "";
        puntuacion.value = "";
        comentario.value = "";

        if (alojamientoActual) {
            alojamiento.value = alojamientoActual.nombre;
        } else {
            alojamiento.value = "";
        }

        summary.classList.add("d-none");

        [alojamiento, nombre, puntuacion, comentario].forEach(rujaClearValidation);
        actualizarContador();
    });

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        if (!validarReview()) {
            summary.classList.add("d-none");
            return;
        }

        pintarResumen();
    });

    cargarAlojamiento();
    actualizarContador();
});