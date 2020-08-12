function fillModalForm(id){
    var url = "/rest/updateUser/"+id;
    fetch(url).then(function(response) {
        response.json().then(function(data) {
            $('#inputModalId').val(data.id);
            $('#inputModalEmail').val(data.email);
            $('#inputModalLogin').val(data.login);
            $('#inputModalPassword').val(data.password);
        });
    });
}
