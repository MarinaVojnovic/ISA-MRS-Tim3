var urlRegisterUser = "http://localhost:8080/registerUser";
var urlRootSendMail="http://localhost:8080/sendEmail";

var urlUserRegistration = "http://localhost:8080/auth/registerUser"; 

$(document).ready(function () {

    $("#registerForm").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_submit();

    });

});

function fire_submit() {

    console.log('fire submit called');
 
    
    var username=$("#username").val();
    var password = $("#passwordOne").val();
    var passwordTwo = $("#passwordTwo").val();
    var name = $("#name").val();
    var surname = $("#surname").val();
    var email = $("#emailField").val();
    var phone=$("#phone").val();
    
    
    console.log('phone: '+phone);
    
    
    if (name=="" || surname=="" || email=="" || password=="" || username=="" || phone==""){
    	alert('None of the fields is allowed to be empty');
    }
    
    if (password!=passwordTwo){
    	alert('Passwords must match!');
    }
    else{
    	 $("#register").prop("disabled", true);

    	    $.ajax({
    	        type: "POST",
    	        url: urlUserRegistration,
    	        contentType: "application/json",
    	        data: createUserToJSON(username, password, name, surname, email, phone),
    	        dataType: 'json',
    	        success: function (data) {
    	        	if (data.message != undefined) {
						alert(data.message);
					} else {
						if (data) {
							alert("Successful registration, congratulations! Please go to email to verify your registration!");
							sendEMail(email);
						} else {
							alert("Error while registrating!");
						}
					}	
    	        	
    	        	
    	        },
    	        error : function(jqXHR, textStatus,
						errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}
    	    })
    	    
    	   
    }

   
}

function sendEMail(email){
	console.log(email);
	
	$.ajax({
		type : 'POST',
		url : urlRootSendMail,
		contentType : 'application/json',
		
		data : mailToJson(email),
		success : function(data) {
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})
	
}

function mailToJson(emailAddress){
	return JSON.stringify({
		"emailAddress": emailAddress,
		"subject": "Register verification",
		"body": "http://localhost:8080/login.html"
	})
}

function createUserToJSON(username, password1, firstName, lastName,
		email, phoneNumber) {
	return JSON.stringify({
		"username" : username,
		"password" : password1,
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : email,
		"phoneNumber" : phoneNumber,
	})
}
