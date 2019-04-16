var urlRoot1 = "http://localhost:8080/createFlight";
var urlRoot2 = "http://localhost:8080/findAirline";
var urlRoot3 = "http://localhost:8080/saveChangesAirline";
var urlRoot4 = "http://localhost:8080/api/editUser";
var urlRoot5 = "http://localhost:8080/api/getLogged";

var TOKEN_KEY = 'jwtToken';
getLogged();


findAirline();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

function findAirline() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot2,
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("airlineIdEdit").value = data.id;
						document.getElementById("airlineNameEdit").value = data.name;
						document.getElementById("airlineAddressEdit").value = data.address;
						document
								.getElementById("airlinePromotionalDescriptionEdit").value = data.promotionalDescription;
						document.getElementById("airlineDestinationsEdit").value = data.destinations;
						document.getElementById("airlineFlightsEdit").value = data.flights;
						document
								.getElementById("airlineQuickBookingTicketsEdit").value = data.quickBookingTickets;
						document.getElementById("airlineAirplanesEdit").value = data.airplanes;
						document.getElementById("airlineCustomerServicesEdit").value = data.airlineCustomerServices;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}

			})
};

$(document)
.on(
		"submit",
		"#form2",
		function(e) {
			e.preventDefault();
			var flightNumberRegister = document
					.getElementById("flightNumberRegister").value;
			var startDestinationRegister = document
					.getElementById("startDestinationRegister").value;
			var finalDestinationRegister = document
					.getElementById("finalDestinationRegister").value;
			var costOfFlight = document.getElementById("costOfFlight").value;
			var dateOfFlight = document.getElementById("dateOfFlight").value;
			var dateOfArrival=document.getElementById("dateOfArrival").value;
			var length=document.getElementById("lengthOfFlight").value;
			var numOfSeats=document.getElementById("numberOfSeats").value;
			if (flightNumberRegister == ""
					|| startDestinationRegister == ""
					|| finalDestinationRegister == ""
					|| costOfFlight == "" || dateOfFlight == "",dateOfArrival==""
						|| length=="" || numOfSeats=="" || isNaN(length) || isNaN(numOfSeats)) {
				alert('At least one field is blank, please fill it up with proper information!');
			} else {
				$
						.ajax({
							type : 'POST',
							url : urlRoot1,
							contentType : 'application/json',
							dataType : "json",
							data : createFlightToJSON(
									flightNumberRegister,
									startDestinationRegister,
									finalDestinationRegister,
									costOfFlight, dateOfFlight,dateOfArrival,length,numOfSeats),
							success : function(data) {
								alert("Successful registration, congratulations!");
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);

							}
						})
			}
		});

$(document)
		.on(
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var id = document.getElementById("airlineIdEdit").value;
					var name = document.getElementById("airlineNameEdit").value;
					var address = document.getElementById("airlineAddressEdit").value;

					var promotionalDescription = document
							.getElementById("airlinePromotionalDescriptionEdit").value;
					var destinations = document
							.getElementById("airlineDestinationsEdit").value;
					var flights = document.getElementById("airlineFlightsEdit").value;

					var quickBookingTickets = document
							.getElementById("airlineQuickBookingTicketsEdit").value;
					var airplanes = document
							.getElementById("airlineAirplanesEdit").value;
					var airlineCustomerServices = document
							.getElementById("airlineCustomerServicesEdit").value;

					if (name == "" || address == ""
							|| promotionalDescription == "") {
						alert('None of the fields is allowed to be empty!');
					} else {

						$.ajax({
							type : 'PUT',
							url : urlRoot3,
							data : airlineToJson(id, name, address,
									promotionalDescription, destinations,
									flights, quickBookingTickets, airplanes,
									airlineCustomerServices),
							dataType : "json",
							contentType : 'application/json',
							success : function(data) {
								alert("Successful editing, congratulations!");

							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);

							}

						})
					}

				});

function getLogged() {
	var token = getJwtToken(TOKEN_KEY);
	if (token) {
		$
				.ajax({
					type : 'GET',
					url : urlRoot5,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data == null) {
							alert('Error while finding loged one!');
						} else {
							document.getElementById("airlineAdminUsernameEdit").value = data.username;
							document.getElementById("airlineAdminFirstNameEdit").value = data.firstName;
							document.getElementById("airlineAdminLastNameEdit").value = data.lastName;
							document.getElementById("airlineAdminEmailEdit").value = data.email;
							document
									.getElementById("airlineAdminPhoneNumberEdit").value = data.phoneNumber;
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.status);
						alert(textStatus);
						alert(errorThrown);
					}

				})
	}
}

$(document)
		.on(
				"submit",
				"#form4",
				function(e) {
					e.preventDefault();
					var username = document
							.getElementById("airlineAdminUsernameEdit").value;
					var password1 = document
							.getElementById("airlineAdminPassword1Edit").value;
					var password2 = document
							.getElementById("airlineAdminPassword2Edit").value;
					var firstName = document
							.getElementById("airlineAdminFirstNameEdit").value;
					var lastName = document
							.getElementById("airlineAdminLastNameEdit").value;
					var email = document
							.getElementById("airlineAdminEmailEdit").value;
					var phoneNumber = document
							.getElementById("airlineAdminPhoneNumberEdit").value;
					if (username == "" || password1 == "" || password2 == ""
							|| firstName == "" || lastName == "" || email == ""
							|| phoneNumber == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
					} else {
						$
								.ajax({
									type : 'PUT',
									url : urlRoot4,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : airlineAdminEditToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber),
									success : function(data) {
										if (data) {
											alert("Successful editing, congratulations!");
										} else {
											alert("Error while editing!");
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
				});

function createFlightToJSON(flightNumberRegister, startDestinationRegister,
		finalDestinationRegister, costOfFlight, dateOfFlight,dateOfArrival,length,numOfSeats) {
	return JSON.stringify({
		"flightNumberRegister" : flightNumberRegister,
		"startDestinationRegister" : startDestinationRegister,
		"finalDestinationRegister" : finalDestinationRegister,
		"costOfFlight" : costOfFlight,
		"dateOfFlight" : dateOfFlight,
		"dateOfArrival":dateOfArrival,
		"length":length,
		"numOfSeats":numOfSeats,
	})
}


function airlineToJson(id, name, address, promotionalDescription, destinations,
		flights, quickBookingTickets, airplanes, airlineCustomerServices) {
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : address,
		"promotionalDescription" : promotionalDescription,
		"destinations" : destinations,
		"flights" : flights,
		"quickBookingTicket" : quickBookingTickets,
		"airplanes" : airplanes,
		"airlineCustomerServices" : airlineCustomerServices,

	})
}


function airlineAdminEditToJSON(username, password1, firstName, lastName,
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