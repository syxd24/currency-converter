// ===========================
// FLUXR — script.js
// Drop-in replacement for your existing Spring Boot backend
// ===========================

const amountInput  = document.getElementById("amount");
const fromSelect   = document.getElementById("from");
const toSelect     = document.getElementById("to");
const swapBtn      = document.getElementById("swap-btn");
const convertBtn   = document.getElementById("convert-btn");
const btnSpinner   = document.getElementById("btn-spinner");
const btnText      = convertBtn.querySelector(".btn-text");

const resultValue    = document.getElementById("result-value");
const resultSkeleton = document.getElementById("result-skeleton");
const loadingBar     = document.getElementById("loading-bar-wrapper");
const errorBox       = document.getElementById("error-box");
const errorMsg       = document.getElementById("error-msg");
const rateText       = document.getElementById("rate-text");

// ---- Helpers ----

function showLoading() {
    loadingBar.style.display    = "block";
    errorBox.style.display      = "none";
    resultValue.style.display   = "none";
    resultSkeleton.style.display = "block";
    btnText.textContent         = "Converting…";
    btnSpinner.style.display    = "inline-flex";
    convertBtn.disabled         = true;
}

function hideLoading() {
    loadingBar.style.display = "none";
    btnText.textContent      = "Convert";
    btnSpinner.style.display = "none";
    convertBtn.disabled      = false;
}

function showResult(value) {
    resultSkeleton.style.display = "none";
    resultValue.style.display    = "block";
    resultValue.textContent      = value;
    // subtle pop animation
    resultValue.animate(
        [{ opacity: 0, transform: "scale(0.95)" }, { opacity: 1, transform: "scale(1)" }],
        { duration: 280, easing: "cubic-bezier(0.16,1,0.3,1)" }
    );
}

function showError(msg) {
    resultSkeleton.style.display = "none";
    resultValue.style.display    = "block";
    resultValue.textContent      = "—";
    errorMsg.textContent         = msg;
    errorBox.style.display       = "flex";
}

function formatNumber(num) {
    // Smart formatting: no decimals for large integers (like JPY, KRW)
    if (num >= 1000 && Number.isInteger(num)) {
        return num.toLocaleString("en-US", { maximumFractionDigits: 0 });
    }
    return num.toLocaleString("en-US", { minimumFractionDigits: 2, maximumFractionDigits: 4 });
}

// ---- Prevent same currency selection ----

function updateCurrencyOptions() {
    const selectedFrom = fromSelect.value;
    const selectedTo   = toSelect.value;

    Array.from(toSelect.options).forEach(opt => {
        opt.disabled = opt.value === selectedFrom;
    });
    Array.from(fromSelect.options).forEach(opt => {
        opt.disabled = opt.value === selectedTo;
    });
}

fromSelect.addEventListener("change", updateCurrencyOptions);
toSelect.addEventListener("change", updateCurrencyOptions);
updateCurrencyOptions();

// ---- Swap currencies ----

swapBtn.addEventListener("click", () => {
    const tmp     = fromSelect.value;
    fromSelect.value = toSelect.value;
    toSelect.value   = tmp;
    updateCurrencyOptions();
    doConvert();
});

// ---- Live rate badge update ----

function updateRateBadge(from, to, rate) {
    rateText.textContent = `1 ${from} = ${formatNumber(rate)} ${to} · Live`;
}

// ---- Core convert function (calls your Spring Boot backend) ----

async function doConvert() {
    const amount = parseFloat(amountInput.value);
    const from   = fromSelect.value;
    const to     = toSelect.value;

    if (isNaN(amount) || amount <= 0) {
        showError("Please enter a valid amount greater than 0.");
        return;
    }

    showLoading();

    try {
        const response = await fetch(`/api/convert?amount=${amount}&from=${from}&to=${to}`);
        const data = await response.json();

        hideLoading();

        if (!response.ok) {
            showError(data.message || "Something went wrong. Please try again.");
            return;
        }

        // data shape from your backend: { amount, from, to, convertedAmount, rate }
        showResult(formatNumber(data.convertedAmount));
        updateRateBadge(data.from, data.to, data.rate);

    } catch (err) {
        hideLoading();
        showError("Could not reach the server. Check your connection.");
    }
}

// ---- Auto-convert on input change (debounced) ----

let debounceTimer;
function debouncedConvert() {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(doConvert, 500);
}

amountInput.addEventListener("input", debouncedConvert);
fromSelect.addEventListener("change", doConvert);
toSelect.addEventListener("change", doConvert);

// ---- Manual convert button ----
convertBtn.addEventListener("click", doConvert);

// ---- Initial load ----
doConvert();