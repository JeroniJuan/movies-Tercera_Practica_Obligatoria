function moveItem(fromId, toId) {
    const fromSelect = document.getElementById(fromId);
    const toSelect = document.getElementById(toId);

    Array.from(fromSelect.selectedOptions).forEach(option => {
        toSelect.appendChild(option);
    });
}
function selectAllOptions() {
    document.querySelectorAll("select[multiple]").forEach(select => {
        Array.from(select.options).forEach(option => option.selected = true);
    });
}
function moveItemWithRole(fromId, toId, roleId) {
        let fromSelect = document.getElementById(fromId);
        let toSelect = document.getElementById(toId);
        let selectedOptions = Array.from(fromSelect.selectedOptions);

        selectedOptions.forEach(option => {
            let exists = Array.from(toSelect.options).some(opt => opt.value === option.value);
            if (!exists) {
                let newOption = option.cloneNode(true);
                newOption.dataset.role = roleId;
                toSelect.appendChild(newOption);
                fromSelect.removeChild(option);
            }
        });
    }