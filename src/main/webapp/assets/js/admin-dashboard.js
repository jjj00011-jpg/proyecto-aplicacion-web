window.addEventListener("load", () => {
    const chartDefaults = {
        responsive: true,
        maintainAspectRatio: true
    };

    function getCanvas(id) {
        return document.getElementById(id);
    }

    function parseValues(canvas) {
        if (!canvas || !canvas.dataset.values) {
            return [];
        }

        return canvas.dataset.values
            .split(",")
            .map((value) => Number(value.trim()))
            .filter((value) => !Number.isNaN(value));
    }

    function parseLabels(canvas) {
        if (!canvas || !canvas.dataset.labels) {
            return [];
        }

        return canvas.dataset.labels
            .split(",")
            .map((label) => label.trim())
            .filter((label) => label.length > 0);
    }

    function createTiposChart() {
        const canvas = getCanvas("graficoTipos");
        if (!canvas) return;

        const hoteles = Number(canvas.dataset.hoteles || 0);
        const apartamentos = Number(canvas.dataset.apartamentos || 0);

        const chart = new Chart(canvas, {
            type: "doughnut",
            data: {
                labels: ["Hoteles", "Apartamentos"],
                datasets: [{
                    label: "Alojamientos",
                    data: [hoteles, apartamentos]
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Distribución de alojamientos por tipo"
                    },
                    legend: {
                        display: true,
                        position: "bottom"
                    }
                }
            }
        });

        const botonDescarga = getCanvas("descargarGraficoTipos");
        if (botonDescarga) {
            botonDescarga.addEventListener("click", () => {
                const enlace = document.createElement("a");
                enlace.href = chart.toBase64Image();
                enlace.download = "alojamientos-por-tipo.png";
                enlace.click();
            });
        }
    }

    function createPreciosChart() {
        const canvas = getCanvas("graficoPrecios");
        if (!canvas) return;

        const precioHoteles = Number(canvas.dataset.precioHoteles || 0);
        const precioApartamentos = Number(canvas.dataset.precioApartamentos || 0);

        new Chart(canvas, {
            type: "bar",
            data: {
                labels: ["Hoteles", "Apartamentos"],
                datasets: [{
                    label: "Precio medio (€)",
                    data: [precioHoteles, precioApartamentos]
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Precio medio por tipo de alojamiento"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    function createServiciosChart() {
        const canvas = getCanvas("graficoServicios");
        if (!canvas) return;

        new Chart(canvas, {
            type: "bar",
            data: {
                labels: parseLabels(canvas),
                datasets: [{
                    label: "Alojamientos asociados",
                    data: parseValues(canvas)
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Frecuencia de uso de servicios"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    function createCiudadesChart() {
        const canvas = getCanvas("graficoCiudades");
        if (!canvas) return;

        new Chart(canvas, {
            type: "bar",
            data: {
                labels: parseLabels(canvas),
                datasets: [{
                    label: "Número de alojamientos",
                    data: parseValues(canvas)
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Número de alojamientos por ciudad"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    function createValoracionesChart() {
        const canvas = getCanvas("graficoValoraciones");
        if (!canvas) return;

        new Chart(canvas, {
            type: "bar",
            data: {
                labels: parseLabels(canvas),
                datasets: [{
                    label: "Valoración media",
                    data: parseValues(canvas)
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                scales: {
                    y: {
                        min: 0,
                        max: 5
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: "Valoración media de cada alojamiento"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    function createCapacidadChart() {
        const canvas = getCanvas("graficoCapacidad");
        if (!canvas) return;

        const capacidadHoteles = Number(canvas.dataset.capacidadHoteles || 0);
        const capacidadApartamentos = Number(canvas.dataset.capacidadApartamentos || 0);

        new Chart(canvas, {
            type: "bar",
            data: {
                labels: ["Hoteles", "Apartamentos"],
                datasets: [{
                    label: "Capacidad media",
                    data: [capacidadHoteles, capacidadApartamentos]
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Capacidad media por tipo"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    function createLineaPreciosChart() {
        const canvas = getCanvas("graficoLineaPrecios");
        if (!canvas) return;

        new Chart(canvas, {
            type: "line",
            data: {
                labels: parseLabels(canvas),
                datasets: [{
                    label: "Precio por noche (€)",
                    data: parseValues(canvas),
                    tension: 0.3,
                    fill: false
                }]
            },
            options: {
                ...chartDefaults,
                plugins: {
                    title: {
                        display: true,
                        text: "Precio por noche de cada alojamiento"
                    },
                    legend: {
                        display: false
                    }
                }
            }
        });
    }

    createTiposChart();
    createPreciosChart();
    createServiciosChart();
    createCiudadesChart();
    createValoracionesChart();
    createCapacidadChart();
    createLineaPreciosChart();
});