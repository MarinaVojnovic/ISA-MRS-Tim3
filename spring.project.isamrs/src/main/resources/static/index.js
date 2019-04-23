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
			console.log('uslo u success');
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
			console.log('uslo u success');
			fillTableFlights(data, "tableAllFlight");

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
			console.log('uslo u success');
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
			console.log('uslo u success');
			fillTableCars(data, "tableAllCar");

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function fillTableAirlines(data) {
	console.log('fill table airlines called');
	var response = data;
	$("#tableAllAirline").find("tr").remove();
	var tabela = document.getElementById("tableAllAirline");
	console.log(tabela);

	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
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
		cell4.innerHTML = "X";
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

function fillTableHotels(data) {
	console.log('fill table hotels called');
	var response = data;
	$("#tableAllHotel").find("tr").remove();
	var tabela = document.getElementById("tableAllHotel");
	console.log(tabela);

	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
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
		cell4.innerHTML = "X";
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

function fillTableRentacars(data) {
	console.log('fill table rentacars called');
	var response = data;
	$("#tableAllRentacar").find("tr").remove();
	var tabela = document.getElementById("tableAllRentacar");
	console.log(tabela);

	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
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
		cell4.innerHTML = "X";
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

function fillTableFlights(data, table) {
	console.log('fill table flights called');
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);
	console.log(tabela);
	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
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
		cell1.innerHTML = response[counter].airline.name;
		cell2.innerHTML = response[counter].number;
		cell3.innerHTML = response[counter].startAirline.name;
		cell4.innerHTML = response[counter].finalAirline.name;
		cell5.innerHTML = response[counter].cost;
		cell6.innerHTML = response[counter].dateOfStart;
		cell7.innerHTML = response[counter].dateOfEnd;
		cell8.innerHTML = response[counter].numOfStops;
		cell9.innerHTML = response[counter].lengthOfFlight;

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
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Airline it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of flight</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Start airline</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Final airline</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Cost</p>';
	cell6.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Start date</p>';
	cell7.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">End date</p>';
	cell8.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of stops</p>';
	cell9.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Length of flights</p>';
}

function fillTableRooms(data, table) {
	console.log('fill table rooms called');
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);
	console.log(tabela);

	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].hotel.name;
		cell2.innerHTML = response[counter].roomNumber;
		cell3.innerHTML = response[counter].price;
		cell4.innerHTML = response[counter].numberPeople;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Hotel it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of room</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of people for room</p>';
}

function fillTableCars(data, table) {
	console.log('fill table cars called');
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);
	console.log(tabela);

	var index = 0;
	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].rentacar.name;
		cell2.innerHTML = response[counter].name;
		cell3.innerHTML = response[counter].price;
		cell4.innerHTML = response[counter].year;

	}
	var row = tabela.insertRow(0);
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">#</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Rentacar it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Name</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Car year</p>';
}

$(document).on(
		"click",
		".showAirlineProfile",
		function(e) {
			e.preventDefault();
			// alert(this.id);
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
						// fillTableFlights(data.flights, "tableAllFlight2");
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
						ymaps.ready(init(data.address,"airlineLocation"));
						$("#airlineProfileName").html(data.name);
						$("#airlineProfileAddress").html(data.address);
						$("#airlineProfilePromoDescription").html(
								data.promotionalDescription);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

$(document).on(
		"click",
		".showHotelProfile",
		function(e) {
			e.preventDefault();
			// alert(this.id);
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
						ymaps.ready(init(data.address,"hotelLocation"));
						$("#hotelProfileName").html(data.name);
						$("#hotelProfileAddress").html(data.address);
						$("#hotelProfilePromoDescription").html(
								data.promotionalDescription);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

$(document).on(
		"click",
		".showRentacarProfile",
		function(e) {
			e.preventDefault();
			// alert(this.id);
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
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		});

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