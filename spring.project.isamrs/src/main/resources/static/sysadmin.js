var urlRoot1 = "http://localhost:8080/createAirline";
var urlRoot2 = "http://localhost:8080/createHotel";
var urlRoot3 = "http://localhost:8080/createRentacar";
var urlRoot4 = "http://localhost:8080/auth/registerAirlineAdmin";
var urlRoot5 = "http://localhost:8080/auth/registerHotelAdmin";
var urlRoot6 = "http://localhost:8080/auth/registerRentacarAdmin";
var urlRoot7 = "http://localhost:8080/auth/registerSystemAdmin";

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document)
		.on(
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var airlineNameRegister = document
							.getElementById("airlineNameRegister").value;
					var airlineAddressRegister = document
							.getElementById("airlineAddressRegister").value;
					var airlinePromotionalDescription = document
							.getElementById("airlinePromotionalDescription").value;
					if (airlineNameRegister == ""
							|| airlineAddressRegister == ""
							|| airlinePromotionalDescription == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot1,
									contentType : 'application/json',
									dataType : "json",
									data : createAirlineToJSON(
											airlineNameRegister,
											airlineAddressRegister,
											airlinePromotionalDescription),
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
				"#form2",
				function(e) {
					e.preventDefault();
					var hotelNameRegister = document
							.getElementById("hotelNameRegister").value;
					var hotelAddressRegister = document
							.getElementById("hotelAddressRegister").value;
					var hotelPromotionalDescription = document
							.getElementById("hotelPromotionalDescription").value;
					if (hotelNameRegister == "" || hotelAddressRegister == ""
							|| hotelPromotionalDescription == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot2,
									contentType : 'application/json',
									dataType : "json",
									data : createHotelToJSON(hotelNameRegister,
											hotelAddressRegister,
											hotelPromotionalDescription),
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
				"#form3",
				function(e) {
					e.preventDefault();
					var rentacarNameRegister = document
							.getElementById("rentacarNameRegister").value;
					var rentacarAddressRegister = document
							.getElementById("rentacarAddressRegister").value;
					var rentacarPromotionalDescription = document
							.getElementById("rentacarPromotionalDescription").value;
					if (rentacarNameRegister == ""
							|| rentacarAddressRegister == ""
							|| rentacarPromotionalDescription == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot3,
									contentType : 'application/json',
									dataType : "json",
									data : createRentacarToJSON(
											rentacarNameRegister,
											rentacarAddressRegister,
											rentacarPromotionalDescription),
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
				"#form4",
				function(e) {
					e.preventDefault();
					var username = document
							.getElementById("airlineAdminUsernameRegister").value;
					var password1 = document
							.getElementById("airlineAdminPassword1Register").value;
					var password2 = document
							.getElementById("airlineAdminPassword2Register").value;
					var firstName = document
							.getElementById("airlineAdminFirstNameRegister").value;
					var lastName = document
							.getElementById("airlineAdminLastNameRegister").value;
					var email = document
							.getElementById("airlineAdminEmailRegister").value;
					var phoneNumber = document
							.getElementById("airlineAdminPhoneNumberRegister").value;
					if (username == "" || password1 == "" || password2 == ""
							|| firstName == "" || lastName == "" || email == ""
							|| phoneNumber == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot4,
									contentType : 'application/json',
									dataType : "json",
									data : createAirlineAdminToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber),
									success : function(data) {
										if (data.message != undefined) {
											alert(data.message);
										} else {
											if (data) {
												alert("Successful registration, congratulations!");
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
				});

function createAirlineToJSON(airlineNameRegister, airlineAddressRegister,
		airlinePromotionalDescription) {
	return JSON.stringify({
		"airlineNameRegister" : airlineNameRegister,
		"airlineAddressRegister" : airlineAddressRegister,
		"airlinePromotionalDescription" : airlinePromotionalDescription,
	})
}

function createHotelToJSON(hotelNameRegister, hotelAddressRegister,
		hotelPromotionalDescription) {
	return JSON.stringify({
		"hotelNameRegister" : hotelNameRegister,
		"hotelAddressRegister" : hotelAddressRegister,
		"hotelPromotionalDescription" : hotelPromotionalDescription,
	})
}

function createRentacarToJSON(rentacarNameRegister, rentacarAddressRegister,
		rentacarPromotionalDescription) {
	return JSON.stringify({
		"rentacarNameRegister" : rentacarNameRegister,
		"rentacarAddressRegister" : rentacarAddressRegister,
		"rentacarPromotionalDescription" : rentacarPromotionalDescription,
	})
}

function createAirlineAdminToJSON(username, password1, firstName, lastName,
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