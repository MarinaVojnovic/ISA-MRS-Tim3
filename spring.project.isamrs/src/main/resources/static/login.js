var urlLoginUser = "http://localhost:8080/auth/login"
$(document).ready(function() {

	$("#loginForm").submit(function(event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();

		login();

	});

});

var TOKEN_KEY = 'jwtToken';
// var emailSad;
function login() {
	console.log('login called');

	var username = $("#loginusername").val();
	var password = $("#loginpassword").val();

	$.ajax({

		dataType : 'json',
		url : urlLoginUser,
		type : 'POST',
		contentType : 'application/json',
		data : userDtoToJson(username, password),
		success : function(data) {
			if (data.message != undefined) {
				alert(data.message);
			} else {
				setJwtToken(TOKEN_KEY, data.accessToken);
				if (data.userRoleName == "ROLE_HOTEL_ADMIN") {
					window.location.href = "hoteladmin.html";
				} else if (data.userRoleName == "ROLE_AIRLINE_ADMIN") {
					window.location.href = "airlineadmin.html";
				} else if (data.userRoleName == "ROLE_RENTACAR_ADMIN") {
					window.location.href = "rentacaradmin.html";
				} else {
					window.location.href = "sysadmin.html";
				}
			}
		},
		error : function(e) {
			alert('user not found');
			console.log('error');

		}
	});
}
function userDtoToJson(username, password) {
	return JSON.stringify({
		"username" : username,
		"password" : password,

	})
}
