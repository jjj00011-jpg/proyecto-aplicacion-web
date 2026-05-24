function rujaById(id) {
    return document.getElementById(id);
}

function rujaClearValidation(input) {
    if (!input) return;

    input.classList.remove("is-invalid");
    input.classList.remove("is-valid");

    const feedback = input.parentElement.querySelector(".invalid-feedback");
    if (feedback) {
        feedback.textContent = "";
    }
}

function rujaSetInvalid(input, message) {
    if (!input) return;

    input.classList.add("is-invalid");
    input.classList.remove("is-valid");

    const feedback = input.parentElement.querySelector(".invalid-feedback");
    if (feedback) {
        feedback.textContent = message;
    }
}

function rujaSetValid(input) {
    if (!input) return;

    input.classList.remove("is-invalid");
    input.classList.add("is-valid");

    const feedback = input.parentElement.querySelector(".invalid-feedback");
    if (feedback) {
        feedback.textContent = "";
    }
}

function rujaFormatEuros(value) {
    return `${value}€`;
}

function rujaDiasEntre(fechaEntrada, fechaSalida) {
    const entrada = new Date(fechaEntrada);
    const salida = new Date(fechaSalida);

    const msDia = 1000 * 60 * 60 * 24;
    return Math.round((salida - entrada) / msDia);
}