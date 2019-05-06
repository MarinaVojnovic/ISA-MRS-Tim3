var urlRoot1 = "http://localhost:8080/createAirline";
var urlRoot2 = "http://localhost:8080/createHotel";
var urlRoot3 = "http://localhost:8080/createRentacar";
var urlRoot4 = "http://localhost:8080/auth/registerAirlineAdmin";
var urlRoot5 = "http://localhost:8080/auth/registerHotelAdmin";
var urlRoot6 = "http://localhost:8080/auth/registerRentacarAdmin";
var urlRoot7 = "http://localhost:8080/auth/registerSystemAdmin";
var urlRoot8 = "http://localhost:8080/getAllAirlines";
var urlRoot9 = "http://localhost:8080/getAllHotels";
var urlRoot10 = "http://localhost:8080/getAllRentacars";

var TOKEN_KEY = 'jwtToken';

getAirlineWithoutAdmin();
getHotelWithoutAdmin();
getRentacarWithoutAdmin();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function getAirlineWithoutAdmin() {
	$('#airlineAdminAirlineIdRegister').find('option').remove()
	$.ajax({
		type : 'GET',
		url : urlRoot8,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('profile not found');
			} else {
				$.each(data, function(i, item) {
					$('#airlineAdminAirlineIdRegister').append($('<option>', {
						value : item.id,
						text : item.id + " " + item.name
					}));
				});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}

	})
}

function getHotelWithoutAdmin() {
	$('#hotelAdminHotelIdRegister').find('option').remove()
	$.ajax({
		type : 'GET',
		url : urlRoot9,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('profile not found');
			} else {
				$.each(data, function(i, item) {
					$('#hotelAdminHotelIdRegister').append($('<option>', {
						value : item.id,
						text : item.id + " " + item.name
					}));
				});
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}

	})
}

function getRentacarWithoutAdmin() {
	$('#rentacarAdminRentacarIdRegister').find('option').remove()
	$.ajax({
		type : 'GET',
		url : urlRoot10,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('profile not found');
			} else {
				$.each(data, function(i, item) {
					$('#rentacarAdminRentacarIdRegister').append(
							$('<option>', {
								value : item.id,
								text : item.id + " " + item.name
							}));
				});
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
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createAirlineToJSON(
											airlineNameRegister,
											airlineAddressRegister,
											airlinePromotionalDescription),
									success : function(data) {
										getAirlineWithoutAdmin();
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
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createHotelToJSON(hotelNameRegister,
											hotelAddressRegister,
											hotelPromotionalDescription),
									success : function(data) {
										getHotelWithoutAdmin();
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
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createRentacarToJSON(
											rentacarNameRegister,
											rentacarAddressRegister,
											rentacarPromotionalDescription),
									success : function(data) {
										getRentacarWithoutAdmin();
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
					var k = document
							.getElementById("airlineAdminAirlineIdRegister");
					if (k.selectedIndex != -1) {
						var adminId = k.options[k.selectedIndex].value;
					} else {
						var adminId = "";
					}
					if (adminId == "") {
						alert("You must pick some airline object. Maybe there is not even"
								+ " one for you, be sure to first create one airline.")
					} else if (username == "" || password1 == ""
							|| password2 == "" || firstName == ""
							|| lastName == "" || email == ""
							|| phoneNumber == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot4,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createAirlineAdminToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber, adminId),
									success : function(data) {
										if (data.message != undefined) {
											alert(data.message);
										} else {
											if (data) {
												getAirlineWithoutAdmin();
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

$(document)
		.on(
				"submit",
				"#form5",
				function(e) {
					e.preventDefault();
					var username = document
							.getElementById("hotelAdminUsernameRegister").value;
					var password1 = document
							.getElementById("hotelAdminPassword1Register").value;
					var password2 = document
							.getElementById("hotelAdminPassword2Register").value;
					var firstName = document
							.getElementById("hotelAdminFirstNameRegister").value;
					var lastName = document
							.getElementById("hotelAdminLastNameRegister").value;
					var email = document
							.getElementById("hotelAdminEmailRegister").value;
					var phoneNumber = document
							.getElementById("hotelAdminPhoneNumberRegister").value;
					var k = document
							.getElementById("hotelAdminHotelIdRegister");
					if (k.selectedIndex != -1) {
						var adminId = k.options[k.selectedIndex].value;
					} else {
						var adminId = "";
					}
					if (adminId == "") {
						alert("You must pick some hotel object. Maybe there is not even"
								+ " one for you, be sure to first create one hotel.")
					} else if (username == "" || password1 == ""
							|| password2 == "" || firstName == ""
							|| lastName == "" || email == ""
							|| phoneNumber == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot5,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createHotelAdminToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber, adminId),
									success : function(data) {
										if (data.message != undefined) {
											alert(data.message);
										} else {
											if (data) {
												getHotelWithoutAdmin();
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

$(document)
		.on(
				"submit",
				"#form6",
				function(e) {
					e.preventDefault();
					var username = document
							.getElementById("rentacarAdminUsernameRegister").value;
					var password1 = document
							.getElementById("rentacarAdminPassword1Register").value;
					var password2 = document
							.getElementById("rentacarAdminPassword2Register").value;
					var firstName = document
							.getElementById("rentacarAdminFirstNameRegister").value;
					var lastName = document
							.getElementById("rentacarAdminLastNameRegister").value;
					var email = document
							.getElementById("rentacarAdminEmailRegister").value;
					var phoneNumber = document
							.getElementById("rentacarAdminPhoneNumberRegister").value;
					var k = document
							.getElementById("rentacarAdminRentacarIdRegister");
					if (k.selectedIndex != -1) {
						var adminId = k.options[k.selectedIndex].value;
					} else {
						var adminId = "";
					}
					if (adminId == "") {
						alert("You must pick some rentacar object. Maybe there is not even"
								+ " one for you, be sure to first create one rentacar.")
					} else if (username == "" || password1 == ""
							|| password2 == "" || firstName == ""
							|| lastName == "" || email == ""
							|| phoneNumber == "") {
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot6,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createRentacarAdminToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber, adminId),
									success : function(data) {
										if (data.message != undefined) {
											alert(data.message);
										} else {
											if (data) {
												getRentacarWithoutAdmin();
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

$(document)
		.on(
				"submit",
				"#form7",
				function(e) {
					e.preventDefault();
					var username = document
							.getElementById("systemAdminUsernameRegister").value;
					var password1 = document
							.getElementById("systemAdminPassword1Register").value;
					var password2 = document
							.getElementById("systemAdminPassword2Register").value;
					var firstName = document
							.getElementById("systemAdminFirstNameRegister").value;
					var lastName = document
							.getElementById("systemAdminLastNameRegister").value;
					var email = document
							.getElementById("systemAdminEmailRegister").value;
					var phoneNumber = document
							.getElementById("systemAdminPhoneNumberRegister").value;
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
									url : urlRoot7,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : createSystemAdminToJSON(username,
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
		email, phoneNumber, adminId) {
	return JSON.stringify({
		"username" : username,
		"password" : password1,
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : email,
		"phoneNumber" : phoneNumber,
		"adminId" : adminId,
	})
}

function createHotelAdminToJSON(username, password1, firstName, lastName,
		email, phoneNumber, adminId) {
	return JSON.stringify({
		"username" : username,
		"password" : password1,
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : email,
		"phoneNumber" : phoneNumber,
		"adminId" : adminId,
	})
}

function createRentacarAdminToJSON(username, password1, firstName, lastName,
		email, phoneNumber, adminId) {
	return JSON.stringify({
		"username" : username,
		"password" : password1,
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : email,
		"phoneNumber" : phoneNumber,
		"adminId" : adminId,
	})
}

function createSystemAdminToJSON(username, password1, firstName, lastName,
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