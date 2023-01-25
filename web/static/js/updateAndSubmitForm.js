function updateAndSubmit() {
    const selectedOption = document.getElementById("orderBy").value;
    const inputKey = document.getElementById("input-key");
    const inputValue = document.getElementById("input-value");
    if (selectedOption === "orderBy-time-asc") {
        inputKey.value = "orderopened";
        inputValue.value = "ASC";
    } else if (selectedOption === "orderBy-time-desc") {
        inputKey.value = "orderopened";
        inputValue.value = "DESC";
    } else if (selectedOption === "orderBy-cap-asc") {
        inputKey.value = "\"carCapacity\"";
        inputValue.value = "ASC";
    } else if (selectedOption === "orderBy-cap-desc") {
        inputKey.value = "\"carCapacity\"";
        inputValue.value = "DESC";
    } else if (selectedOption === "orderBy-length-asc") {
        inputKey.value = "length";
        inputValue.value = "ASC";
    } else if (selectedOption === "orderBy-length-desc") {
        inputKey.value = "length";
        inputValue.value = "DESC";
    }
    document.getElementById("formOrderBy").submit();
}