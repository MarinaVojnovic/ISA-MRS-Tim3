var urlRegisterUser = "http://localhost:8080/registerUser";

$(document).ready(function () {

    $("#registerForm").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_submit();

    });

});

function fire_submit() {

    console.log('fire submit called');
    var name = $("#name").val();
    var surname = $("#surname").val();
    var email = $("#emailField").val();
    var password = $("#passwordOne").val();
    var username=$("#username").val();
    
    if (name=="" || surname=="" || email=="" || password=="" || username==""){
    	alert('None of the fields is allowed to be empty');
    }

    $("#register").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: urlRegisterUser,
        data: userToJson(name,surname,email,username, password),
        dataType: 'json',
        success: function (data) {
        	
        	console.log('user successfully registered');
        },
        error: function (e) {

             console.log("ERROR : ");
             $("#registruj").prop("disabled", false);

        }
    });
}

function userToJson(name, surname, email, username, password) {
	return JSON.stringify({
		"name" : name,
		"surname" : surname,
		"email" : email,
		"username": username,
		"password":password
	})
}

