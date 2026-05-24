window.addEventListener("load", () => {
    const form = rujaById("searchForm");
    if (!form) return;

    const summary = rujaById("searchSummary");

    const destino = rujaById("destino");
    const entrada = rujaById("entrada");
    const salida = rujaById("salida");
    const huespedes = rujaById("huespedes");
    const clearBtn = rujaById("clearBtn");

    function validarBusqueda() {
        let valido = true;

        rujaClearValidation(destino);
        rujaClearValidation(entrada);
        rujaClearValidation(salida);
        rujaClearValidation(huespedes);

        if (!destino.value.trim() || destino.value.trim().length < 2) {
            rujaSetInvalid(destino, "Introduce un destino de al menos 2 caracteres.");
            valido = false;
        } else {
            rujaSetValid(destino);
        }

        if (!entrada.value) {
            rujaSetInvalid(entrada, "Selecciona una fecha de entrada.");
            valido = false;
        } else {
            rujaSetValid(entrada);
        }

        if (!salida.value) {
            rujaSetInvalid(salida, "Selecciona una fecha de salida.");
            valido = false;
        } else {
            rujaSetValid(salida);
        }

        if (entrada.value && salida.value && new Date(salida.value) <= new Date(entrada.value)) {
            rujaSetInvalid(salida, "La fecha de salida debe ser posterior a la entrada.");
            valido = false;
        }

        const numHuespedes = Number(huespedes.value);
        if (!Number.isInteger(numHuespedes) || numHuespedes < 1 || numHuespedes > 50) {
            rujaSetInvalid(huespedes, "Indica entre 1 y 50 huéspedes.");
            valido = false;
        } else {
            rujaSetValid(huespedes);
        }

        return valido;
    }

    function actualizarResumen() {
        rujaById("sDestino").textContent = `"${destino.value.trim()}"`;
        rujaById("sEntrada").textContent = entrada.value;
        rujaById("sSalida").textContent = salida.value;
        rujaById("sHuespedes").textContent = huespedes.value;
        summary.classList.remove("d-none");
    }

    [destino, entrada, salida, huespedes].forEach((input) => {
        input.addEventListener("input", () => {
            validarBusqueda();
        });

        input.addEventListener("change", () => {
            validarBusqueda();
        });
    });

    clearBtn.addEventListener("click", () => {
        form.reset();
        huespedes.value = 2;
        summary.classList.add("d-none");

        rujaClearValidation(destino);
        rujaClearValidation(entrada);
        rujaClearValidation(salida);
        rujaClearValidation(huespedes);
    });

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        if (!validarBusqueda()) {
            summary.classList.add("d-none");
            return;
        }

        actualizarResumen();
    });
});