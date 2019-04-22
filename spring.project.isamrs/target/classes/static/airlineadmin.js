var urlRoot1 = "http://localhost:8080/createFlight";
var urlRoot2 = "http://localhost:8080/findAirline";
var urlRoot3 = "http://localhost:8080/saveChangesAirline";
var urlRoot4 = "http://localhost:8080/api/editUser";
var urlRoot5 = "http://localhost:8080/api/getLogged";
var urlRoot6 = "http://localhost:8080/getAllAirlinesExcept";
var urlRoot7 = "http://localhost:8080/addDestination";
var urlRoot8 = "http://localhost:8080/getAirlineWorkingDestinations";

var TOKEN_KEY = 'jwtToken';
getLogged();
getAllAirlines();

findAirline();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function findAirline() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot2,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("airlineNameEdit").value = data.name;
						document.getElementById("airlineAddressEdit").value = data.address;
						document
								.getElementById("airlinePromotionalDescriptionEdit").value = data.promotionalDescription;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}

			})
};

function addFlight() {
	var flightNumberRegister = document.getElementById("flightNumberRegister").value;
	var startDestinationRegister = $('option:selected',
			"#startDestinationRegister").attr('name');
	var finalDestinationRegister = $('option:selected',
			"#finalDestinationRegister").attr('name');
	var costOfFlight = document.getElementById("costOfFlight").value;
	var dateOfFlight = document.getElementById("dateOfFlight").value;
	var dateOfArrival = document.getElementById("dateOfArrival").value;
	var length = document.getElementById("lengthOfFlight").value;
	var numOfSeats = document.getElementById("numberOfSeats").value;
	var numOfStops = document.getElementById("numberOfFlightStops").value;
	var stops = "";
	var i = 0
	if (flightNumberRegister == "" || costOfFlight == "" || dateOfFlight == ""
			|| dateOfArrival == "" || length == "" || numOfSeats == ""
			|| numOfStops == "") {
		alert("You must fill in all of the fields");
		i = 1;
	}
	if (isNaN(costOfFlight) || costOfFlight < 0 || isNaN(length) || length < 0
			|| isNaN(numOfSeats) || numOfSeats < 0 || isNaN(numOfStops)
			|| numOfStops < 0) {
		alert("You must insert positive number for cost of flight,length,number of seats and stops!");
		i = 1;
	}
	if (startDestinationRegister == finalDestinationRegister) {
		alert("Start and final destination of flight cannot be same!");
		i = 1;
	}
	if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
		alert("Date of flight cannot be after date of arrival");
		i = 1;
	}
	if (new Date() > new Date(dateOfFlight)
			|| new Date() > new Date(dateOfArrival)) {
		alert("You cannot put dates in the past");
		i = 1;
	}
	if (new Date() > new Date(dateOfFlight)
			&& new Date() > new Date(dateOfArrival)) {
		alert("You cannot put dates in the past");
		i = 1;
	}
	if (i == 0) {
		console.log(dateOfFlight);
		if (numOfStops > 0) {
			for (var i = 0; i < numOfStops; i++) {
				var s = document.getElementById("flightStop " + i + "").value;
				console.log(s);
				stops += s + " "
			}
			stops.substring(0, stops.length - 1);
		}
		console.log(stops);
		$.ajax({
			type : 'POST',
			url : urlRoot1,
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
			contentType : "application/json",
			dataType : "json",
			data : createFlightToJSON(flightNumberRegister,
					startDestinationRegister, finalDestinationRegister,
					costOfFlight, dateOfFlight, dateOfArrival, length,
					numOfSeats, numOfStops, stops),
			success : function(data) {
				alert("Successful added flight, congratulations!");
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
				"#form2",
				function(e) {
					e.preventDefault();
					$("#divStops").empty();
					var flightNumberRegister = document
							.getElementById("flightNumberRegister").value;
					var startDestination = $('option:selected',
							"#startDestinationRegister").attr('name');
					var finalDestination = $('option:selected',
							"#finalDestinationRegister").attr('name');
					var costOfFlight = document.getElementById("costOfFlight").value;
					var dateOfFlight = document.getElementById("dateOfFlight").value;
					var dateOfArrival = document
							.getElementById("dateOfArrival").value;
					var length = document.getElementById("lengthOfFlight").value;
					var numOfSeats = document.getElementById("numberOfSeats").value;
					var numOfStops = document
							.getElementById("numberOfFlightStops").value;
					var i = 0;
					if (flightNumberRegister == "" || costOfFlight == ""
							|| dateOfFlight == "" || dateOfArrival == ""
							|| length == "" || numOfSeats == ""
							|| numOfStops == "") {
						alert("You must fill in all of the fields");
						i = 1;
					}
					if (isNaN(costOfFlight) || costOfFlight < 0
							|| isNaN(length) || length < 0 || isNaN(numOfSeats)
							|| numOfSeats < 0 || isNaN(numOfStops)
							|| numOfStops < 0) {
						alert("You must insert positive number for cost of flight,length,number of seats and stops!");
						i = 1;
					}
					if (startDestination == finalDestination) {
						alert("Start and final destination of flight cannot be same!");
						i = 1;
					}
					if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
						alert("Date of flight cannot be after date of arrival");
						i = 1;
					}
					if (new Date() > new Date(dateOfFlight)
							|| new Date() > new Date(dateOfArrival)) {
						alert("You cannot put dates in the past");
						i = 1;
					}
					if (new Date() > new Date(dateOfFlight)
							&& new Date() > new Date(dateOfArrival)) {
						alert("You cannot put dates in the past");
						i = 1;
					}
					if (i == 0 && numOfStops > 0) {
						$
								.ajax({
									type : 'GET',
									url : urlRoot8,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									dataType : "json",
									success : function(data) {
										var list = data == null ? []
												: (data instanceof Array ? data
														: [ data ]);
										if (data == null) {

										} else {
											var list = data == null ? []
													: (data instanceof Array ? data
															: [ data ]);
											if (list.length > 0) {
												for (var i = 0; i < numOfStops; i++) {
													$("#divStops")
															.append(
																	'<label class="control-label col-sm-2" for="stop">Flight stop number '
																			+ (i + 1)
																			+ ': </label><div class="col-sm-10">')
													var select = $('<select class="form-control" id="flightStop '
															+ i
															+ '" name="flightStop '
															+ i + '"></select>')
													$
															.each(
																	list,
																	function(
																			index,
																			destination) {
																		var option = $('<option value="'
																				+ destination.id
																				+ '">'
																				+ destination.worksWith.name
																				+ '</option>');
																		select
																				.append(option);
																	})
													$('#divStops').append(
															select);
													$('#divStops').append(
															'</div><br>');
												}
												$('#divStops')
														.append(
																'<div class="col-sm-offset-2 col-sm-10"><button type="submit" class="btn btn-default" id="buttonAddFlight">Add flight</button></div>')

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
					} else if (numOfStops == 0) {
						addFlight();
					} else {

					}

				})

$(document).on('click', '#buttonAddFlight', function(e) {
	e.preventDefault();
	addFlight();
})

$(document)
		.on(
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var name = document.getElementById("airlineNameEdit").value;
					var address = document.getElementById("airlineAddressEdit").value;

					var promotionalDescription = document
							.getElementById("airlinePromotionalDescriptionEdit").value;

					if (name == "" || address == ""
							|| promotionalDescription == "") {
						alert('None of the fields is allowed to be empty!');
					} else {

						$
								.ajax({
									type : 'PUT',
									url : urlRoot3,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									data : airlineToJson(name, address,
											promotionalDescription),
									dataType : "json",
									contentType : 'application/json',
									success : function(data) {
										alert("Successful editing, congratulations!");

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
							document
									.getElementById("airlineAdminFirstNameEdit").value = data.firstName;
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
											setJwtToken(TOKEN_KEY,
													data.accessToken);
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

function getAllAirlines() {
	$.ajax({
		type : 'GET',
		url : urlRoot6,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			var list = data == null ? [] : (data instanceof Array ? data
					: [ data ]);
			if (list.length > 0) {
				var i = 0
				$.each(list, function(index, destination) {
					var option = $('<option name="' + destination.id
							+ '" value="' + destination.id + '">'
							+ destination.name + '</option>')
					$("#chooseDestination").append(option);
				})
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})

}

$(document).on(
		'click',
		'#addFlightButton',
		function(e) {
			e.preventDefault();
			$.ajax({
				type : 'GET',
				url : urlRoot8,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (data == null) {

					} else {
						var list = data == null ? []
								: (data instanceof Array ? data : [ data ]);
						if (list.length > 0) {
							var i = 0
							$.each(list, function(index, destination) {
								var option = $('<option name="'
										+ destination.worksWith.id + '">'
										+ destination.worksWith.name
										+ '</option>')
								$("#startDestinationRegister").append(option);
							})
							$.each(list, function(index, destination) {
								var option = $('<option name="'
										+ destination.worksWith.id + '">'
										+ destination.worksWith.name
										+ '</option>')
								$("#finalDestinationRegister").append(option);
							})

						}

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}
			})
		})

$(document).on(
		'submit',
		'#form5',
		function(e) {
			e.preventDefault();
			var destination = $('option:selected', "#chooseDestination").attr(
					'name');
			if (destination == undefined) {
				alert("There is no destination to add!");
			} else {
				$.ajax({
					type : 'POST',
					url : urlRoot7 + "/" + destination,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					contentType : 'application/json',
					success : function(data) {
						alert("Successfully added destination.");
						$(
								"#chooseDestination option[value='"
										+ destination + "']").remove();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.status);
						alert(textStatus);
						alert(errorThrown);

					}
				})
			}
		})

function createFlightToJSON(flightNumberRegister, startDestinationRegister,
		finalDestinationRegister, costOfFlight, dateOfFlight, dateOfArrival,
		length, numOfSeats, numOfStops, stops) {
	return JSON.stringify({
		"flightNumberRegister" : flightNumberRegister,
		"startDestinationRegister" : startDestinationRegister,
		"finalDestinationRegister" : finalDestinationRegister,
		"costOfFlight" : costOfFlight,
		"dateOfFlight" : dateOfFlight,
		"dateOfArrival" : dateOfArrival,
		"length" : length,
		"numOfSeats" : numOfSeats,
		"numOfStops" : numOfStops,
		"stops" : stops,
	})
}

function airlineToJson(name, address, promotionalDescription) {
	return JSON.stringify({
		"airlineNameRegister" : name,
		"airlineAddressRegister" : address,
		"airlinePromotionalDescription" : promotionalDescription,

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