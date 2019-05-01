var urlRoot1 = "http://localhost:8080/getAllAirlines";
var urlRoot2 = "http://localhost:8080/getAllHotels";
var urlRoot3 = "http://localhost:8080/getAllRentacars";
var urlRoot4 = "http://localhost:8080/getAllFlights";
var urlRoot5 = "http://localhost:8080/getAllRooms";
var urlRoot6 = "http://localhost:8080/getAllCars";
var urlRoot7 = "http://localhost:8080/findConcreteAirline";
var urlRoot8 = "http://localhost:8080/findConcreteHotel";
var urlRoot9 = "http://localhost:8080/findConcreteRentacar";
var urlRoot10 = "http://localhost:8080/findConcreteFlights";
var urlRoot11 = "http://localhost:8080/findConcreteRooms";
var urlRoot12 = "http://localhost:8080/findConcreteCars";
var urlRoot13 = "http://localhost:8080/getConcreteDestinations";
var urlRoot14 = "http://localhost:8080/getConcreteHotelCustomerServices";
var urlRoot15 = "http://localhost:8080/getConcreteBranches";
var urlRoot16 = "http://localhost:8080/searchFlightUnregistered";
var urlRoot17 = "http://localhost:8080/searchRoomUnregistered";
var urlRoot18 = "http://localhost:8080/searchCarUnregistered";

var TOKEN_KEY = 'jwtToken';

function register() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	window.location.href = "register.html";
}

function login() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	window.location.href = "login.html";
}

function showHome() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#home").show();
}

function showAirlines() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu1").show();
	$.ajax({
		type : 'GET',
		url : urlRoot1,
		dataType : "json",
		success : function(data) {
			fillTableAirlines(data, "tableAllAirline");

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showHotels() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu2").show();
	$.ajax({
		type : 'GET',
		url : urlRoot2,
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

function showRentacars() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu3").show();
	$.ajax({
		type : 'GET',
		url : urlRoot3,
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

function showFlights() {
	$('#flightSearchStartDestination').find('option').remove()
	$('#flightSearchEndDestination').find('option').remove()
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu4").show();
	$.ajax({
		type : 'GET',
		url : urlRoot4,
		dataType : "json",
		success : function(data) {
			fillTableFlights(data, "tableAllFlight");

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	$.ajax({
		type : 'GET',
		url : urlRoot1,
		dataType : "json",
		success : function(data) {
			if (data) {
				$('#flightSearchStartDestination').append($('<option>', {
					value : 0,
					text : "None"
				}));
				$.each(data, function(i, item) {
					$('#flightSearchStartDestination').append($('<option>', {
						value : item.id,
						text : item.id + " " + item.name
					}));
				});
				$('#flightSearchEndDestination').append($('<option>', {
					value : 0,
					text : "None"
				}));
				$.each(data, function(i, item) {
					$('#flightSearchEndDestination').append($('<option>', {
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

function showRooms() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu5").show();
	$.ajax({
		type : 'GET',
		url : urlRoot5,
		dataType : "json",
		success : function(data) {
			fillTableRooms(data, "tableAllRoom");

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showCars() {
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$("#hotelProfile").hide();
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu6").show();
	$.ajax({
		type : 'GET',
		url : urlRoot6,
		dataType : "json",
		success : function(data) {
			fillTableCars(data, "tableAllCar");

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function fillTableAirlines(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	var index = 0;
	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0)
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);
		var cell5 = row.insertCell(5);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = response[counter].score;
		cell5.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showAirlineProfile\" value=\"Show profile\">Show profile</button>';

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Description</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%"></p>';

}

function fillTableDestinations(data, table) {
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
		cell1.innerHTML = response[counter].worksWith.name;
		cell2.innerHTML = response[counter].worksWith.address;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Address</p>';

}

function fillTableHotels(data) {
	var response = data;
	$("#tableAllHotel").find("tr").remove();
	var tabela = document.getElementById("tableAllHotel");

	var index = 0;
	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0)
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);
		var cell5 = row.insertCell(5);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = response[counter].score;
		cell5.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showHotelProfile\" value=\"Show profile\">Show profile</button>';

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Description</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%"></p>';

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

function fillTableRentacars(data) {
	var response = data;
	$("#tableAllRentacar").find("tr").remove();
	var tabela = document.getElementById("tableAllRentacar");

	var index = 0;
	for ( var counter in response) {
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0)
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);
		var cell5 = row.insertCell(5);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = response[counter].score;
		cell5.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showRentacarProfile\" value=\"Show profile\">Show profile</button>';

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Address</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%"> Description</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%></p>';

}

function fillTableBranches(data, table) {
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
		cell1.innerHTML = response[counter].city;
		cell2.innerHTML = response[counter].address;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200% font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">City</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Address</p>';

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
		var cell8 = row.insertCell(8);
		var cell9 = row.insertCell(9);
		var cell10 = row.insertCell(10);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].airline.name;
		cell2.innerHTML = response[counter].number;
		cell3.innerHTML = response[counter].startAirline.name;
		cell4.innerHTML = response[counter].finalAirline.name;
		cell5.innerHTML = response[counter].cost;
		cell6.innerHTML = response[counter].dateOfStart;
		cell7.innerHTML = response[counter].dateOfEnd;
		cell8.innerHTML = response[counter].numOfStops;
		cell9.innerHTML = response[counter].lengthOfFlight;
		cell10.innerHTML = response[counter].score;

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
	var cell9 = row.insertCell(9);
	var cell10 = row.insertCell(10);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Airline it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of flight</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Start airline</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Final airline</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Cost</p>';
	cell6.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Start date</p>';
	cell7.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">End date</p>';
	cell8.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of stops</p>';
	cell9.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Length of flights</p>';
	cell10.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
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
		cell5.innerHTML = response[counter].score;

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

function fillTableCars(data, table) {
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
		var cell8 = row.insertCell(8);
		var cell9 = row.insertCell(9);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].rentacar.name;
		cell2.innerHTML = response[counter].name;
		cell3.innerHTML = response[counter].price;
		cell4.innerHTML = response[counter].year;
		cell5.innerHTML = response[counter].seats;
		cell6.innerHTML = response[counter].carType;
		cell7.innerHTML = response[counter].brand;
		cell8.innerHTML = response[counter].model;
		cell9.innerHTML = response[counter].score;

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
	var cell9 = row.insertCell(9);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Rentacar it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Car year</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of seats</p>';
	cell6.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Car type</p';
	cell7.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Brand</p>';
	cell8.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Model</p>';
	cell9.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
}

$(document).on(
		"click",
		".showAirlineProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			$(".tab-pane").hide();
			$("#airlineProfile").show();
			var finalPath = urlRoot7 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRoot10 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath2,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableFlights(data, "tableAllFlight2");
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})
						ymaps.ready(init(data.address, "airlineLocation"));
						$("#airlineProfileName").html(data.name);
						$("#airlineProfileAddress").html(data.address);
						$("#airlineProfilePromoDescription").html(
								data.promotionalDescription);
						$("#airlineProfileScore").html(data.score);

						var finalPath3 = urlRoot13 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath3,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableDestinations(data,
											"tableDestinations")
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

$(document)
		.on(
				"click",
				".searchFlightButton",
				function(e) {
					e.preventDefault();
					var k = document
							.getElementById("flightSearchStartDestination");
					if (k.selectedIndex != -1) {
						var startDestination = k.options[k.selectedIndex].value;
					} else {
						var startDestination = "-1";
					}
					var k = document
							.getElementById("flightSearchEndDestination");
					if (k.selectedIndex != -1) {
						var endDestination = k.options[k.selectedIndex].value;
					} else {
						var endDestination = "-1";
					}
					var startDate = document
							.getElementById("flightSearchStartDate").value;
					if (startDate == "") {
						startDate = "0000-00-00"
					}
					var endDate = document
							.getElementById("flightSearchEndDate").value;
					if (endDate == "") {
						endDate = "0000-00-00"
					}
					var finalPath = urlRoot16 + "/" + startDate + "/" + endDate
							+ "/" + startDestination + "/" + endDestination;
					$.ajax({
						type : 'GET',
						url : finalPath,
						headers : createAuthorizationTokenHeader(TOKEN_KEY),
						dataType : "json",
						success : function(data) {
							fillTableFlights(data, "tableAllFlight");

						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert(jqXHR.status);
							alert(textStatus);
							alert(errorThrown);
						}

					})
				})

$(document).on(
		"click",
		".showHotelProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			$(".tab-pane").hide();
			$("#hotelProfile").show();
			var finalPath = urlRoot8 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRoot11 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath2,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableRooms(data, "tableAllRoom2");
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})
						ymaps.ready(init(data.address, "hotelLocation"));
						$("#hotelProfileName").html(data.name);
						$("#hotelProfileAddress").html(data.address);
						$("#hotelProfilePromoDescription").html(
								data.promotionalDescription);
						$("#hotelProfileScore").html(data.score);

						var finalPath3 = urlRoot14 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath3,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableHotelCustomerServices(data,
											"tableHotelCustomerServices")
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

$(document)
		.on(
				"click",
				".searchRoomButton",
				function(e) {
					e.preventDefault();
					var k = document.getElementById("roomSearchPeopleNumber");
					if (k.selectedIndex != -1) {
						var numberPeople = k.options[k.selectedIndex].value;
					} else {
						var numberPeople = "0";
					}
					var lowestPrice = document
							.getElementById("roomSearchLowestPrice").value;
					if (lowestPrice == "") {
						lowestPrice = -1;
					} else {
						if (lowestPrice < 0) {
							lowestPrice = 0;
						}
					}
					var highestPrice = document
							.getElementById("roomSearchHighestPrice").value;
					if (highestPrice == "") {
						highestPrice = -1;
					} else {
						if (highestPrice < 0) {
							highestPrice = 0;
						}
					}
					var finalPath = urlRoot17 + "/" + numberPeople + "/"
							+ lowestPrice + "/" + highestPrice;
					$.ajax({
						type : 'GET',
						url : finalPath,
						headers : createAuthorizationTokenHeader(TOKEN_KEY),
						dataType : "json",
						success : function(data) {
							fillTableRooms(data, "tableAllRoom");

						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert(jqXHR.status);
							alert(textStatus);
							alert(errorThrown);
						}

					})
				})

$(document).on(
		"click",
		".showRentacarProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			$(".tab-pane").hide();
			$("#rentacarProfile").show();
			var finalPath = urlRoot9 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRoot12 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath2,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableCars(data, "tableAllCar2");
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})
						ymaps.ready(init(data.address, "rentacarLocation"));
						$("#rentacarProfileName").html(data.name);
						$("#rentacarProfileAddress").html(data.address);
						$("#rentacarProfilePromoDescription").html(
								data.promotionalDescription);
						$("#rentacarProfileScore").html(data.score);

						var finalPath3 = urlRoot15 + "/" + ID;
						$.ajax({
							type : 'GET',
							url : finalPath3,
							dataType : "json",
							success : function(data) {
								if (data) {
									fillTableBranches(data,
											"tableBranchOffices")
								}
							},
							error : function(jqXHR, textStatus, errorThrown) {
								alert(jqXHR.status);
								alert(textStatus);
								alert(errorThrown);
							}

						})
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

$(document)
		.on(
				"click",
				".searchCarButton",
				function(e) {
					e.preventDefault();
					var brand = document.getElementById("carSearchBrand").value;
					if (brand == "") {
						brand = "0";
					}
					var lowestPrice = document
							.getElementById("carSearchLowestPrice").value;
					if (lowestPrice == "") {
						lowestPrice = -1;
					} else {
						if (lowestPrice < 0) {
							lowestPrice = 0;
						}
					}
					var highestPrice = document
							.getElementById("carSearchHighestPrice").value;
					if (highestPrice == "") {
						highestPrice = -1;
					} else {
						if (highestPrice < 0) {
							highestPrice = 0;
						}
					}
					var finalPath = urlRoot18 + "/" + brand + "/" + lowestPrice
							+ "/" + highestPrice;
					$.ajax({
						type : 'GET',
						url : finalPath,
						headers : createAuthorizationTokenHeader(TOKEN_KEY),
						dataType : "json",
						success : function(data) {
							fillTableCars(data, "tableAllCar");

						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert(jqXHR.status);
							alert(textStatus);
							alert(errorThrown);
						}

					})
				})

$(document).on("click", "#airlineBack", function(e) {
	e.preventDefault();
	$(".lokacija").html("");
	$("#airlineProfile").hide();
	$(".tab-pane").hide();
	$("#menu1").show();
});

$(document).on("click", "#hotelBack", function(e) {
	e.preventDefault();
	$(".lokacija").html("");
	$("#hotelProfile").hide();
	$(".tab-pane").hide();
	$("#menu2").show();
});

$(document).on("click", "#rentacarBack", function(e) {
	e.preventDefault();
	$(".lokacija").html("");
	$("#rentacarProfile").hide();
	$(".tab-pane").hide();
	$("#menu3").show();
});