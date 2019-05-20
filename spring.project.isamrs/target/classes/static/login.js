var urlLoginUser = "http://localhost:8080/auth/login"

function showMessage(message, type) {
	if (type != "success" && type != "error" && type != "warning"
			&& type != "info") {
		type = "info";
	}
	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"newestOnTop" : false,
		"progressBar" : false,
		"positionClass" : "toast-top-right",
		"preventDuplicates" : false,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
	toastr[type](message);
}

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

	var next_window;

	$.ajax({

		dataType : 'json',
		url : urlLoginUser,
		type : 'POST',
		contentType : 'application/json',
		data : userDtoToJson(username, password),
		success : function(data) {
			if (data.message != undefined) {
				showMessage(data.message, "warning");
			} else {
				setJwtToken(TOKEN_KEY, data.accessToken);
				console.log(data.userRoleName)
				if (data.userRoleName == "ROLE_HOTEL_ADMIN") {
					window.location.href = "hoteladmin.html";
				} else if (data.userRoleName == "ROLE_AIRLINE_ADMIN") {
					window.location.href = "airlineadmin.html";
				} else if (data.userRoleName == "ROLE_RENTACAR_ADMIN") {
					console.log('Role is rentacardmin');
					window.location.href = "rentacaradmin.html"
				} else if (data.userRoleName == "ROLE_USER") {
					window.location.href = "registeredUser.html";
				} else {
					window.location.href = "sysadmin.html"
				}
			}
		},
		error : function(e) {
			showMessage('user not found', "warning");
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
