window.addEventListener("load", () => {
    const mapBox = rujaById("homeMap");
    if (!mapBox) return;

    // Centro aproximado de la provincia de Jaén
    const lat = 37.7796;
    const lng = -3.7849;
    const delta = 0.08;

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
            title="Mapa de alojamientos en Jaén">
        </iframe>
    `;
});