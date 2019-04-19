var urlRoot1 = "http://localhost:8080/createRoom";
var urlRoot2 = "http://localhost:8080/getRooms";
var urlRoot3 = "http://localhost:8080/deleteRoom";
var urlRoot4 = "http://localhost:8080/findRoom";
var urlRoot5 = "http://localhost:8080/saveEditedRoom";
var urlRoot6 = "http://localhost:8080/findHotel";
var urlRoot7 = "http://localhost:8080/saveChangesHotel";
var urlRoot8 = "http://localhost:8080/api/getLogged";
var urlRoot9 = "http://localhost:8080/api/editUser";

var TOKEN_KEY = 'jwtToken';

findHotel();
getLogged();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

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

			var hotelCustomerServices = document
					.getElementById("hotelCustomerServicesEdit").value;
			var rooms = document.getElementById("hotelRoomsEdit").value;

			var id = document.getElementById("hotelIdEdit").value;

			if (name == "" || address == "" || promotionalDescription == "") {
				alert('None of the fields is allowed to be empty!');
			} else {

				$.ajax({
					type : 'PUT',
					url : urlRoot7,
					data : hotelToJson(id, name, address,
							promotionalDescription, hotelCustomerServices,
							rooms),
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

function findHotel() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot6,
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("hotelIdEdit").value = data.id;
						document.getElementById("hotelNameEdit").value = data.name;
						document.getElementById("hotelAddressEdit").value = data.address;
						document
								.getElementById("hotelPromotionalDescriptionEdit").value = data.promotionalDescription;
						document.getElementById("hotelCustomerServicesEdit").value = data.hotelCustomerServices;
						document.getElementById("hotelRoomsEdit").value = data.rooms;
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
	} else {

		$.ajax({
			type : 'PUT',
			url : urlRoot5,
			data : roomToJson(id, roomNumber, price, numberPeople),
			dataType : "json",
			contentType : 'application/json',
			success : function(data) {
				alert("Room successfully edited, congratulations!");

			},
			error : function(XMLHttpRequest) {
				alert("Error while editing room! ");
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

					urlRoot6 = "http://localhost:8080/findRoom";
					showRooms("forEdit");
				},
				error : function(XMLHttpRequest) {
					alert("Error while eiting room");
				}

			})

}

function deleteRoom(roomId) {
	console.log('Delete room called');

	urlRoot3 = urlRoot3 + "/" + roomId;
	$.ajax({
		type : 'GET',
		url : urlRoot3,
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
		error : function(XMLHttpRequest) {
			alert("Error while deleting room");
		}

	})

}

function showRooms(type) {
	console.log('show rooms for deleting called');

	$
			.ajax({
				type : 'GET',
				url : urlRoot2,
				dataType : "json",
				success : function(data) {

					var response = data;
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

					for ( var counter in response) {
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
							cell5.innerHTML = '<button id=\"'
									+ response[counter].id
									+ '\" class=\"deleteRoomButton\" class="btn btn-primary">Delete</button>';
						} else if (type == "forEdit") {
							cell5.innerHTML = '<button id=\"'
									+ response[counter].id
									+ '\" class=\"editRoomButton\" class="btn btn-primary" >Edit</button>';
						}

					}
					var row = tabela.insertRow(0);
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					var cell5 = row.insertCell(4);

					cell1.innerHTML = "Id";
					cell2.innerHTML = "Room Number";
					cell3.innerHTML = "Price";
					cell4.innerHTML = "Number of people";

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
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRoot1,
									contentType : 'application/json',
									dataType : "json",
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
		"roomNumber" : roomNumber,
		"price" : price,
		"numberPeople" : numberPeople,
	})
}

function hotelToJson(id, name, address, promotionalDescription,
		hotelCustomerServices, rooms) {
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : address,
		"promotionalDescription" : promotionalDescription,
		"hotelCustomerServices" : hotelCustomerServices,
		"rooms" : rooms,

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
