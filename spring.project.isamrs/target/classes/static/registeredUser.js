var urlShowHotels = "http://localhost:8080/showHotels";
var urlShowRentacars = "http://localhost:8080/showRentACars";
var urlShowAirlines = "http://localhost:8080/showAirlines";
var urlFindRentacars = "http://localhost:8080/findRentacars";
var urlFindAirlines = "http://localhost:8080/findAirlines";
var urlFindHotels = "http://localhost:8080/findHotels";
var urlRoot = "http://localhost:8080/getAllFlights";
var urlRoot2 = "http://localhost:8080/searchFlight";
var urlRoot3 = "http://localhost:8080/getAllAirlines";
var urlRoot4 = "http://localhost:8080/api/editUser";
var urlRoot5 = "http://localhost:8080/api/getLogged";
var urlRoot6 = "http://localhost:8080/api/findFriends";
var urlRoot7 = "http://localhost:8080/api/sendFriendRequest";
var urlRoot8 = "http://localhost:8080/api/getFriendRequests";
var urlRoot9 = "http://localhost:8080/api/acceptFriend";
var urlRoot10 = "http://localhost:8080/api/rejectFriend";
var urlRoot11 = "http://localhost:8080/api/getAllFriends";
var urlRoot12 = "http://localhost:8080/api/getAllFriendRequests";
var urlRoot13 = "http://localhost:8080/api/removeFriend";
var TOKEN_KEY = 'jwtToken';

getLogged();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function fillTableAirlines(data) {
	console.log('fill table airlines called');
	var response = data;
	$("#tableOfAirlines").find("tr").remove();
	var tabela = document.getElementById("tableOfAirlines");
	console.log(tabela);

	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\"chooseAirline\" class="btn btn-primary">Choose</button>';
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "";
	cell5.innerHTML = "Location";

}

function fillTableHotels(data) {
	console.log('fill table hotels called');
	var response = data;
	$("#tableOfHotels").find("tr").remove();
	var tabela = document.getElementById("tableOfHotels");
	console.log(tabela);

	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\"chooseHotel\" class="btn btn-primary">Choose</button>';
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "";
	cell5.innerHTML = "Location";

}

function findAirlines() {
	console.log('Find airlines called!');

	var field = document.getElementById("nameLocationAirline").value;
	console.log('Field: ' + field);
	if (field == "") {
		alert("Field is not allowed to be empty, fill it!");
	} else {

		$.ajax({
			type : 'GET',
			url : urlFindAirlines + "/" + field,
			dataType : "json",
			success : function(data) {
				console.log('uslo u success');
				fillTableAirlines(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);
			}

		})
	}
}

function findHotels() {
	console.log('Find hotels called!');

	var field = document.getElementById("nameLocationHotel").value;
	console.log('Field: ' + field);
	if (field == "") {
		alert("Field is not allowed to be empty, fill it!");
	} else {

		$.ajax({
			type : 'GET',
			url : urlFindHotels + "/" + field,
			dataType : "json",
			success : function(data) {
				console.log('uslo u success');
				fillTableHotels(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);
			}

		})
	}
}

function showAirlines(criteria) {
	console.log('showing airlines');
	$.ajax({
		type : 'GET',
		url : urlShowAirlines + "/" + criteria,
		dataType : "json",
		success : function(data) {
			fillTableAirlines(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showHotels(criteria) {
	console.log('showing hotels');

	$.ajax({
		type : 'GET',
		url : urlShowHotels + "/" + criteria,
		dataType : "json",
		success : function(data) {

			fillTableHotels(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showRentacars(criteria) {
	console.log('showing rentacars');

	$.ajax({
		type : 'GET',
		url : urlShowRentacars + "/" + criteria,
		dataType : "json",
		success : function(data) {
			fillTableRentacars(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function fillTableRentacars(data) {
	var response = data;
	$("#tableOfRentacars").find("tr").remove();
	var tabela = document.getElementById("tableOfRentacars");
	console.log(tabela);

	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = response[counter].grade;
		cell5.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\"chooseRentacar\" class="btn btn-primary">Choose</button>';
		cell6.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);

	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "Grade";
	cell5.innerHTML = "";
	cell6.innerHTML = "Location";

}

function findRentacars() {
	console.log('find rent a car called');
	var field = document.getElementById("nameLocationRentACar").value;
	console.log('Field: ' + field);
	if (field == "") {
		alert("Field is not allowed to be empty, fill it!");
	} else {

		$.ajax({
			type : 'GET',
			url : urlFindRentacars + "/" + field,
			dataType : "json",
			success : function(data) {
				console.log('uslo u success');
				fillTabelRentacars(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);
			}

		})
	}
}

function findFriends() {
	var name = document.getElementById("friendRequestName").value;
	var surname = document.getElementById("friendRequestSurname").value;
	$("#foundFriends").empty();
	if (name == "" && surname == "") {
		alert("Please enter name and/or surname")
	} else {
		if (surname == "") {
			surname = "no_surname"
		}
		$
				.ajax({
					type : 'GET',
					url : urlRoot6 + "/" + name + "/" + surname,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					contentType : 'application/json',
					success : function(data) {

						$
								.ajax({
									type : 'GET',
									url : urlRoot12,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									success : function(data2) {
										var list = data == null ? []
												: (data instanceof Array ? data
														: [ data ]);
										var list2 = data2 == null ? []
												: (data2 instanceof Array ? data2
														: [ data2 ]);
										if (list.length > 0) {
											$("#foundFriends").append(
													"<h3>Found friends: </h3>")
											var tabela = document
													.getElementById("foundFriends");
											var count = 1
											for ( var friend in list) {
												console.log('counter: '
														+ friend);
												var row = tabela
														.insertRow(friend);
												var cell1 = row.insertCell(0);
												var cell2 = row.insertCell(1);
												var cell3 = row.insertCell(2);

												cell1.innerHTML = list[friend].firstName;
												cell2.innerHTML = list[friend].lastName;
												var i = 0;

												if (list2.length > 0) {
													for ( var fr in list2) {
														if (list[friend].username == list2[fr].received.username
																&& list2[fr].accepted == false) {
															i = 1;
														}
														if (list[friend].username == list2[fr].received.username
																&& list2[fr].accepted == true) {
															i = 2;
														}
														if (list[friend].username == list2[fr].sent.username
																&& list2[fr].accepted == true) {
															i = 2;
														}

													}
												}
												if (i == 1) {
													cell3.innerHTML = '<button style="background-color:grey" name=\"'
															+ list[friend].id
															+ '\" id=\"requestSent\" class="btn btn-primary">Request sent</button>';
													$("#requestSent").attr(
															'disabled',
															'disabled');
												} else if (i == 2) {
													cell3.innerHTML = '<button style="background-color:green" name=\"'
															+ list[friend].id
															+ '\" id=\"friendsButton\" class="btn btn-primary">Friends</button>';
													$("#friendsButton").attr(
															'disabled',
															'disabled');
												} else if (i == 0) {
													cell3.innerHTML = '<button name=\"'
															+ list[friend].id
															+ " "
															+ (count)
															+ '\" id=\"addFriend\" class="btn btn-primary">Add friend</button>';

												}
												console.log(i);

												count++;

											}
											var row = tabela.insertRow(0);
											var cell1 = row.insertCell(0);
											var cell2 = row.insertCell(1);
											var cell3 = row.insertCell(2);
											cell1.innerHTML = "Name";
											cell2.innerHTML = "Last name";
											cell3.innerHTML = "";

										}

										else {
											$("#foundFriends")
													.append(
															"<h3>No friends found </h3>");
										}

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										alert(jqXHR.status);
										alert(textStatus);
										alert(errorThrown);
									}
								})

					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.status);
						alert(textStatus);
						alert(errorThrown);
					}

				})

	}
}

$(document).on('click', '#addFriend', function(e) {
	e.preventDefault();
	var id = $(this).attr("name").split(" ")[0];
	var name = $(this).attr("name");
	console.log("User id" + id);
	$.ajax({
		type : 'POST',
		url : urlRoot7 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			alert("Successful sending friend request, congratulations!");
			$("button[name='" + name + "']").attr('disabled', 'disabled');
			$("button[name='" + name + "']").text('Request sent');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
})

$(document).on('click', '#searchFriends', function(e) {
	e.preventDefault();
	console.log("Kliknuo");
	findFriends();
})

$(document).on('click', '.tablinks', function(e) {
	e.preventDefault();
	$("#foundFlights").empty();
})

function searchFlights() {

}
function searchFilter() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot3,
				contentType : 'application/json',
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
								var option = $('<option value="'
										+ destination.id + '">'
										+ destination.name + '</option>')
								$("#startDestination").append(option);
							})
							$.each(list, function(index, destination) {
								var option = $('<option value="'
										+ destination.id + '">'
										+ destination.name + '</option>')
								$("#finalDestination").append(option);
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

}

$(document)
		.on(
				'click',
				'#searchFlightButton',
				function(e) {
					e.preventDefault();
					$("#foundFlights").empty();

					var startDestination = document
							.getElementById("startDestination").value;
					var finalDestination = document
							.getElementById("finalDestination").value;
					var dateOfFlight = document.getElementById("dateOfFlight").value;
					var dateOfArrival = document.getElementById("dateOfReturn").value;
					var from = document.getElementById("from").value;
					var to = document.getElementById("to").value;
					var fromL = document.getElementById("fromL").value;
					var toL = document.getElementById("toL").value;
					var name = document.getElementById("nameL").value;
					var i = 0;
					if (startDestination == "" || finalDestination == ""
							|| dateOfFlight == "") {
						i = 1;
						alert("All fields must be filled in.")
					}
					if (startDestination == finalDestination) {
						alert("Start and final destination cannot be the same");
						i = 1;
					}
					if(from==""){
						from="noFrom";
					}
					if(to==""){
						to="noTo";
					}
					if(fromL==""){
						fromL="noFromL";
					}
					if(toL==""){
						toL="noToL";
					}
					if(name==""){
						name="noName";
					}
					if (dateOfArrival != "") {
						if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
							i = 1;
							alert("Date of flight cannot be after date of return!");
						}
						if (new Date() > new Date(dateOfFlight)
								|| new Date() > new Date(dateOfArrival)) {
							i = 1;
							alert("Dates cannot be in the past");
						}
						if (new Date() > new Date(dateOfFlight)
								&& new Date() > new Date(dateOfArrival)) {
							i = 1;
							alert("Dates cannot be in the past");
						}
					} else {
						if (new Date() > new Date(dateOfFlight)) {
							i = 1;
							alert("Dates cannot be in the past");
						}
					}
					if (i == 0) {
						if (dateOfArrival == "") {
							dateOfArrival = "noDate";
						}

						$
								.ajax({
									type : 'GET',
									url : urlRoot2 + "/" + startDestination
											+ "/" + finalDestination + "/"
											+ dateOfFlight + "/"
											+ dateOfArrival + "/" + from + "/"
											+ to + "/" + fromL + "/" + toL
											+ "/" + name,
									contentType : 'application/json',
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
												$("#foundFlights")
														.append(
																"<h3>Found flights: </h3>")
												var tabela = document
														.getElementById("foundFlights");
												var count = 1;
												for ( var flight in list) {
													console.log('counter: '
															+ flight);
													var row = tabela
															.insertRow(flight);
													var cell1 = row
															.insertCell(0);
													var cell2 = row
															.insertCell(1);
													var cell3 = row
															.insertCell(2);
													var cell4 = row
															.insertCell(3);
													var cell5 = row
															.insertCell(4);
													var cell6 = row
															.insertCell(5);
													var cell7 = row
															.insertCell(6);
													var cell8 = row
															.insertCell(7);
													var cell9 = row
															.insertCell(8);
													cell1.innerHTML = list[flight].number;
													cell2.innerHTML = list[flight].startAirline.name;
													cell3.innerHTML = list[flight].finalAirline.name;
													cell4.innerHTML = list[flight].cost;
													cell5.innerHTML = new Date(
															list[flight].dateOfStart);
													cell6.innerHTML = new Date(
															list[flight].dateOfEnd);
													cell7.innerHTML = list[flight].numOfStops;
													cell8.innerHTML = list[flight].lengthOfFlight;
													cell9.innerHTML = '<button name=\"'
															+ list[flight].id
															+ '\" id=\"chooseFlight\" class="btn btn-primary">Choose flight</button>';
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
												$("#foundFlights")
														.append(
																"<h3>No flights found </h3>");
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

					} else {

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
							document.getElementById("userUsernameEdit").value = data.username;
							document.getElementById("userFirstNameEdit").value = data.firstName;
							document.getElementById("userLastNameEdit").value = data.lastName;
							document.getElementById("userEmailEdit").value = data.email;
							document.getElementById("userPhoneNumberEdit").value = data.phoneNumber;
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
					var username = document.getElementById("userUsernameEdit").value;
					var password1 = document
							.getElementById("userPassword1Edit").value;
					var password2 = document
							.getElementById("userPassword2Edit").value;
					var firstName = document
							.getElementById("userFirstNameEdit").value;
					var lastName = document.getElementById("userLastNameEdit").value;
					var email = document.getElementById("userEmailEdit").value;
					var phoneNumber = document
							.getElementById("userPhoneNumberEdit").value;
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
									data : userEditToJSON(username, password1,
											firstName, lastName, email,
											phoneNumber),
									success : function(data) {
										alert("Successful editing, congratulations!");

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										alert(jqXHR.status);
										alert(textStatus);
										alert(errorThrown);

									}
								});
					}
				});

function seeFriendRequests() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot8,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				contentType : 'application/json',
				success : function(data) {
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (list.length > 0) {
						$("#friendRequestTable").append(
								"<h3>Found friends: </h3>")
						var tabela = document
								.getElementById("friendRequestTable");
						var count = 1;
						for ( var friend in list) {
							console.log('counter: ' + friend);
							var row = tabela.insertRow(friend);
							var cell1 = row.insertCell(0);
							var cell2 = row.insertCell(1);
							var cell3 = row.insertCell(2);
							var cell4 = row.insertCell(3);

							cell1.innerHTML = list[friend].sent.firstName;
							cell2.innerHTML = list[friend].sent.lastName;
							cell3.innerHTML = '<button style="background-color:green" name=\"'
									+ list[friend].sent.id
									+ " "
									+ count
									+ '\" id=\"acceptFriend\" class="btn btn-primary">Accept</button>';
							cell4.innerHTML = '<button style="background-color:red" name=\"'
									+ list[friend].sent.id
									+ " "
									+ count
									+ '\" id=\"rejectFriend\" class="btn btn-primary">Reject</button>';

							count++;
						}
						var row = tabela.insertRow(0);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						var cell4 = row.insertCell(3);
						cell1.innerHTML = "Name";
						cell2.innerHTML = "Last name";
						cell3.innerHTML = "";
						cell4.innerHTML = "";
					} else {
						$("#friendRequestTable").append(
								"<h3>No friend requests </h3>")
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}
			})
}

function showAllFriends() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot11,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				contentType : 'application/json',
				success : function(data) {
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (list.length > 0) {
						$("#friendList").append("<h3>Found friends: </h3>")
						var tabela = document.getElementById("friendList");
						var count = 1
						for ( var friend in list) {
							console.log('counter: ' + friend);
							var row = tabela.insertRow(friend);
							var cell1 = row.insertCell(0);
							var cell2 = row.insertCell(1);
							var cell3 = row.insertCell(2);

							cell1.innerHTML = list[friend].firstName;
							cell2.innerHTML = list[friend].lastName;
							cell3.innerHTML = '<button name=\"'
									+ list[friend].id
									+ " "
									+ count
									+ '\" id=\"removeFriend\" class="btn btn-primary">Remove friend</button>';

							count++;

						}
						var row = tabela.insertRow(0);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						cell1.innerHTML = "Name";
						cell2.innerHTML = "Last name";
						cell3.innerHTML = "";
					} else {
						$("#friendList").append("<h3>No friends</h3>")
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}
			})

}

$(document).on('click', '#removeFriend', function(e) {
	e.preventDefault();
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'DELETE',
		url : urlRoot13 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			alert("Successfully removed friend from a friend list");
			document.getElementById("friendList").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})
})

$(document).on('click', '#listOfFri', function(e) {
	e.preventDefault();
	$("#friendList").empty();
	showAllFriends();
})

$(document).on('click', '#acceptFriend', function(e) {
	e.preventDefault();
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'PUT',
		url : urlRoot9 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			alert("Successfully accepted friend request.");
			document.getElementById("friendRequestTable").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})

})

$(document).on('click', '#rejectFriend', function(e) {
	e.preventDefault();
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'DELETE',
		url : urlRoot10 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			alert("Successfully rejected friend request.");
			document.getElementById("friendRequestTable").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})

})

$(document).on('click', '#friendR', function(e) {
	e.preventDefault();
	$("#friendRequestTable").empty();
	seeFriendRequests();
})

$(document).on('click', '#searchFilter', function(e) {
	e.preventDefault();
	searchFilter();
})

function searchToJson(startDestination, finalDestination, dateOfFlight,
		dateOfArrival, from, to, fromL, toL, name) {
	return JSON.stringify({
		"startDestination" : startDestination,
		"finalDestination" : finalDestination,
		"startDate" : dateOfFlight,
		"endDate" : dateOfArrival,
		"from" : from,
		"to" : to,
		"fromL" : fromL,
		"toL" : toL,
		"name" : name
	})
}

function userEditToJSON(username, password1, firstName, lastName, email,
		phoneNumber) {
	return JSON.stringify({
		"username" : username,
		"password" : password1,
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : email,
		"phoneNumber" : phoneNumber,
	})
}

function rentacarReservation(id) {
	console.log('Rentacar reservation called.');
}
$(document).on('click', '#hotelsButton', function(e) {
	console.log('hotels button clicked');
	// e.preventDefault();

	showHotels("bez");

});

$(document).on('click', '#rentACarButton', function(e) {
	console.log('rent a car button clicked');
	// e.preventDefault();

	showRentacars("bez");

});

$(document).on('click', '.airlinesButton', function(e) {
	console.log('airlines button clicked');
	// e.preventDefault();

	showAirlines("bez");

});
$(document).on('click', '.sortByNameHotels', function(e) {
	console.log('sort by name clicked');
	// e.preventDefault();

	showHotels("sortByNameHotels");

});

$(document).on('click', '.sortByNameRentACars', function(e) {
	console.log('sort by name clicked');
	// e.preventDefault();

	showRentacars("sortByNameRentACars");

});

$(document).on('click', '.sortByAddressHotels', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();

	showHotels("sortByAddressHotels");

});

$(document).on('click', '.sortByAddressRentACars', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();

	showRentacars("sortByAddressRentACars");

});

$(document).on('click', '.searchRentACarButton', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();

	findRentacars();

	$(".sortByNameRentACars").hide();
	$(".sortByAddressRentACars").hide();

});

$(document).on('click', '.searchAirlineButton', function(e) {
	console.log('search airline button clicked');
	// e.preventDefault();

	findAirlines();

	$(".sortByNameAirlines").hide();
	$(".sortByAddressAirlines").hide();

});

$(document).on('click', '.searchHotelButton', function(e) {
	console.log('search hotel button clicked');
	// e.preventDefault();

	findHotels();

	$(".sortByNameHotels").hide();
	$(".sortByAddressHotels").hide();

});

$(document).on('click', '.chooseRentacar', function(e) {
	// e.preventDefault();
	var _this = $(this);

	console.log('car number' + '   ' + this.id);
	openCity(e, 'rentacarReservation');
	rentacarReservation(this.id);

});

$(document).on('click', '.sortByAddressAirlines', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();

	showAirlines("sortByAddressAirlines");

});

$(document).on('click', '.sortByNameAirlines', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();
	showAirlines("sortByNameAirlines");

});
