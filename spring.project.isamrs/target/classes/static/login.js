var urlLoginUser = "http://localhost:8080/loginUser"
$(document).ready(function () {




    $("#loginForm").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        login();

    });

});
//var emailSad;
function login() {
	console.log('login called');
    
    var username = $("#loginusername").val();
    var password = $("#loginpassword").val();
    
    console.log(username+'   '+password);

   
    
    

    $.ajax({
    	
        dataType: 'json',
        url: urlLoginUser,
        type: 'POST',
        contentType: 'application/json',
        data: userDtoToJson(username, password),
        success: function (data) {
        		alert('user successfully found');
        		console.log("SUCCESS : ", data.email);
                $("#login").prop("disabled", false);
                window.location="http://localhost:8080/registeredUser.html";
              
        	},
        error: function (e) {
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
