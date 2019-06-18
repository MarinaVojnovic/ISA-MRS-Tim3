var urlRoot1 = "http://localhost:8080/createRoom";
var urlRoot2 = "http://localhost:8080/getRooms";
var urlRoot3 = "http://localhost:8080/deleteRoom";
var urlRoot4 = "http://localhost:8080/findRoom";
var urlRoot5 = "http://localhost:8080/saveEditedRoom";
var urlRoot6 = "http://localhost:8080/findHotel";
var urlRoot7 = "http://localhost:8080/saveChangesHotel";
var urlRoot8 = "http://localhost:8080/api/getLogged";
var urlRoot9 = "http://localhost:8080/api/editUser";
var urlRoot10 = "http://localhost:8080/getHotelCustomerServices";
var urlRoot11 = "http://localhost:8080/createHotelCustomerService";
var urlRoot12 = "http://localhost:8080/getRoomFastReservations";
var urlRoot13 = "http://localhost:8080/deleteRoomFastReservation";
var urlRoot14 = "http://localhost:8080/createRoomFastReservation";
var urlGetFirstTime = "http://localhost:8080/api/isFirstTime";
var urlChangePassword = "http://localhost:8080/api/changePasswordFirstTime";
var urlRootFindHotelAmount = "http://localhost:8080/findHotelAmount";
var urlRootReportHotelAttendance = "http://localhost:8080/reportHotelAttendance";

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

$(document).on('click', '#editHotelButton', function(e) {
	findHotel();
});

$(document).on('click', '#editProfileButton', function(e) {
	getLogged();
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

function fillDatasAddRoomFastReservation() {
	$.ajax({
		type : 'GET',
		url : urlRoot2,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				fillTableRoomPick(data, "roomFastReservationRoomTable");
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
		url : urlRoot10,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				fillTableHotelCustomerServicesPick(data,
						"roomFastReservationHcsTable");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
}

function fillDatasDeleteRoomFastReservation() {
	$.ajax({
		type : 'GET',
		url : urlRoot12,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				fillTableRoomFastReservations(data,
						"tableDeleteRoomFastReservation");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
}

function fillHotelCustomerServices() {
	$.ajax({
		type : 'GET',
		url : urlRoot10,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				fillTableHotelCustomerServices(data,
						"tableHotelCustomerServices");
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
		url : urlRoot6,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				$.ajax({
					type : 'GET',
					url : urlRoot2,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data) {
							fillTableRooms(data, "reportHotelEachRoom");
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status, "error");
						showMessage(textStatus, "error");
						showMessage(errorThrown, "error");
					}

				})
				var ocena=0;
				if (data.gradeNumber!=0){
					ocena=Math.round(data.score/data.gradeNumber);
				}
				$("#reportHotelAverageGrade").html(ocena);

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
		url : urlRootReportHotelAttendance,
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

function fillTableHotelCustomerServices(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	var index = 0;
	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0)
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].price;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';

}

function fillTableRoomFastReservations(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	var indeks = 0;
	for ( var counter in response) {
		if (!response[counter].reserved) {
			var row = tabela.insertRow(indeks++);
			var cell0 = row.insertCell(0)
			var cell1 = row.insertCell(1);
			var cell2 = row.insertCell(2);
			var cell3 = row.insertCell(3);
			var cell4 = row.insertCell(4);
			var cell5 = row.insertCell(5);
			var cell6 = row.insertCell(6);
			var cell7 = row.insertCell(7);
			var cell8 = row.insertCell(8);

			cell0.innerHTML = response[counter].room.hotel.name;
			cell1.innerHTML = response[counter].room.roomNumber;
			cell2.innerHTML = response[counter].room.numberPeople;
			cell3.innerHTML = response[counter].startDate;
			cell4.innerHTML = response[counter].endDate;
			cell5.innerHTML = response[counter].originalPrice;
			cell6.innerHTML = response[counter].discount + "%";
			cell7.innerHTML = response[counter].newPrice;
			cell8.innerHTML = "<button class=\"deleteRFR\" id=\""
					+ response[counter].id
					+ "\" style=\" background:#cc0033;color:white\">Delete</button>"
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
	var cell8 = row.insertCell(8);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">Hotel</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Room number</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of people in room</p>';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">Start date</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">End date</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Original price</p>';
	cell6.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">Discount</p>';
	cell7.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">New price</p>';
	cell8.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%"></p>';

}

function fillTableHotelCustomerServicesPick(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0)
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);

		cell0.innerHTML = "<input type=\"checkbox\" name=\"hcsPick\" value=\""
				+ response[counter].id + "\">"
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].price;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">Pick extra services</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';

}

function fillTableRooms(data, table) {
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

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].hotel.name;
		cell2.innerHTML = response[counter].roomNumber;
		cell3.innerHTML = response[counter].price;
		cell4.innerHTML = response[counter].numberPeople;
		var ocena=0;
		if (response[counter].gradeNumber!=0){
			ocena=Math.round(response[counter].score/response[counter].gradeNumber);
		}
		cell5.innerHTML = ocena;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Hotel it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of room</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of people for room</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
}

function fillTableRoomPick(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);
		var cell5 = row.insertCell(5);

		cell0.innerHTML = "<input type=\"radio\" name=\"roomPick\" value=\""
				+ response[counter].id + "\">"
		cell1.innerHTML = response[counter].hotel.name;
		cell2.innerHTML = response[counter].roomNumber;
		cell3.innerHTML = response[counter].price;
		cell4.innerHTML = response[counter].numberPeople;
		var ocena=0;
		if (response[counter].gradeNumber!=0){
			ocena=Math.round(response[counter].score/response[counter].gradeNumber);
		}
		cell5.innerHTML = ocena;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Pick one room</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Hotel it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of room</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of people for room</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
}

function passwordValidation() {
	console.log('password validation called');
	var password1 = document.getElementById("newPasswordOne").value;
	var password2 = document.getElementById("newPasswordTwo").value;

	if (password1 == "" || password2 == "") {
		showMessage('You have to fill both fields', "warning");
	} else if (password1 != password2) {
		showMessage('Passwords must match!', "warning");
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

function checkFirstTime() {
	$.ajax({
		type : 'GET',
		url : urlGetFirstTime,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data) {
				window.location.href = "passwordFirstTime.html"
			} else {
				console.log('uslo ovde');

			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})
}

function getLogged() {
	var token = getJwtToken(TOKEN_KEY);
	if (token) {
		$
				.ajax({
					type : 'GET',
					url : urlRoot8,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data == null) {
							showMessage('Error while finding loged one!',
									"warning");
						} else {
							document.getElementById("hotelAdminUsernameEdit").value = data.username;
							document.getElementById("hotelAdminFirstNameEdit").value = data.firstName;
							document.getElementById("hotelAdminLastNameEdit").value = data.lastName;
							document.getElementById("hotelAdminEmailEdit").value = data.email;
							document
									.getElementById("hotelAdminPhoneNumberEdit").value = data.phoneNumber;
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
							.getElementById("hotelAdminUsernameEdit").value;
					var password1 = document
							.getElementById("hotelAdminPassword1Edit").value;
					var password2 = document
							.getElementById("hotelAdminPassword2Edit").value;
					var firstName = document
							.getElementById("hotelAdminFirstNameEdit").value;
					var lastName = document
							.getElementById("hotelAdminLastNameEdit").value;
					var email = document.getElementById("hotelAdminEmailEdit").value;
					var phoneNumber = document
							.getElementById("hotelAdminPhoneNumberEdit").value;
					if (username == "" || password1 == "" || password2 == ""
							|| firstName == "" || lastName == "" || email == ""
							|| phoneNumber == "") {
						showMessage(
								'At least one field is blank, please fill it up with proper information!',
								"warning");
					} else if (password1 != password2) {
						showMessage("Password must match, try again!",
								"warning");
					} else {
						$
								.ajax({
									type : 'PUT',
									url : urlRoot9,
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									contentType : 'application/json',
									dataType : "json",
									data : HotelAdminEditToJSON(username,
											password1, firstName, lastName,
											email, phoneNumber),
									success : function(data) {
										setJwtToken(TOKEN_KEY, data.accessToken);
										showMessage(
												"Successful editing, congratulations!",
												"success");

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

$(document).on(
		"submit",
		"#form3",
		function(e) {
			e.preventDefault();
			var name = document.getElementById("hotelNameEdit").value;
			var address = document.getElementById("hotelAddressEdit").value;
			var city = document.getElementById("hotelCityEdit").value;
			var promotionalDescription = document
					.getElementById("hotelPromotionalDescriptionEdit").value;

			if (name == "" || address == "" || promotionalDescription == ""
					|| city == "") {
				showMessage('None of the fields is allowed to be empty!',
						"warning");
			} else {

				$.ajax({
					type : 'PUT',
					url : urlRoot7,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					data : hotelToJson(name, address, promotionalDescription,
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

function findHotel() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot6,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("hotelNameEdit").value = data.name;
						document.getElementById("hotelAddressEdit").value = data.address;
						document.getElementById("hotelCityEdit").value = data.city;
						document
								.getElementById("hotelPromotionalDescriptionEdit").value = data.promotionalDescription;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");
				}

			})

}

function saveEditedRoom() {
	console.log('save edited room called');
	var id = document.getElementById("roomIdEdit").value;
	var roomNumber = document.getElementById("roomNumberEdit").value;
	var price = document.getElementById("roomPriceEdit").value;
	var numberPeople = document.getElementById("roomNumberPeopleEdit").value;

	console.log(roomNumber + ' ' + price + ' ' + numberPeople);

	if (roomNumber == "" || price == "" || numberPeople == "") {
		showMessage('None of the fields is allowed to be empty!', "warning");
	} else if (roomNumber <= 0 || roomNumber >= 1000) {
		showMessage('Number of room must be in a range [1, 999] !', "warning");
	} else if (price <= 0) {
		showMessage('Price must be positive number!', "warning");
	} else if (numberPeople <= 0 || numberPeople >= 6) {
		showMessage('Number of people in room must be in a range [1, 5] !',
				"warning");
	} else {

		$
				.ajax({
					type : 'PUT',
					url : urlRoot5,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					data : roomToJson(id, roomNumber, price, numberPeople),
					dataType : "json",
					contentType : 'application/json',
					success : function(data) {
						showRooms("forEdit");
						if (data.message == "Room successfully edited, congratulations!") {
							showMessage(data.message, "success")
						} else {
							showMessage(data.message, "warning");
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

function editRoom(roomId) {
	console.log('Edit rooms called');

	urlRoot4 = urlRoot4 + "/" + roomId;
	$
			.ajax({
				type : 'GET',
				url : urlRoot4,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('Room not found');

					} else {

						document.getElementById("roomIdEdit").value = data.id;
						document.getElementById("roomNumberEdit").value = data.roomNumber;
						document.getElementById("roomPriceEdit").value = data.price;
						document.getElementById("roomNumberPeopleEdit").value = data.numberPeople;

						console.log('Room successfully edited.');
					}

					urlRoot4 = "http://localhost:8080/findRoom";
					showRooms("forEdit");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");

				}

			})

}

function deleteRoom(roomId) {
	console.log('Delete room called');

	urlRoot3 = urlRoot3 + "/" + roomId;
	$.ajax({
		type : 'DELETE',
		url : urlRoot3,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data.message == "Successfully deleted room!") {
				showMessage(data.message, "success");
			} else {
				showMessage(data.message, "warning");
			}

			urlRoot3 = "http://localhost:8080/deleteRoom";
			showRooms("forDelete");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status, "error");
			showMessage(textStatus, "error");
			showMessage(errorThrown, "error");
		}

	})

}

function showRooms(type) {
	console.log('show rooms for deleting called');

	$
			.ajax({
				type : 'GET',
				url : urlRoot2,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {

					var response = data;
					$(".message").children().remove();
					$("#tableOfRoomsForDelete").find("tr").remove();
					$("#tableOfRoomsForEdit").find("tr").remove();
					var tabela;
					if (type == "forDelete") {
						tabela = document
								.getElementById("tableOfRoomsForDelete");
					} else {
						tabela = document.getElementById("tableOfRoomsForEdit");
					}

					console.log(tabela);

					var broj = 0;
					for ( var counter in response) {
						broj++;
						console.log('counter: ' + counter);
						var row = tabela.insertRow(counter);
						console.log(row);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						cell2.id = "roomNumber";
						var cell3 = row.insertCell(2);
						cell3.id = "price";
						var cell4 = row.insertCell(3);
						cell3.id = "numberPeople";
						var cell5 = row.insertCell(4);

						cell1.innerHTML = response[counter].id;
						console.log(cell1.innerHTML);
						console.log('ID PRE PODESAVANJA ' + cell1.id);
						cell2.innerHTML = response[counter].roomNumber;
						console.log(cell2.innerHTML);
						cell3.innerHTML = response[counter].price;
						cell4.innerHTML = response[counter].numberPeople;
						if (type == "forDelete") {
							cell5.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
									+ response[counter].id
									+ '\" class=\"deleteRoomButton\" class="btn btn-primary">Delete</button>';
						} else if (type == "forEdit") {
							cell5.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
									+ response[counter].id
									+ '\" class=\"editRoomButton\" class="btn btn-primary" >Edit</button>';
						}

					}
					if (broj != 0) {
						var row = tabela.insertRow(0);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						var cell4 = row.insertCell(3);
						var cell5 = row.insertCell(4);

						cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
						cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Room number</p>';
						cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Price</p>';
						cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Number of people</p>';
					} else {
						$(".message").append('<h3>No rooms found.</p>')
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");
				}

			})
}

$(document).on("click", "#removeRoomsButton", function(e) {

	showRooms("forDelete");
})

$(document).on("click", "#editRoomsButton", function(e) {

	showRooms("forEdit");
})

$(document)
		.on(
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var roomNumberRegister = document
							.getElementById("roomNumberRegister").value;
					var roomPeopleNumberRegister = document
							.getElementById("roomPeopleNumberRegister").value;
					var roomPriceRegister = document
							.getElementById("roomPriceRegister").value;
					if (roomNumberRegister == ""
							|| roomPeopleNumberRegister == ""
							|| roomPriceRegister == "") {
						showMessage(
								'At least one field is blank, please fill it up with proper information!',
								"warning");
					} else if (roomNumberRegister <= 0
							|| roomNumberRegister >= 1000) {
						showMessage(
								'Number of room must be in a range [1, 999] !',
								"warning");
					} else if (roomPriceRegister <= 0) {
						showMessage('Price must be positive number!', "warning");
					} else if (roomPeopleNumberRegister <= 0
							|| roomPeopleNumberRegister >= 6) {
						showMessage(
								'Number of people in room must be in a range [1, 5] !',
								"warning");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot1,
									contentType : 'application/json',
									dataType : "json",
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									data : createRoomToJSON(roomNumberRegister,
											roomPeopleNumberRegister,
											roomPriceRegister),
									success : function(data) {
										if (data.message == "Successful adding room, congratulations!") {
											showMessage(data.message, "success");
										} else {
											showMessage(data.message, "warning");
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

$(document)
		.on(
				"submit",
				"#addRoomFastReservationForm",
				function(e) {
					e.preventDefault();
					var roomId = $('input[name="roomPick"]:checked').val();
					var startDate = document
							.getElementById("roomFastReservationStartDate").value;
					var endDate = document
							.getElementById("roomFastReservationEndDate").value;
					var discount = document
							.getElementById("roomFastReservationDiscount").value;
					startDate = startDate.replace("T", " ");
					endDate = endDate.replace("T", " ");
					var hcsIds = "";
					$("input:checkbox[name='hcsPick']:checked").each(
							function() {
								hcsIds += $(this).val() + " ";
							});
					if (hcsIds != "") {
						hcsIds.substring(0, hcsIds.length - 1);
					}
					if (roomId == undefined || startDate == "" || endDate == ""
							|| discount == "") {
						showMessage(
								'At least one field is blank, please fill it up with proper information!',
								"warning");
					} else if (discount < 20 || discount > 70) {
						showMessage('Discount must be in a range [20, 70] !',
								"warning");
					} else if (new Date() >= new Date(startDate)) {
						showMessage(
								'You cannot put for fast reserving in past!',
								"warning");
					} else if (new Date(startDate) >= new Date(endDate)) {
						showMessage(
								'End date cant be before or equal to start date!',
								"warning");
					} else if ((new Date(startDate)).getTime() + 3024000000 < (new Date(
							endDate)).getTime()) {
						showMessage(
								'You cant reserve room for more than 5 weeks or 35 days!',
								"warning");
					} else if ((new Date(startDate)).getTime() + 7200000 > (new Date(
							endDate)).getTime()) {
						showMessage(
								'You cant reserve room for less than 2 hours!',
								"warning");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot14,
									contentType : 'application/json',
									dataType : "json",
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									data : createRoomFastReservationToJSON(
											roomId, startDate, endDate,
											discount, hcsIds),
									success : function(data) {
										if (data.message == "Succesfully added this room on fast reservation!") {
											showMessage(data.message, "success");
										} else {
											showMessage(data.message, "warning");
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

$(document)
		.on(
				"submit",
				"#hotelCustomerServiceForm",
				function(e) {
					e.preventDefault();
					var name = document
							.getElementById("hotelCustomerServiceName").value;
					var price = document
							.getElementById("hotelCustomerServicePrice").value;
					if (name == "" || price == "") {
						showMessage(
								'At least one field is blank, please fill it up with proper information!',
								"warning");
					} else if (price <= 0) {
						showMessage('Price must be positive number!', "warning");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot11,
									contentType : 'application/json',
									dataType : "json",
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									data : createHotelCustomerServiceToJSON(
											name, price),
									success : function(data) {
										showMessage(
												"Succesfully added new extra service!",
												"success");
										fillHotelCustomerServices();
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

$(document).on('click', '.deleteRoomButton', function(e) {
	var _this = $(this);

	console.log('room number' + '   ' + this.id);
	deleteRoom(this.id);
});

$(document).on('click', '#reportHotelButton', function(e) {
	fillDatas();
});

$(document).on('click', '#addHotelCustomerServiceButton', function(e) {
	fillHotelCustomerServices();
});

$(document).on('click', '#addRoomFastReservationButton', function(e) {
	fillDatasAddRoomFastReservation();
});

$(document).on('click', '#deleteRoomFastReservationButton', function(e) {
	fillDatasDeleteRoomFastReservation();
});

$(document).on('click', '.editRoomButton', function(e) {
	var _this = $(this);

	console.log('room number' + '   ' + this.id);
	openCity(e, 'editRoomFormular');
	editRoom(this.id);

});

$(document).on('submit', '#editRoomForm', function(e) {
	e.preventDefault();

	saveEditedRoom();
	openCity(e, 'editRoom');
	showRooms('forEdit');

});

$(document).on('click', '#findAmountHotel', function(e) {
	e.preventDefault();
	var startDate = document.getElementById("amountSearchStartDate").value;
	var endDate = document.getElementById("amountSearchEndDate").value;
	if (startDate == "") {
		startDate = "0000-00-00"
	}
	if (endDate == "") {
		endDate = "0000-00-00"
	}
	var finalPath = urlRootFindHotelAmount + "/" + startDate + "/" + endDate;
	$.ajax({
		type : 'GET',
		url : finalPath,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			$("#amountHotelValue").html(data.toFixed(2));
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status);
			showMessage(textStatus);
			showMessage(errorThrown);
		}

	})

});

$(document).on(
		'submit',
		'#hotelDiscountForm',
		function(e) {
			e.preventDefault();
			var hotelDiscountNumber = document
					.getElementById("hotelDiscountNumber").value;
			if (hotelDiscountNumber == "") {
				showMessage("Number cant be empty field!", "warning");
			} else if (hotelDiscountNumber < 1 || hotelDiscountNumber > 5) {
				showMessage("Number must be in range [1, 5]", "warning");
			} else {
				setHotelDiscount(hotelDiscountNumber);
				showMessage("Succesfully set new discount number!", "success");
			}

		});

$(document).on(
		'click',
		'.deleteRFR',
		function(e) {
			e.preventDefault();
			var ID = this.id;
			var finalPath = urlRoot13 + "/" + ID;
			$.ajax({
				type : 'DELETE',
				url : finalPath,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage("Succesfully deleted room fast reservation!",
							"success")
					fillDatasDeleteRoomFastReservation();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status, "error");
					showMessage(textStatus, "error");
					showMessage(errorThrown, "error");
				}

			})
		});

function createRoomToJSON(roomNumberRegister, roomPeopleNumberRegister,
		roomPriceRegister) {
	return JSON.stringify({
		"roomNumberRegister" : roomNumberRegister,
		"roomPeopleNumberRegister" : roomPeopleNumberRegister,
		"roomPriceRegister" : roomPriceRegister,
	})
}

function roomToJson(id, roomNumber, price, numberPeople) {
	return JSON.stringify({
		"id" : id,
		"roomNumberRegister" : roomNumber,
		"roomPriceRegister" : price,
		"roomPeopleNumberRegister" : numberPeople,
	})
}

function hotelToJson(name, address, promotionalDescription, city) {
	return JSON.stringify({
		"hotelNameRegister" : name,
		"hotelAddressRegister" : address,
		"hotelPromotionalDescription" : promotionalDescription,
		"city" : city,

	})
}

function loginToJSON(token) {
	return JSON.stringify({
		"token" : token,
	})
}

function HotelAdminEditToJSON(username, password1, firstName, lastName, email,
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

$(document).on('submit', '#passwordValidationForm', function(e) {
	e.preventDefault();

	passwordValidation();

});

function passwordDTOJson(password1) {
	return JSON.stringify({
		"password" : password1

	})
}

function createHotelCustomerServiceToJSON(name, price) {
	return JSON.stringify({
		"name" : name,
		"price" : price,
	})
}

function createRoomFastReservationToJSON(roomId, startDate, endDate, discount,
		hcsIds) {
	return JSON.stringify({
		"roomId" : roomId,
		"startDate" : startDate,
		"endDate" : endDate,
		"discount" : discount,
		"hotelCustomerServices" : hcsIds,
	})
}
