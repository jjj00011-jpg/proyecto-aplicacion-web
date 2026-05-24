import { createApp } from "https://unpkg.com/vue@3/dist/vue.esm-browser.js";

function obtenerContextPath() {
    const path = window.location.pathname;
    const index = path.indexOf("/", 1);
    return index > 0 ? path.substring(0, index) : "";
}

const contextPath = obtenerContextPath();

createApp({
    data() {
        return {
            alojamientos: [],
            filtros: {
                ciudad: "",
                tipo: "",
                precioMax: 300
            },
            errores: [],
            cargando: false,
            errorConexion: "",
            contextPath: contextPath,
            rutaImagenDefecto: `${contextPath}/assets/img/alojamiento-default.jpg`
        };
    },

    computed: {
        alojamientosFiltrados() {
            return this.alojamientos
                .filter((a) => {
                    const ciudadFiltro = this.filtros.ciudad.toLowerCase();
                    const ciudadAlojamiento = String(a.ciudad || "").toLowerCase();

                    const coincideCiudad =
                        !ciudadFiltro || ciudadAlojamiento.includes(ciudadFiltro);

                    const coincideTipo =
                        !this.filtros.tipo || a.tipo === this.filtros.tipo;

                    const coincidePrecio =
                        Number(a.precioNoche) <= Number(this.filtros.precioMax);

                    return coincideCiudad && coincideTipo && coincidePrecio;
                })
                .sort((a, b) => Number(a.precioNoche) - Number(b.precioNoche));
        }
    },

    created() {
        this.cargarAlojamientos();
    },

    methods: {
        async cargarAlojamientos() {
            this.cargando = true;
            this.errorConexion = "";

            try {
                const response = await fetch(`${this.contextPath}/api/alojamientos`);

                if (!response.ok) {
                    throw new Error(`Error HTTP ${response.status}`);
                }

                this.alojamientos = await response.json();
            } catch (error) {
                console.error(error);
                this.errorConexion = "No se han podido cargar los alojamientos desde el servidor.";
            } finally {
                this.cargando = false;
            }
        },

        validarFiltros() {
            this.errores = [];

            if (this.filtros.ciudad && this.filtros.ciudad.length < 2) {
                this.errores.push("La ciudad debe tener al menos 2 caracteres.");
            }

            if (Number(this.filtros.precioMax) < 30) {
                this.errores.push("El precio máximo debe ser al menos 30€.");
            }

            if (Number(this.filtros.precioMax) > 300) {
                this.errores.push("El precio máximo no puede superar 300€.");
            }

            return this.errores.length === 0;
        },

        limpiarFiltros() {
            this.filtros.ciudad = "";
            this.filtros.tipo = "";
            this.filtros.precioMax = 300;
            this.errores = [];
        }
    },

    template: `
        <div>
            <section class="card shadow-sm mb-4">
                <div class="card-body">
                    <div class="d-flex flex-wrap justify-content-between align-items-start gap-3">
                        <div>
                            <h1 class="h3 mb-1">Alojamientos con VueJS</h1>
                            <p class="text-muted mb-0">
                                Vista cliente desarrollada con VueJS y conectada al API REST de ReservaUJA.
                            </p>
                        </div>

                        <button class="btn ruja-btn" type="button" @click="cargarAlojamientos">
                            Recargar catálogo
                        </button>
                    </div>
                </div>
            </section>

            <section class="card shadow-sm mb-4">
                <div class="card-header fw-bold">Filtros Vue</div>

                <div class="card-body">
                    <div class="row g-3">

                        <div class="col-12 col-md-4">
                            <label class="form-label" for="vueCiudad">Ciudad</label>
                            <input id="vueCiudad"
                                   class="form-control"
                                   type="text"
                                   v-model.trim="filtros.ciudad"
                                   placeholder="Ej. Jaén, Úbeda, Baeza">
                        </div>

                        <div class="col-12 col-md-4">
                            <label class="form-label" for="vueTipo">Tipo</label>
                            <select id="vueTipo" class="form-select" v-model="filtros.tipo">
                                <option value="">Todos</option>
                                <option value="HOTEL">Hotel</option>
                                <option value="APARTAMENTO">Apartamento</option>
                            </select>
                        </div>

                        <div class="col-12 col-md-4">
                            <label class="form-label" for="vuePrecio">
                                Precio máximo: {{ filtros.precioMax }}€
                            </label>
                            <input id="vuePrecio"
                                   class="form-range"
                                   type="range"
                                   min="30"
                                   max="300"
                                   step="5"
                                   v-model.number="filtros.precioMax">
                        </div>

                        <div class="col-12">
                            <div class="d-flex flex-wrap gap-2">
                                <button class="btn ruja-btn" type="button" @click="validarFiltros">
                                    Aplicar validación
                                </button>

                                <button class="btn btn-outline-secondary" type="button" @click="limpiarFiltros">
                                    Limpiar filtros
                                </button>
                            </div>
                        </div>

                        <div class="col-12" v-if="errores.length > 0">
                            <div class="alert alert-danger mb-0">
                                <strong>Revisa los filtros:</strong>
                                <ul class="mb-0">
                                    <li v-for="error in errores" :key="error">{{ error }}</li>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            <section>
                <div v-if="cargando" class="alert alert-info">
                    Cargando alojamientos desde el servidor...
                </div>

                <div v-if="errorConexion" class="alert alert-danger">
                    {{ errorConexion }}
                </div>

                <div v-if="!cargando && alojamientosFiltrados.length === 0" class="alert alert-warning">
                    No hay alojamientos que coincidan con los filtros seleccionados.
                </div>

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <p class="text-muted mb-0">
                        {{ alojamientosFiltrados.length }} alojamiento(s) encontrados
                    </p>
                </div>

                <div class="row g-4">
                    <article class="col-12 col-md-6 col-xl-4"
                             v-for="a in alojamientosFiltrados"
                             :key="a.idAlojamiento">

                        <div class="card h-100 shadow-sm ruja-vue-card">
                            <img :src="a.fotoUrl || rutaImagenDefecto"
                                 class="ruja-vue-img"
                                 :alt="'Foto de ' + a.nombre">

                            <div class="card-body d-flex flex-column">
                                <h2 class="h5 mb-1">{{ a.nombre }}</h2>

                                <p class="text-muted mb-2">
                                    {{ a.ciudad }} · {{ a.tipo }} · {{ a.valoracionMedia }} ★
                                </p>

                                <p class="mb-3">
                                    {{ a.descripcion }}
                                </p>

                                <div class="mt-auto">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <span class="fw-bold">{{ a.precioNoche }}€/noche</span>
                                        <span class="badge text-bg-secondary">
                                            {{ a.capacidad }} huésped(es)
                                        </span>
                                    </div>

                                    <a class="btn ruja-btn w-100"
                                       :href="contextPath + '/details.xhtml?id=' + a.idAlojamiento">
                                        Ver detalle
                                    </a>
                                </div>
                            </div>
                        </div>

                    </article>
                </div>
            </section>
        </div>
    `
}).mount("#vueApp");