const form = document.getElementById("converter-form");
const resultDiv = document.getElementById("result");
const errorDiv = document.getElementById("error");
const resultContainer = document.getElementById("result-container");
const errorContainer = document.getElementById("error-container");

const fromSelect = document.getElementById("from");
const toSelect = document.getElementById("to");

function updateCurrencyOptions() {
    const selectedFrom = fromSelect.value;
    const selectedTo = toSelect.value;

    Array.from(toSelect.options).forEach(option => {
        option.disabled = option.value === selectedFrom;
    });

    Array.from(fromSelect.options).forEach(option => {
        option.disabled = option.value === selectedTo;
    });
}

fromSelect.addEventListener("change", updateCurrencyOptions);
toSelect.addEventListener("change", updateCurrencyOptions);
updateCurrencyOptions();

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const amount = document.getElementById("amount").value;
    const from = document.getElementById("from").value;
    const to = document.getElementById("to").value;

    resultDiv.textContent = "";
    errorDiv.textContent = "";
    resultContainer.style.display = "none";
    errorContainer.style.display = "none";

    try {
        const response = await fetch(`/api/convert?amount=${amount}&from=${from}&to=${to}`);
        const data = await response.json();

        if (!response.ok) {
            errorDiv.textContent = data.message || "Something went wrong";
            errorContainer.style.display = "block";
            return;
        }

        resultDiv.textContent =
            `${data.amount} ${data.from} = ${data.convertedAmount} ${data.to} (Rate: ${data.rate})`;
        resultContainer.style.display = "block";
    } catch (error) {
        errorDiv.textContent = "Failed to connect to backend";
        errorContainer.style.display = "block";
    }
});