var urlRoot1 = "http://localhost:8080/findRentacar";
var urlRoot2 = "http://localhost:8080/saveChangesRentACar";
var urlRoot3 = "http://localhost:8080/createCar";
var urlRoot4 = "http://localhost:8080/getCars";
var urlRoot5 = "http://localhost:8080/deleteCar";
var urlRoot6 = "http://localhost:8080/findCar";
var urlRoot7 = "http://localhost:8080/saveEditedCar";
var	urlGetFirstTime = "http://localhost:8080/api/isFirstTime"
var urlChangePassword="http://localhost:8080/api/changePasswordFirstTime";

var TOKEN_KEY = 'jwtToken';

findRentacar();
window.onload = function(e){ 
	console.log('window loades');
	
	var r;
	$
	.ajax({
		type : 'GET',
		url : urlGetFirstTime,
	    headers: createAuthorizationTokenHeader(TOKEN_KEY),
	    dataType: "json",
		success : function(data) {
			if (data){
				console.log('You have to change your password!');
				r= 1;
				console.log('r posle you have to change your password '+r)
				$('.tab').hide();
				openCity(e, 'passwordValidation');
				
			}else{
				console.log('You do not have to change your password!');
				r=0;
				
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	/*console.log('r posle ajax '+r);
	
	if (r==1){
		console.log('uslo u r=1');
		$('.tab').hide();
		openCity(e, 'passwordValidation');
	}else {
		
	}*/
 }

function passwordValidation(){
	console.log('password validation called');
	var password1 = document.getElementById("newPasswordOne").value;
	var password2 = document.getElementById("newPasswordTwo").value;
	
	if (password1=="" || password2==""){
		alert('You have to fill both fields');
	}
	else if (password1!=password2){
		alert('Passwords must match!');
	}
	else{
		$
		.ajax({
			type : 'PUT',
			url : urlChangePassword,
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
			contentType : 'application/json',
			dataType : "json",
			data : passwordDTOJson(password1),
			success : function(data) {
				if (data) {
					alert("Successful changing password, congratulations!");
					$('.tab').show();
					$('#passwordValidation').hide();
				} else {
					alert("Error while changing password!");
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

/*function checkFirstTime(){
	console.log('uslo u check first time');
	var r;
	$
	.ajax({
		type : 'GET',
		url : urlGetFirstTime,
	    headers: createAuthorizationTokenHeader(TOKEN_KEY),
	    dataType: "json",
		success : function(data) {
			if (data){
				console.log('You have to change your password!');
				r= 1;
				console.log('r posle you have to change your password '+r)
				return r;
			}else{
				console.log('You do not have to change your password!');
				r=0;
				return r;
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	console.log('r posle ajax '+r);
	
}*/

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function saveEditedCar(){
	console.log('save edited car called');
	var id = document.getElementById("carIdEdit").value;
	var name = document.getElementById("carNameEdit").value;
	var price = document
			.getElementById("carPriceEdit").value;
	var year = document
			.getElementById("carYearEdit").value;
	
	console.log(name+' '+price+' '+year);
	
	if (name == "" || price == "" || year == "") {
		alert('None of the fields is allowed to be empty!');
	} else {

	$
			.ajax({
				type : 'PUT',
				url : urlRoot7,
				data : carToJson(id, name, price, year),
				dataType : "json",
				contentType : 'application/json',
				success : function(data) {
					alert("Car successfully edited, congratulations!");
					

				},
				error : function(XMLHttpRequest) {
					alert("Error while editing car! ");
				}

			})
			
	}


}

function editCar(carId){
	console.log('Edit cars called');


	urlRoot6 = urlRoot6+"/"+carId;
	$.ajax({
		type : 'GET',
		url : urlRoot6,
		dataType : "json",
		success : function(data) {
			if (data==null){
				console.log('Car not found');
				
			}
			else{
				
				document.getElementById("carIdEdit").value = data.id;
				document.getElementById("carNameEdit").value = data.name;
				document.getElementById("carPriceEdit").value = data.price;
				document.getElementById("carYearEdit").value = data.year;
			
				console.log('Car successfully edited.');
				
			}
			
			urlRoot6 = "http://localhost:8080/findCar";
			showCars("forEdit");
		},
		error: function(XMLHttpRequest){
			alert("Error while deleting flight");
		}
		
	})
	
	
}

function deleteCar(carId){
	console.log('Delete cars called');


	urlRoot5 = urlRoot5+"/"+carId;
	$.ajax({
		type : 'DELETE',
		url : urlRoot5,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			if (data==null){
				console.log('Car not found');
			}
			else{
				console.log('Car successfully deleted.');
			}
			
			urlRoot5 = "http://localhost:8080/deleteCar";
			showCars("forDelete");
			
		},
		error: function(XMLHttpRequest){
			alert("Error while deleting flight");
		}
		
	})
	
}

function findRentacar() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot1,
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("rentacarIdEdit").value = data.id;
						document.getElementById("rentacarNameEdit").value = data.name;
						document.getElementById("rentacarAddressEdit").value = data.address;
						document
								.getElementById("rentacarPromotionalDescriptionEdit").value = data.promotionalDescription;
						document.getElementById("rentacarCustomerServicesEdit").value = data.rentacarCustomerServices;
						document.getElementById("rentacarCarsEdit").value = data.cars;
						document.getElementById("rentacarBranchesEdit").value = data.branches;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})

}

$(document)
		.on(
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var name = document.getElementById("rentacarNameEdit").value;
					var address = document
							.getElementById("rentacarAddressEdit").value;
					var promotionalDescription = document
							.getElementById("rentacarPromotionalDescriptionEdit").value;

					var rentacarCustomerServices = document
							.getElementById("rentacarCustomerServicesEdit").value;
					var cars = document.getElementById("rentacarCarsEdit").value;
					var branches = document
							.getElementById("rentacarBranchesEdit").value;

					var id = document.getElementById("rentacarIdEdit").value;

					if (name == "" || address == ""
							|| promotionalDescription == "") {
						alert('None of the fields is allowed to be empty!');
					} else {

						$
								.ajax({
									type : 'PUT',
									url : urlRoot2,
									data : rentACarToJson(id, name, address,
											promotionalDescription,
											rentacarCustomerServices, cars,
											branches),
									dataType : "json",
									contentType : 'application/json',
									success : function(data) {
										alert("Successful editing, congratulations!");

									},
									error : function(XMLHttpRequest) {
										alert("Error while changing profile information ");
									}

								})
					}

				});

function showCars(type){
	console.log('show cars for deleting called');
	
	$
	.ajax({
		type : 'GET',
		url : urlRoot4,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			
			var response = data;
			$("#tableOfCarsForDelete").find("tr").remove();
			$("#tableOfCarsForEdit").find("tr").remove();
			var tabela;
			if (type=="forDelete"){
				tabela = document.getElementById("tableOfCarsForDelete");
			}else {
				tabela = document.getElementById("tableOfCarsForEdit");
			}
			
			console.log(tabela);
			
			for(var counter in response){
				console.log('counter: '+counter);
				var row = tabela.insertRow(counter);
				console.log(row);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell2.id="name";
				var cell3 = row.insertCell(2);
				cell3.id="price";
				var cell4 = row.insertCell(3);
				cell3.id="year";
				var cell5 = row.insertCell(4);
				
				
				cell1.innerHTML = response[counter].id;
				console.log(cell1.innerHTML);
				console.log('ID PRE PODESVANAJA '+cell1.id);
				cell2.innerHTML = response[counter].name;
				console.log(cell2.innerHTML);
				cell3.innerHTML = response[counter].price;
				cell4.innerHTML = response[counter].year;
				if (type=="forDelete"){
					cell5.innerHTML = '<button id=\"'+ response[counter].id+'\" class=\"deleteCarButton\" class="btn btn-primary">Delete</button>';
				}else if(type=="forEdit"){
					cell5.innerHTML = '<button id=\"'+ response[counter].id+'\" class=\"editCarButton\" class="btn btn-primary" >Edit</button>';
				}
				
			
			}
			var row = tabela.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			
			cell1.innerHTML = "Id";
			cell2.innerHTML = "Name";
			cell3.innerHTML = "Price";
			cell4.innerHTML = "Year";
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

$(document).on("click", "#removeCarsButton", function(e){
	
	showCars("forDelete");
})

$(document).on("click", "#editCarsButton", function(e){
	
	showCars("forEdit");
})


$(document).on("submit", "#form2", function(e) {
	e.preventDefault();

	var name = document.getElementById("addCarName").value;
	var price = document.getElementById("addCarPrice").value;
	var year = document.getElementById("addCarYear").value;
	if (name == "" || price == "" || year == "") {
		alert('None of the fields is allowed to be empty!');
	} else {

		$.ajax({
			type : 'POST',
			url : urlRoot3,
			contentType : 'application/json',
			dataType : "json",
			data : createCarDTOToJson(name, price, year),
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
			success : function(data) {
				alert("Successful adding car, congratulations!");
				document.getElementById("addCarName").value="";
				document.getElementById("addCarPrice").value="";
				document.getElementById("addCarYear").value="";
				openCity(e, 'addCar');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);

			}
		})
	}
	
	
	
	
	

})

function rentACarToJson(id, name, address, promotionalDescription,
		rentacarCustomerServices, cars, branches) {
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : address,
		"promotionalDescription" : promotionalDescription,
		"rentacarCustomerServices" : rentacarCustomerServices,
		"cars" : cars,
		"branches" : branches,

	})
}

function carToJson(id, name, price, year) {
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"price" : price,
		"year" : year
	})
}

function createCarDTOToJson(name, price, year) {
	return JSON.stringify({
		"name" : name,
		"price" : price,
		"year" : year

	})
}

$(document).on('click','.deleteCarButton',function(e){
	//e.preventDefault();
	var _this = $(this);

    console.log('car number'+ '   '+this.id);
    deleteCar(this.id);
    
});

$(document).on('click','.editCarButton',function(e){
	//e.preventDefault();
	var _this = $(this);

    console.log('car number'+ '   '+this.id);
    openCity(e, 'editCarFormular');
    editCar(this.id);
   
});

$(document).on('submit','#editCarForm',function(e){
	e.preventDefault();
	
    saveEditedCar();
    openCity(e, 'editCar');
    showCars('forEdit');
   
});

$(document).on('submit','#passwordValidationForm',function(e){
	e.preventDefault();
	
    passwordValidation();
   
});



function passwordDTOJson(password1) {
	return JSON.stringify({
		"password" : password1
		

	})
}

