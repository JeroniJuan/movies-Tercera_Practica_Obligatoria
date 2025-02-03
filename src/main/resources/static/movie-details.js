function moveItem(fromId, toId) {
    const fromSelect = document.getElementById(fromId);
    const toSelect = document.getElementById(toId);

    Array.from(fromSelect.selectedOptions).forEach(option => {
        toSelect.appendChild(option);
    });
}
