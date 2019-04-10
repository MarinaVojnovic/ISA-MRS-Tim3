var urlRegisterUser = "http://localhost:8080/registerUser";
var urlRootSendMail="http://localhost:8080/sendEmail";

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
    var passwordTwo = $("#passwordTwo").val();
    var username=$("#username").val();
    var city=$("#city").val();
    var phone=$("#phone").val();
    
    console.log('phone: '+phone);
    
    
    if (name=="" || surname=="" || email=="" || password=="" || username=="" || city=="" || phone==""){
    	alert('None of the fields is allowed to be empty');
    }
    
    if (password!=passwordTwo){
    	alert('Passwords must match!');
    }
    else{
    	 $("#register").prop("disabled", true);

    	    $.ajax({
    	        type: "POST",
    	        contentType: "application/json",
    	        url: urlRegisterUser,
    	        data: userToJson(name,surname,email,username, password, city, phone),
    	        dataType: 'json',
    	        success: function (data) {
    	        	if (data==null){
    	        		 $("#register").prop("disabled", false);
    	        		alert('Username already exists!');
    	        		
    	        	}else{
    	        		alert('You have successfully registered. Please go to your email and verify registration in order to be able to use funcionalites of system.');
        	        	console.log('user successfully registered');
        	        	sendEMail(email);
    	        	}
    	        
    	        	
    	        },
    	        error: function (e) {
    	        	 
    	             console.log("ERROR : ");
    	            

    	        }
    	    });
    	    
    	   
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
function userToJson(name, surname, email, username, password, city, phone) {
	return JSON.stringify({
		"name" : name,
		"surname" : surname,
		"email" : email,
		"username": username,
		"password":password,
		"city": city,
		"phone": phone,
		"firstTime": true
	})
}

