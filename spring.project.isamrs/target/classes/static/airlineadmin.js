var urlRoot1 = "http://localhost:8080/createFlight";
var urlRoot2 = "http://localhost:8080/findAirline";
var urlRoot3 = "http://localhost:8080/saveChangesAirline";

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

					if (flightNumberRegister == ""
							|| startDestinationRegister == ""
							|| finalDestinationRegister == ""
							|| costOfFlight == "" || dateOfFlight == "") {
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
											costOfFlight, dateOfFlight),
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

function createFlightToJSON(flightNumberRegister, startDestinationRegister,
		finalDestinationRegister, costOfFlight, dateOfFlight) {
	return JSON.stringify({
		"flightNumberRegister" : flightNumberRegister,
		"startDestinationRegister" : startDestinationRegister,
		"finalDestinationRegister" : finalDestinationRegister,
		"costOfFlight" : costOfFlight,
		"dateOfFlight" : dateOfFlight,
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