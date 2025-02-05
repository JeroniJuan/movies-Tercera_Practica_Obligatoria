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
 function moveLanguage() {
        let all = document.getElementById('allLanguages');
        let selected = document.getElementById('languageIds');
        let roleSelect = document.getElementById('languageRole');
        let roleId = roleSelect.value;
        let roleText = roleSelect.options[roleSelect.selectedIndex].text;
        let hiddenSelect = document.getElementById('languageRoleIds');

        for (let option of [...all.selectedOptions]) {
            let exists = [...selected.children].some(opt =>
                opt.value === option.value && opt.getAttribute('data-role') === roleId);

            if (!exists) {
                let newOption = document.createElement('option');
                newOption.value = option.value;
                newOption.text = `${option.text} (${roleText})`;
                newOption.setAttribute('data-role', roleId);
                newOption.selected = true;
                selected.appendChild(newOption);

                let hiddenOption = document.createElement('option');
                hiddenOption.value = `${option.value}:${roleId}`;
                hiddenOption.selected = true;
                hiddenSelect.appendChild(hiddenOption);

                option.selected = false;
            }
        }
        updateHiddenSelect();
    }

    function removeLanguage() {
        let selected = document.getElementById('languageIds');
        let all = document.getElementById('allLanguages');
        let hiddenSelect = document.getElementById('languageRoleIds');

        for (let option of [...selected.selectedOptions]) {
            let restoredOption = document.createElement('option');
            restoredOption.value = option.value;
            restoredOption.text = option.text.split(' (')[0];
            all.appendChild(restoredOption);

            let hiddenOptions = [...hiddenSelect.options];
            let hiddenOption = hiddenOptions.find(opt => opt.value.startsWith(option.value + ':'));
            if (hiddenOption) {
                hiddenOption.remove();
            }

            option.remove();
        }
        updateHiddenSelect();
    }

    function updateHiddenSelect() {
        let selected = document.getElementById('languageIds');
        let hiddenSelect = document.getElementById('languageRoleIds');
        hiddenSelect.innerHTML = '';

        for (let option of selected.options) {
            let languageId = option.value;
            let languageRoleId = option.getAttribute('data-role');
            let hiddenOption = document.createElement('option');
            hiddenOption.value = `${languageId}:${languageRoleId}`;
            hiddenOption.selected = true;
            hiddenSelect.appendChild(hiddenOption);
        }
    }

    document.addEventListener('DOMContentLoaded', function() {
        updateHiddenSelect();
    });