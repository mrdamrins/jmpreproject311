$(document).ready(function () {
    getAllRolesForAddForm();
});

function getAllRolesForAddForm() {
    fetch("/rest/roles").then(function (response) {
        response.json().then(function (data) {
            var selectBody = "";
            selectBody = $('#newUser-role');
            $(data).each(function (i, role) {
                selectBody.append(`<option value="${role.id}">${role.role}</option>`);
            })
        })
    });
}