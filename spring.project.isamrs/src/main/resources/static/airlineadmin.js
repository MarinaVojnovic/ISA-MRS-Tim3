var urlRoot1 = "http://localhost:8080/createFlight";
var urlRoot2 = "http://localhost:8080/findAirline";
var urlRoot3 = "http://localhost:8080/saveChangesAirline";
var urlRoot4 = "http://localhost:8080/api/editUser";
var urlRoot5 = "http://localhost:8080/api/getLogged";
var urlRoot6 = "http://localhost:8080/getAllAirlinesExcept";
var urlRoot7 = "http://localhost:8080/addDestination";
var urlRoot8 = "http://localhost:8080/getAirlineWorkingDestinations";
var urlRoot9 = "http://localhost:8080/getAllFlightsAirline";
var urlGetFirstTime = "http://localhost:8080/api/isFirstTime";
var urlRoot14 = "http://localhost:8080/getFlight";
var urlRoot15 = "http://localhost:8080/getSeat";
var urlRoot16 = "http://localhost:8080/addQuickBooking";
var urlRoot17 = "http://localhost:8080/deleteSeats";
var urlChangePassword = "http://localhost:8080/api/changePasswordFirstTime";
var urlRoot18 = "http://localhost:8080/getFlights";
var urlRoot19 = "http://localhost:8080/reportFlightAttendance";
var urlRoot20 = "http://localhost:8080/findAirlineAmount";

var TOKEN_KEY = 'jwtToken';

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

$(document).on('click', '#editAirlineButton', function(e) {
	findAirline();
})

$(document).on('click', '#editProfileButton', function(e) {
	getLogged();
})

$(document).on('click', '#addDestinationButton', function(e) {
	getAllAirlines();
})

$(document).on('click', '#reportsButton', function(e) {
	fillDatas();
});

window.onload = function(e) {
	console.log('window loades');

	var r;
	$.ajax({
		type : 'GET',
		url : urlGetFirstTime,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				console.log('You have to change your password!');
				r = 1;
				console.log('r posle you have to change your password ' + r)
				$('.tab').hide();
				openCity(e, 'passwordValidation');

			} else {
				console.log('You do not have to change your password!');
				r = 0;

			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
}

function fillDatas() {
	$.ajax({
		type : 'GET',
		url : urlRoot2,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				$.ajax({
					type : 'GET',
					url : urlRoot18,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data) {
							fillTableFlights(data, "reportAirlineEachFlight");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");
					}

				})
				if (data.gradeNumber != 0) {
					$("#reportAirlineAverageGrade").html(
							(data.score / data.gradeNumber).toFixed(2));
				} else {
					$("#reportAirlineAverageGrade").html(
							(data.score).toFixed(2));
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
	$.ajax({
		type : 'GET',
		url : urlRoot19,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			// napravi chart myChart1
			createChart("myChart1", data.dailyLabels, data.dailyValues)
			createChart("myChart2", data.weeklyLabels, data.weeklyValues)
			createChart("myChart3", data.monthlyLabels, data.monthlyValues)
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
}

function fillTableFlights(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	var index = 0;
	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);
		var cell5 = row.insertCell(5);
		var cell6 = row.insertCell(6);
		var cell7 = row.insertCell(7);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].number;
		cell2.innerHTML = response[counter].startAirline.city
		cell3.innerHTML = response[counter].finalAirline.city;
		cell4.innerHTML = response[counter].dateOfStart;
		cell5.innerHTML = response[counter].dateOfEnd;
		cell6.innerHTML = response[counter].cost;
		if (response[counter].gradeNumber != 0) {
			cell7.innerHTML = (response[counter].score / response[counter].gradeNumber)
					.toFixed(2);
		} else {
			cell7.innerHTML = (response[counter].score).toFixed(2);
		}
	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);
	var cell6 = row.insertCell(6);
	var cell7 = row.insertCell(7);

	cell0.innerHTML = '<p style= "color:#002699;">#</p>';
	cell1.innerHTML = '<p style= "color:#002699;">Flight number</p>';
	cell2.innerHTML = '<p style= "color:#002699;">Start destination</p';
	cell3.innerHTML = '<p style= "color:#002699;">Final destination</p>';
	cell4.innerHTML = '<p style= "color:#002699;">Start date</p>';
	cell5.innerHTML = '<p style= "color:#002699;">End date</p>';
	cell6.innerHTML = '<p style= "color:#002699;">Cost</p>';
	cell7.innerHTML = '<p style= "color:#002699;">Score</p>';
}

function createChart(canvasId, labelNames, data) {
	var ctx = document.getElementById(canvasId).getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : labelNames,
			datasets : [ {
				label : 'attendance',
				data : data,
				backgroundColor : Array(data.length).fill(' #0099ff'),
				borderColor : Array(data.length).fill('black'),
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
}

function passwordValidation() {
	console.log('password validation called');
	var password1 = document.getElementById("newPasswordOne").value;
	var password2 = document.getElementById("newPasswordTwo").value;

	if (password1 == "" || password2 == "") {
		showMessage('You have to fill both fields', "warning");
	} else if (password1 != password2) {
		showMessage('Passwords must match!', "warning");
	} else if (password1.length < 6) {
		showMessage('Passwords must be at least 6 characters long!', "warning");
	} else {
		$.ajax({
			type : 'PUT',
			url : urlChangePassword,
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
			contentType : 'application/json',
			dataType : "json",
			data : passwordDTOJson(password1),
			success : function(data) {
				if (data) {
					setJwtToken(TOKEN_KEY, data.accessToken);
					showMessage(
							"Successful changing password, congratulations!",
							"success");
					$('.tab').show();
					$('#passwordValidation').hide();
				} else {
					showMessage("Error while changing password!", "warning");
				}

			},
			error : function(jqXHR, textStatus, errorThrown) {
				showMessage(jqXHR.status, "error");
				showMessage(textStatus, "error");
				showMessage(errorThrown, "error");

			}
		})
	}

}

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
						document.getElementById("airlineCityEdit").value = data.city;
						document
								.getElementById("airlinePromotionalDescriptionEdit").value = data.promotionalDescription;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");

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
	var numOfSeatsEconomy = document.getElementById("numberOfSeatsEconomy").value;
	var numOfSeatsBusiness = document.getElementById("numberOfSeatsBusiness").value;
	var numOfSeatsFirst = document.getElementById("numberOfSeatsFirst").value;
	var numOfStops = document.getElementById("numberOfFlightStops").value;
	dateOfFlight = dateOfFlight.replace("T", " ");
	dateOfArrival = dateOfArrival.replace("T", " ");
	var stops = "";
	var i = 0
	if (flightNumberRegister == "" || costOfFlight == "" || dateOfFlight == ""
			|| dateOfArrival == "" || numOfSeatsEconomy == ""
			|| numOfSeatsBusiness == "" || numOfSeatsFirst == ""
			|| numOfStops == "") {
		showMessage("You must fill in all of the fields", "warning");
		i = 1;
	}
	if (isNaN(costOfFlight) || costOfFlight < 0 || isNaN(numOfSeatsEconomy)
			|| numOfSeatsEconomy < 0 || isNaN(numOfSeatsBusiness)
			|| numOfSeatsBusiness < 0 || isNaN(numOfSeatsFirst)
			|| numOfSeatsFirst < 0 || isNaN(numOfStops) || numOfStops < 0) {
		showMessage(
				"You must insert positive number for cost of flight,number of seats and stops!",
				"warning");
		i = 1;
	}
	if (startDestinationRegister == finalDestinationRegister) {
		showMessage("Start and final destination of flight cannot be same!",
				"warning");
		i = 1;
	}
	if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
		showMessage("Date of flight cannot be after date of arrival", "warning");
		i = 1;
	}
	if (new Date() > new Date(dateOfFlight)
			|| new Date() > new Date(dateOfArrival)) {
		showMessage("You cannot put dates in the past", "warning");
		i = 1;
	}
	if (new Date() > new Date(dateOfFlight)
			&& new Date() > new Date(dateOfArrival)) {
		showMessage("You cannot put dates in the past", "warning");
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
					costOfFlight, dateOfFlight, dateOfArrival,
					numOfSeatsEconomy, numOfSeatsBusiness, numOfSeatsFirst,
					numOfStops, stops),
			success : function(data) {
				showMessage("Successful added flight, congratulations!",
						"success");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				showMessage(jqXHR.status, "error");
				showMessage(textStatus, "error");
				showMessage(errorThrown, "error");

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
					var numOfSeatsEconomy = document
							.getElementById("numberOfSeatsEconomy").value;
					var numOfSeatsBusiness = document
							.getElementById("numberOfSeatsBusiness").value;
					var numOfSeatsFirst = document
							.getElementById("numberOfSeatsFirst").value;
					var numOfStops = document
							.getElementById("numberOfFlightStops").value;
					dateOfFlight = dateOfFlight.replace("T", " ");
					dateOfArrival = dateOfArrival.replace("T", " ");
					var i = 0
					if (flightNumberRegister == "" || costOfFlight == ""
							|| dateOfFlight == "" || dateOfArrival == ""
							|| numOfSeatsEconomy == ""
							|| numOfSeatsBusiness == ""
							|| numOfSeatsFirst == "" || numOfStops == "") {
						showMessage("You must fill in all of the fields",
								"warning");
						i = 1;
					}
					if (isNaN(costOfFlight) || costOfFlight < 0
							|| isNaN(numOfSeatsEconomy)
							|| numOfSeatsEconomy < 0
							|| isNaN(numOfSeatsBusiness)
							|| numOfSeatsBusiness < 0 || isNaN(numOfSeatsFirst)
							|| numOfSeatsFirst < 0 || isNaN(numOfStops)
							|| numOfStops < 0) {
						showMessage(
								"You must insert positive number for cost of flight,number of seats and stops!",
								"warning");
						i = 1;
					}
					if (startDestination == finalDestination) {
						showMessage(
								"Start and final destination of flight cannot be same!",
								"warning");
						i = 1;
					}
					if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
						showMessage(
								"Date of flight cannot be after date of arrival",
								"warning");
						i = 1;
					}
					if (new Date() > new Date(dateOfFlight)
							|| new Date() > new Date(dateOfArrival)) {
						showMessage("You cannot put dates in the past",
								"warning");
						i = 1;
					}
					if (new Date() > new Date(dateOfFlight)
							&& new Date() > new Date(dateOfArrival)) {
						showMessage("You cannot put dates in the past",
								"warning");
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
																				+ destination.worksWith.city
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
										showMessage(jqXHR.status, "error");
										showMessage(textStatus, "error");
										showMessage(errorThrown, "error");

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

$(document).on(
		"submit",
		"#form1",
		function(e) {
			e.preventDefault();
			var name = document.getElementById("airlineNameEdit").value;
			var address = document.getElementById("airlineAddressEdit").value;
			var city = document.getElementById("airlineCityEdit").value;
			var promotionalDescription = document
					.getElementById("airlinePromotionalDescriptionEdit").value;

			if (name == "" || address == "" || promotionalDescription == ""
					|| city == "") {
				showMessage('None of the fields is allowed to be empty!',
						"warning");
			} else {

				$.ajax({
					type : 'PUT',
					url : urlRoot3,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					data : airlineToJson(name, address, promotionalDescription,
							city),
					dataType : "json",
					contentType : 'application/json',
					success : function(data) {
						showMessage("Successful editing, congratulations!",
								"success");

					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");

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
							showMessage('Error while finding loged one!',
									"warning");
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
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");
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
						showMessage(
								'At least one field is blank, please fill it up with proper information!',
								"warning");
					} else if (password1 != password2) {
						showMessage("Password must match, try again!",
								"warning");
					} else if (password1.length < 6) {
						showMessage(
								"Password must be at least 6 characters long, try again!",
								"warning");
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
											showMessage(
													"Successful editing, congratulations!",
													"success");
										} else {
											showMessage("Error while editing!",
													"warning");
										}

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										showMessage(jqXHR.status, "error");
										showMessage(textStatus, "error");
										showMessage(errorThrown, "error");

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
							+ destination.city + '</option>')
					$("#chooseDestination").append(option);
				})
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");

		}
	})

}

$(document)
		.on(
				'click',
				"#allFligh",
				function(e) {
					e.preventDefault();
					$("#tableAllFlights").empty();
					$
							.ajax({
								type : 'GET',
								url : urlRoot9,
								headers : createAuthorizationTokenHeader(TOKEN_KEY),
								contentType : 'application/json',
								success : function(data) {
									var list = data == null ? []
											: (data instanceof Array ? data
													: [ data ]);
									if (list.length > 0) {
										var tabela = document
												.getElementById("tableAllFlights");
										var count = 1;
										for ( var flight in list) {
											console.log('counter: ' + flight);
											var row = tabela.insertRow(flight);
											var cell1 = row.insertCell(0);
											var cell2 = row.insertCell(1);
											var cell3 = row.insertCell(2);
											var cell4 = row.insertCell(3);
											var cell5 = row.insertCell(4);
											var cell6 = row.insertCell(5);
											var cell7 = row.insertCell(6);
											var cell8 = row.insertCell(7);
											var cell9 = row.insertCell(8);
											cell1.innerHTML = list[flight].number;
											cell2.innerHTML = list[flight].startAirline.city;
											cell3.innerHTML = list[flight].finalAirline.city;
											cell4.innerHTML = list[flight].cost;
											cell5.innerHTML = list[flight].dateOfStart;
											cell6.innerHTML = list[flight].dateOfEnd;
											cell7.innerHTML = list[flight].numOfStops;
											cell8.innerHTML = list[flight].lengthOfFlight;
											cell9.innerHTML = '<button name=\"'
													+ list[flight].id
													+ '\" class=\"chooseFlight\" class="btn btn-primary">Choose flight</button>';
										}
										var row = tabela.insertRow(0);
										var cell1 = row.insertCell(0);
										var cell2 = row.insertCell(1);
										var cell3 = row.insertCell(2);
										var cell4 = row.insertCell(3);
										var cell5 = row.insertCell(4);
										var cell6 = row.insertCell(5);
										var cell7 = row.insertCell(6);
										var cell8 = row.insertCell(7);
										var cell9 = row.insertCell(8);
										cell1.innerHTML = "Flight number";
										cell2.innerHTML = "Start destination";
										cell3.innerHTML = "Final destination";
										cell4.innerHTML = "Cost";
										cell5.innerHTML = "Date of flight";
										cell6.innerHTML = "Date of arrival";
										cell7.innerHTML = "Number of stops";
										cell8.innerHTML = "Length of flight";
										cell9.innerHTML = "";
									} else {
										$("#tableAllFlights").append(
												"<h3>No flights found </h3>");
									}

								},
								error : function(jqXHR, textStatus, errorThrown) {
									showMessage(jqXHR.status, "error");
									showMessage(textStatus, "error");
									showMessage(errorThrown, "error");

								}
							})

				})

$(document)
		.on(
				'click',
				'.chooseFlight',
				function(e) {
					console.log('choose flight clicked');
					e.preventDefault();
					sessionStorage.removeItem("choosenSeats");
					var id = $(this).attr('name');
					sessionStorage.setItem("flightId", JSON.stringify(id));
					$("#seatsFlight").empty();
					$("#seatsFlight").append(
							"<h3>Click on seats you want to reserve</h3>")
					$("#seatsFlight")
							.append(
									"<h3>If you want to cancel seat click again on it</h3>")
					$
							.ajax({
								type : 'GET',
								dataType : 'json',
								url : urlRoot14 + "/" + id,
								headers : createAuthorizationTokenHeader(TOKEN_KEY),
								success : function(data) {
									var table = $('<table align="center"></table>')
									var list = data.firstClass == null ? []
											: (data.firstClass instanceof Array ? data.firstClass
													: [ data.firstClass ]);
									var list2 = data.businessClass == null ? []
											: (data.businessClass instanceof Array ? data.businessClass
													: [ data.businessClass ]);
									var list3 = data.economyClass == null ? []
											: (data.economyClass instanceof Array ? data.economyClass
													: [ data.economyClass ]);
									var i = 1;
									var j = 1;

									var tr;
									for ( var fr in list) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}
										console.log(list[fr].taken);

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');

										}
										if (list[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else {
											tr
													.append('<td id="a"><div class="foo blue" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									j = 1;
									for ( var fr in list2) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');
										}
										if (list2[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list2[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else {
											tr
													.append('<td id="a"><div class="foo green" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									j = 1;
									for ( var fr in list3) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');
										}
										if (list3[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list3[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}

										else {
											tr
													.append('<td id="a"><div class="foo purple" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									$("#seatsFlight").append('<br><br>')
									$("#seatsFlight").append(table);
									$("#seatsFlight").append('<br><br>')
									$("#seatsFlight")
											.append(
													'<button id="quickBooking" class="btn btn-primary">Quick booking</button>');
									$("#seatsFlight").append(
											'<div class="divider"/>')
									$("#seatsFlight")
											.append(
													'<button id="deleteSeats" class="btn btn-primary">Delete</button>');

								},
								error : function(jqXHR, textStatus, errorThrown) {
									showMessage(jqXHR.status, "error");
									showMessage(textStatus, "error");
									showMessage(errorThrown, "error");

								}
							})

					openCity(e, 'flightReservation');

				});

$(document).on(
		'click',
		'#addFlightButton',
		function(e) {
			e.preventDefault();
			$.ajax({
				type : 'GET',
				url : urlRoot2,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				success : function(data) {
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (data == null) {

					} else {
						var option = $('<option name="' + data.id + '">'
								+ data.city + '</option>');
						$("#startDestinationRegister").append(option);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");

				}
			})
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
							// $.each(list, function(index, destination) {
							// var option = $('<option name="'
							// + destination.worksWith.id + '">'
							// + destination.worksWith.name
							// + '</option>')
							// $("#startDestinationRegister").append(option);
							// })
							$.each(list, function(index, destination) {
								var option = $('<option name="'
										+ destination.worksWith.id + '">'
										+ destination.worksWith.city
										+ '</option>')
								$("#finalDestinationRegister").append(option);
							})

						}

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");

				}
			})
		})

$(document)
		.on(
				'click',
				'#a',
				function(e) {
					e.preventDefault();
					var id = $(this).find('div').attr('id');
					if (sessionStorage["choosenSeats"]) {

					} else {
						var choosenSeats = [];
						sessionStorage.setItem("choosenSeats", JSON
								.stringify(choosenSeats));
					}
					var choosenSeats = JSON
							.parse(sessionStorage["choosenSeats"]);
					var i = 0;
					for ( var s in choosenSeats) {
						console.log(s);
						if (choosenSeats[s] == id) {
							i = 1;
							$
									.ajax({
										type : 'GET',
										url : urlRoot15 + "/" + id,
										headers : createAuthorizationTokenHeader(TOKEN_KEY),
										dataType : "json",
										success : function(data) {
											console.log((data.fc).toString())
											if ((data.fc).toString() == "ECONOMY") {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo purple");
											} else if ((data.fc).toString() == "BUSINESS") {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo green");
											} else {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo blue");
											}
											choosenSeats.splice(s, 1);
											sessionStorage
													.setItem(
															"choosenSeats",
															JSON
																	.stringify(choosenSeats));

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											showMessage(jqXHR.status, "error");
											showMessage(textStatus, "error");
											showMessage(errorThrown, "error");
										}

									})
							break;
						}
					}
					if (i == 0) {
						choosenSeats.push(id);
						sessionStorage.setItem("choosenSeats", JSON
								.stringify(choosenSeats));
						$(this).find('div').removeClass(
								$(this).find('div').attr('class')).addClass(
								"foo grey");
					}
				})

$(document).on(
		'click',
		'#book',
		function(e) {
			e.preventDefault();
			var popust = document.getElementById("discount").value;
			console.log("popust", popust);
			if (isNaN(popust) || popust < 0 || popust > 70) {
				showMessage("Discount must be number between 0 and 70",
						"warning");
			} else {
				sed = "";
				var sedista = JSON.parse(sessionStorage["choosenSeats"]);
				for ( var s in sedista) {
					sed += sedista[s] + "*";
				}
				console.log(sed);
				sed.substring(0, sed.length - 1);
				sessionStorage.removeItem("choosenSeats");
				$.ajax({
					type : 'POST',
					url : urlRoot16 + "/" + sed + "/" + popust,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					contentType : 'application/json',
					success : function(data) {
						showMessage(
								"Successful added to quick booking tickets!",
								"success");
						openCity(event, 'allFlights');

					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");
					}
				})
			}

		})

$(document).on('click', '#findAmountAirline', function(e) {
	e.preventDefault();
	var startDate = document.getElementById("amountSearchStartDate").value;
	var endDate = document.getElementById("amountSearchEndDate").value;
	if (startDate == "") {
		startDate = "0000-00-00"
	}
	if (endDate == "") {
		endDate = "0000-00-00"
	}
	var finalPath = urlRoot20 + "/" + startDate + "/" + endDate;
	// popuni za report 4
	$.ajax({
		type : 'GET',
		url : finalPath,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			$("#amountAirlineValue").html(data.toFixed(2));
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})

});

$(document)
		.on(
				'click',
				'#quickBooking',
				function(e) {
					e.preventDefault();
					$("#deleteSeats").remove();
					$("#quickBooking").remove();
					$("#seatsFlight")
							.append(
									'<label>Discount:</label><input type="text" id="discount" size="10" maxlength="2"/>');
					$("#seatsFlight").append('<div class="divider"/>')
					$("#seatsFlight")
							.append(
									'<button id="book" class="btn btn-primary">Finish</button>')
					// sed="";
					// var sedista = JSON.parse(sessionStorage["choosenSeats"]);
					// for ( var s in sedista) {
					// sed += sedista[s] + "*";
					// }
					// console.log(sed);
					// sed.substring(0, sed.length - 1);
					// sessionStorage.removeItem("choosenSeats");
					// $
					// .ajax({
					// type : 'POST',
					// url : urlRoot16+"/"+sed,
					// headers : createAuthorizationTokenHeader(TOKEN_KEY),
					// contentType : 'application/json',
					// success : function(data) {
					// showMessage("Successful added to quick booking
					// tickets!");
					// openCity(event, 'allFlights');
					//	
					// },
					// error : function(jqXHR, textStatus, errorThrown) {
					// showMessage(jqXHR.status);
					// showMessage(textStatus);
					// showMessage(errorThrown);
					// }
					// })

				})

$(document).on('click', "#deleteSeats", function(e) {
	e.preventDefault();
	sed = "";
	var sedista = JSON.parse(sessionStorage["choosenSeats"]);
	for ( var s in sedista) {
		sed += sedista[s] + "*";
	}
	sed.substring(0, sed.length - 1);
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'DELETE',
		url : urlRoot17 + "/" + sed,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			showMessage("Successful deleted seats!", "success");
			openCity(event, 'allFlights');

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
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
				showMessage("There is no destination to add!", "warning");
			} else {
				$.ajax({
					type : 'POST',
					url : urlRoot7 + "/" + destination,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					contentType : 'application/json',
					success : function(data) {
						showMessage("Successfully added destination.",
								"success");
						$(
								"#chooseDestination option[value='"
										+ destination + "']").remove();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");

					}
				})
			}
		})

$(document).on('submit', '#passwordValidationForm', function(e) {
	e.preventDefault();

	passwordValidation();

});

function passwordDTOJson(password1) {
	return JSON.stringify({
		"password" : password1

	})
}

function createFlightToJSON(flightNumberRegister, startDestinationRegister,
		finalDestinationRegister, costOfFlight, dateOfFlight, dateOfArrival,
		numOfSeatsEconomy, numOfSeatsBusiness, numOfSeatsFirst, numOfStops,
		stops) {
	return JSON.stringify({
		"flightNumberRegister" : flightNumberRegister,
		"startDestinationRegister" : startDestinationRegister,
		"finalDestinationRegister" : finalDestinationRegister,
		"costOfFlight" : costOfFlight,
		"dateOfFlight" : dateOfFlight,
		"dateOfArrival" : dateOfArrival,
		"numOfSeatsEconomy" : numOfSeatsEconomy,
		"numOfSeatsBusiness" : numOfSeatsBusiness,
		"numOfSeatsFirst" : numOfSeatsFirst,
		"numOfStops" : numOfStops,
		"stops" : stops,
	})
}

function airlineToJson(name, address, promotionalDescription, city) {
	return JSON.stringify({
		"airlineNameRegister" : name,
		"airlineAddressRegister" : address,
		"airlinePromotionalDescription" : promotionalDescription,
		"city" : city,

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