window.addEventListener("load", () => {
    const resultsList = rujaById("resultsList");
    if (!resultsList) return;

    const loadingBox = rujaById("resultsLoading");
    const emptyBox = rujaById("resultsEmpty");
    const errorBox = rujaById("resultsError");
    const countBox = rujaById("resultsCount");

    const precioMax = rujaById("precioMax");
    const precioMaxValue = rujaById("precioMaxValue");
    const filtroHotel = rujaById("hotel");
    const filtroApartamento = rujaById("apto");
    const valoracionMin = rujaById("valoracionMin");
    const orden = rujaById("ordenResultados");
    const aplicarBtn = rujaById("aplicarFiltros");
    const limpiarBtn = rujaById("limpiarFiltros");

    let alojamientos = [];

    function contextPath() {
        const path = window.location.pathname;
        const index = path.indexOf("/", 1);
        return index > 0 ? path.substring(0, index) : "";
    }

    function mostrarCargando(mostrar) {
        if (loadingBox) {
            loadingBox.classList.toggle("d-none", !mostrar);
        }
    }

    function mostrarError(mensaje) {
        if (!errorBox) return;
        errorBox.textContent = mensaje;
        errorBox.classList.remove("d-none");
    }

    function ocultarError() {
        if (errorBox) {
            errorBox.classList.add("d-none");
            errorBox.textContent = "";
        }
    }

    function normalizarTipo(tipo) {
        return String(tipo || "").toUpperCase();
    }

    function alojamientoPasaFiltros(a) {
        const precio = Number(a.precioNoche);
        const valoracion = Number(a.valoracionMedia);
        const tipo = normalizarTipo(a.tipo);

        const permiteHotel = filtroHotel.checked;
        const permiteApartamento = filtroApartamento.checked;

        if (precio > Number(precioMax.value)) {
            return false;
        }

        if (valoracion < Number(valoracionMin.value)) {
            return false;
        }

        if (tipo === "HOTEL" && !permiteHotel) {
            return false;
        }

        if (tipo === "APARTAMENTO" && !permiteApartamento) {
            return false;
        }

        return true;
    }

    function ordenar(lista) {
        const copia = [...lista];

        switch (orden.value) {
            case "precio-asc":
                copia.sort((a, b) => Number(a.precioNoche) - Number(b.precioNoche));
                break;
            case "precio-desc":
                copia.sort((a, b) => Number(b.precioNoche) - Number(a.precioNoche));
                break;
            case "valoracion-desc":
                copia.sort((a, b) => Number(b.valoracionMedia) - Number(a.valoracionMedia));
                break;
            default:
                copia.sort((a, b) => Number(a.idAlojamiento) - Number(b.idAlojamiento));
        }

        return copia;
    }

    function pintarAlojamiento(a) {
        const id = a.idAlojamiento;
        const nombre = a.nombre || "Alojamiento sin nombre";
        const ciudad = a.ciudad || "Ciudad no indicada";
        const tipo = a.tipo || "Tipo no indicado";
        const valoracion = a.valoracionMedia ?? "-";
        const precio = a.precioNoche ?? "-";
        const capacidad = a.capacidad ?? "-";
        const fotoUrl = a.fotoUrl || `${contextPath()}/assets/img/alojamiento-default.jpg`;

        return `
        <div class="card shadow-sm mb-3 ruja-result-card">
            <div class="row g-0">
                <div class="col-12 col-md-4">
                    <img src="${fotoUrl}"
                         class="ruja-result-img"
                         alt="Foto de ${nombre}"
                         loading="lazy"/>
                </div>
    
                <div class="col-12 col-md-8">
                    <div class="card-body d-flex flex-column h-100">
                        <div>
                            <h2 class="h5 mb-1">${nombre}</h2>
                            <div class="text-muted">
                                ${tipo} · ${valoracion} ★ · ${precio}€/noche
                            </div>
                            <div class="small text-muted">
                                ${ciudad} · Capacidad: ${capacidad}
                            </div>
                        </div>
    
                        <div class="mt-auto pt-3 text-end">
                            <a class="btn ruja-btn" href="${contextPath()}/details.xhtml?id=${id}">
                                Ver detalle
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
    }

    function pintarResultados() {
        ocultarError();

        precioMaxValue.textContent = `${precioMax.value}€`;

        const filtrados = ordenar(alojamientos.filter(alojamientoPasaFiltros));

        resultsList.innerHTML = filtrados.map(pintarAlojamiento).join("");

        countBox.textContent = `${filtrados.length} alojamiento(s) encontrados`;

        emptyBox.classList.toggle("d-none", filtrados.length > 0);
    }

    async function cargarAlojamientos() {
        mostrarCargando(true);
        ocultarError();
        emptyBox.classList.add("d-none");

        try {
            const response = await fetch(`${contextPath()}/api/alojamientos`);

            if (!response.ok) {
                throw new Error(`Error HTTP ${response.status}`);
            }

            alojamientos = await response.json();
            pintarResultados();
        } catch (error) {
            console.error(error);
            resultsList.innerHTML = "";
            countBox.textContent = "No se han podido cargar los alojamientos";
            mostrarError("Error al cargar alojamientos desde el servidor.");
        } finally {
            mostrarCargando(false);
        }
    }

    precioMax.addEventListener("input", pintarResultados);
    valoracionMin.addEventListener("change", pintarResultados);
    orden.addEventListener("change", pintarResultados);
    aplicarBtn.addEventListener("click", pintarResultados);

    [filtroHotel, filtroApartamento].forEach((input) => {
        input.addEventListener("change", pintarResultados);
    });

    limpiarBtn.addEventListener("click", () => {
        precioMax.value = 300;
        filtroHotel.checked = true;
        filtroApartamento.checked = true;
        valoracionMin.value = 1;
        orden.value = "relevancia";
        pintarResultados();
    });

    cargarAlojamientos();
});