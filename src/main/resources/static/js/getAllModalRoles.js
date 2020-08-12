$(document).ready(function () {
    getAllRolesForModal();
});

function getAllRolesForModal() {
    fetch("/rest/roles").then(function (response) {
        response.json().then(function (data) {
            var selectBody = "";
            selectBody = $('#inputModalRole');
            $(data).each(function (i, role) {
                selectBody.append(`<option value="${role.id}">${role.role}</option>`);
            })
        })
    });
}