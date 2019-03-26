var urlRoot1 = "http://localhost:8080/createAirline";
var urlRoot2 = "http://localhost:8080/createHotel";
var urlRoot3 = "http://localhost:8080/createRentacar";

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