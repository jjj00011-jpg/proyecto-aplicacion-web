window.addEventListener("load", () => {
    const mapBox = rujaById("detailMap");
    if (!mapBox) return;

    const lat = Number(mapBox.dataset.lat);
    const lng = Number(mapBox.dataset.lng);

    if (!lat || !lng) {
        mapBox.innerHTML = "<div class='alert alert-warning mb-0'>No hay coordenadas disponibles para este alojamiento.</div>";
        return;
    }

    const delta = 0.006;
    const left = lng - delta;
    const right = lng + delta;
    const top = lat + delta;
    const bottom = lat - delta;

    const mapUrl =
        `https://www.openstreetmap.org/export/embed.html?bbox=${left},${bottom},${right},${top}&layer=mapnik&marker=${lat},${lng}`;

    mapBox.innerHTML = `
        <iframe
            class="ruja-map-frame"
            src="${mapUrl}"
            loading="lazy"
            title="Mapa del alojamiento">
        </iframe>
    `;
});