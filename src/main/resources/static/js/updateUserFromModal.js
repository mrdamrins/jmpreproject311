$(document).ready(function () {
    $('#updateUser').click(function () {
        fetchPostUpdateUser();
    });

    function fetchPostUpdateUser() {
        var roleValue = "";
        var role = document.getElementById("inputModalRole");
        if (role[role.selectedIndex].value === 2) {
            roleValue = "ROLE_ADMIN";
        } else {
            roleValue = "ROLE_USER";
        }

        // Create JSON object
        var addData = {
            email: $('#inputModalEmail').val(),
            login: $('#inputModalLogin').val(),
            password: $('#inputModalPassword').val(),
            roles: [{
                id: role[role.selectedIndex].value,
                role: roleValue,
                authority: roleValue
            }]
        };

        var url = "/rest/updateUser/"+$('#inputModalId').val();
        fetch(url, {
            method: 'put',
            body: JSON.stringify(addData),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function () {
            document.location.reload();
        })
    }
});