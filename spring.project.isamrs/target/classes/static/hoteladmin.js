var urlRoot1 = "http://localhost:8080/createRoom";
var urlRoot2 = "http://localhost:8080/getRooms";
var urlRoot3 = "http://localhost:8080/deleteRoom";
var urlRoot4 = "http://localhost:8080/findRoom";
var urlRoot5 = "http://localhost:8080/saveEditedRoom";
var urlRoot6 = "http://localhost:8080/findHotel";
var urlRoot7 = "http://localhost:8080/saveChangesHotel";
var urlRoot8 = "http://localhost:8080/api/getLogged";
var urlRoot9 = "http://localhost:8080/api/editUser";
var urlGetFirstTime = "http://localhost:8080/api/isFirstTime";
var urlChangePassword = "http://localhost:8080/api/changePasswordFirstTime";

var TOKEN_KEY = 'jwtToken';

findHotel();
getLogged();

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
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function passwordValidation() {
	console.log('password validation called');
	var password1 = document.getElementById("newPasswordOne").value;
	var password2 = document.getElementById("newPasswordTwo").value;

	if (password1 == "" || password2 == "") {
		alert('You have to fill both fields');
	} else if (password1 != password2) {
		alert('Passwords must match!');
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
					alert("Successful changing password, congratulations!");
					$('.tab').show();
					$('#passwordValidation').hide();
				} else {
					alert("Error while changing password!");
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
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
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
							alert('Error while finding loged one!');
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
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (password1 != password2) {
						alert("Password must match, try again!");
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

$(document).on(
		"submit",
		"#form3",
		function(e) {
			e.preventDefault();
			var name = document.getElementById("hotelNameEdit").value;
			var address = document.getElementById("hotelAddressEdit").value;
			var promotionalDescription = document
					.getElementById("hotelPromotionalDescriptionEdit").value;

			if (name == "" || address == "" || promotionalDescription == "") {
				alert('None of the fields is allowed to be empty!');
			} else {

				$.ajax({
					type : 'PUT',
					url : urlRoot7,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					data : hotelToJson(name, address, promotionalDescription),
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
						document
								.getElementById("hotelPromotionalDescriptionEdit").value = data.promotionalDescription;
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
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
		alert('None of the fields is allowed to be empty!');
	} else if (roomNumber <= 0 || roomNumber >= 1000) {
		alert('Number of room must be in a range [1, 999] !');
	} else if (price <= 0) {
		alert('Price must be positive number!');
	} else if (numberPeople <= 0 || numberPeople >= 6) {
		alert('Number of people in room must be in a range [1, 5] !');
	} else {

		$.ajax({
			type : 'PUT',
			url : urlRoot5,
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
			data : roomToJson(id, roomNumber, price, numberPeople),
			dataType : "json",
			contentType : 'application/json',
			success : function(data) {
				alert("Room successfully edited, congratulations!");

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);

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
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

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
			if (data == null) {
				console.log('Room not found');
			} else {
				console.log('Room successfully deleted.');
			}

			urlRoot3 = "http://localhost:8080/deleteRoom";
			showRooms("forDelete");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
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
							cell5.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
									+ response[counter].id
									+ '\" class=\"deleteRoomButton\" class="btn btn-primary">Delete</button>';
						} else if (type == "forEdit") {
							cell5.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
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
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
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
						alert('At least one field is blank, please fill it up with proper information!');
					} else if (roomNumberRegister <= 0
							|| roomNumberRegister >= 1000) {
						alert('Number of room must be in a range [1, 999] !');
					} else if (roomPriceRegister <= 0) {
						alert('Price must be positive number!');
					} else if (roomPeopleNumberRegister <= 0
							|| roomPeopleNumberRegister >= 6) {
						alert('Number of people in room must be in a range [1, 5] !');
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
										alert("Successful adding room, congratulations!");
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

$(document).on('click', '.deleteRoomButton', function(e) {
	// e.preventDefault();
	var _this = $(this);

	console.log('room number' + '   ' + this.id);
	deleteRoom(this.id);
});

$(document).on('click', '.editRoomButton', function(e) {
	// e.preventDefault();
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

function hotelToJson(name, address, promotionalDescription) {
	return JSON.stringify({
		"hotelNameRegister" : name,
		"hotelAddressRegister" : address,
		"hotelPromotionalDescription" : promotionalDescription,

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
