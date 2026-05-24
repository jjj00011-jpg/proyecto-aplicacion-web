window.addEventListener("load", () => {
    const form = rujaById("bookingForm");
    if (!form) return;

    const summary = rujaById("bookingSummary");

    const bNombre = rujaById("bNombre");
    const bEmail = rujaById("bEmail");
    const bTelefono = rujaById("bTelefono");
    const bEntrada = rujaById("bEntrada");
    const bSalida = rujaById("bSalida");
    const bHuespedes = rujaById("bHuespedes");
    const bPago = rujaById("bPago");
    const bCupon = rujaById("bCupon");

    const resumenNoches = rujaById("resumenNoches");
    const resumenTotal = rujaById("resumenTotal");
    const resumenPrecioNoche = rujaById("resumenPrecioNoche");
    const resumenAlojamiento = rujaById("resumenAlojamiento");
    const resumenCiudad = rujaById("resumenCiudad");
    const resumenImagen = rujaById("resumenImagen");
    const bookingError = rujaById("bookingError");

    const params = new URLSearchParams(window.location.search);
    const idAlojamiento = params.get("id");

    let alojamiento = null;
    let precioNoche = 72;

    function contextPath() {
        const path = window.location.pathname;
        const index = path.indexOf("/", 1);
        return index > 0 ? path.substring(0, index) : "";
    }

    function mostrarError(mensaje) {
        if (!bookingError) return;
        bookingError.textContent = mensaje;
        bookingError.classList.remove("d-none");
    }

    function ocultarError() {
        if (!bookingError) return;
        bookingError.textContent = "";
        bookingError.classList.add("d-none");
    }

    function validarEmail(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    function validarTelefono(telefono) {
        return /^(\+34\s?)?[6-9]\d{2}\s?\d{3}\s?\d{3}$/.test(telefono);
    }

    function calcularNoches() {
        if (!bEntrada.value || !bSalida.value) {
            return 0;
        }

        const noches = rujaDiasEntre(bEntrada.value, bSalida.value);
        return noches > 0 ? noches : 0;
    }

    function calcularTotal() {
        const noches = calcularNoches();
        let total = noches * precioNoche;

        if (bCupon.value.trim().toUpperCase() === "UJA10") {
            total = total * 0.9;
        }

        return Math.round(total);
    }

    function actualizarResumenPrecio() {
        const noches = calcularNoches();
        const total = calcularTotal();

        resumenPrecioNoche.textContent = rujaFormatEuros(precioNoche);
        resumenNoches.textContent = noches;
        resumenTotal.textContent = rujaFormatEuros(total);
    }

    function pintarAlojamiento() {
        if (!alojamiento) {
            resumenAlojamiento.textContent = "Apartamento Catedral";
            resumenCiudad.textContent = "Jaén";
            resumenImagen.src = `${contextPath()}/assets/img/alojamiento-default.jpg`;
            resumenImagen.alt = "Imagen del alojamiento";
            return;
        }

        resumenAlojamiento.textContent = alojamiento.nombre;
        resumenCiudad.textContent = alojamiento.ciudad;
        resumenImagen.src = alojamiento.fotoUrl || `${contextPath()}/assets/img/alojamiento-default.jpg`;
        resumenImagen.alt = `Imagen de ${alojamiento.nombre}`;
    }

    async function cargarAlojamiento() {
        ocultarError();

        if (!idAlojamiento) {
            pintarAlojamiento();
            actualizarResumenPrecio();
            return;
        }

        try {
            const response = await fetch(`${contextPath()}/api/alojamientos/${idAlojamiento}`);

            if (!response.ok) {
                throw new Error(`Error HTTP ${response.status}`);
            }

            alojamiento = await response.json();
            precioNoche = Number(alojamiento.precioNoche) || precioNoche;

            pintarAlojamiento();
            actualizarResumenPrecio();
        } catch (error) {
            console.error(error);
            mostrarError("No se ha podido cargar el alojamiento seleccionado. Se muestra un resumen por defecto.");
            pintarAlojamiento();
            actualizarResumenPrecio();
        }
    }

    function validarReserva() {
        let valido = true;

        [bNombre, bEmail, bTelefono, bEntrada, bSalida, bHuespedes, bPago].forEach(rujaClearValidation);

        if (bNombre.value.trim().length < 3) {
            rujaSetInvalid(bNombre, "Introduce tu nombre completo.");
            valido = false;
        } else {
            rujaSetValid(bNombre);
        }

        if (!validarEmail(bEmail.value.trim())) {
            rujaSetInvalid(bEmail, "Introduce un email válido.");
            valido = false;
        } else {
            rujaSetValid(bEmail);
        }

        if (!validarTelefono(bTelefono.value.trim())) {
            rujaSetInvalid(bTelefono, "Introduce un teléfono español válido.");
            valido = false;
        } else {
            rujaSetValid(bTelefono);
        }

        if (!bEntrada.value) {
            rujaSetInvalid(bEntrada, "Selecciona una fecha de entrada.");
            valido = false;
        } else {
            rujaSetValid(bEntrada);
        }

        if (!bSalida.value) {
            rujaSetInvalid(bSalida, "Selecciona una fecha de salida.");
            valido = false;
        } else {
            rujaSetValid(bSalida);
        }

        if (bEntrada.value && bSalida.value && new Date(bSalida.value) <= new Date(bEntrada.value)) {
            rujaSetInvalid(bSalida, "La salida debe ser posterior a la entrada.");
            valido = false;
        }

        const huespedes = Number(bHuespedes.value);
        const capacidadMaxima = alojamiento?.capacidad || 10;

        if (!Number.isInteger(huespedes) || huespedes < 1 || huespedes > capacidadMaxima) {
            rujaSetInvalid(bHuespedes, `Indica entre 1 y ${capacidadMaxima} huéspedes.`);
            valido = false;
        } else {
            rujaSetValid(bHuespedes);
        }

        if (!bPago.value) {
            rujaSetInvalid(bPago, "Selecciona un método de pago.");
            valido = false;
        } else {
            rujaSetValid(bPago);
        }

        actualizarResumenPrecio();
        return valido;
    }

    function pintarResumenReserva() {
        rujaById("sAlojamientoReserva").textContent = alojamiento?.nombre || resumenAlojamiento.textContent;
        rujaById("sNombre").textContent = bNombre.value.trim();
        rujaById("sEmail").textContent = bEmail.value.trim();
        rujaById("sTelefono").textContent = bTelefono.value.trim();
        rujaById("sEntrada").textContent = bEntrada.value;
        rujaById("sSalida").textContent = bSalida.value;
        rujaById("sHuespedes").textContent = bHuespedes.value;
        rujaById("sPago").textContent = bPago.value;
        rujaById("sCupon").textContent = bCupon.value.trim() || "(sin cupón)";
        rujaById("sNoches").textContent = calcularNoches();
        rujaById("sTotal").textContent = rujaFormatEuros(calcularTotal());

        summary.classList.remove("d-none");
    }

    [bNombre, bEmail, bTelefono, bEntrada, bSalida, bHuespedes, bPago, bCupon].forEach((input) => {
        input.addEventListener("input", () => {
            validarReserva();
            summary.classList.add("d-none");
        });

        input.addEventListener("change", () => {
            validarReserva();
            summary.classList.add("d-none");
        });
    });

    rujaById("clearBookingBtn").addEventListener("click", () => {
        form.reset();
        bHuespedes.value = 2;
        summary.classList.add("d-none");

        [bNombre, bEmail, bTelefono, bEntrada, bSalida, bHuespedes, bPago].forEach(rujaClearValidation);
        actualizarResumenPrecio();
    });

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        if (!validarReserva()) {
            summary.classList.add("d-none");
            return;
        }

        pintarResumenReserva();
    });

    cargarAlojamiento();
});