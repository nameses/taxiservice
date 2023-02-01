function checkCapacity() {
    const maxCapacity = document.getElementById("maxCapacity")
    if (maxCapacity.value === "")
        maxCapacity.disabled = true
}