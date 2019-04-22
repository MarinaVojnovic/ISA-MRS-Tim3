var urlRoot1 = "http://localhost:8080/getAllAirlines";
var urlRoot2 = "http://localhost:8080/getAllHotels";
var urlRoot3 = "http://localhost:8080/getAllRentacars";
var urlRoot4 = "http://localhost:8080/getAllFlights";
var urlRoot5 = "http://localhost:8080/getAllRooms";
var urlRoot6 = "http://localhost:8080/getAllCars";

function register() {
	window.location.href = "register.html";
}

function login() {
	window.location.href = "login.html";
}

function showAirlines() {
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
	$.ajax({
		type : 'GET',
		url : urlRoot4,
		dataType : "json",
		success : function(data) {
			console.log('uslo u success');
			fillTableFlights(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showRooms() {
	$.ajax({
		type : 'GET',
		url : urlRoot5,
		dataType : "json",
		success : function(data) {
			console.log('uslo u success');
			fillTableRooms(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showCars() {
	$.ajax({
		type : 'GET',
		url : urlRoot6,
		dataType : "json",
		success : function(data) {
			console.log('uslo u success');
			fillTableCars(data);

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

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = "X";
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

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
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Location</p>';

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

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = "X";
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

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
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Location</p>';

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

		var location = "https://maps.google.com/?q="
				+ response[counter].address;

		cell0.innerHTML = ++index;
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = "X";
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

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
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%>Location</p>';

}

function fillTableFlights(data) {
	console.log('fill table flights called');
	var response = data;
	$("#tableAllFlight").find("tr").remove();
	var tabela = document.getElementById("tableAllFlight");
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
		//cell1.innerHTML = response[counter].airline.name;
		cell1.innerHTML = "X";
		cell2.innerHTML = response[counter].number;
		//cell3.innerHTML = response[counter].startAirline.name;
		cell3.innerHTML = "X";
		//cell4.innerHTML = response[counter].finalAirline.name;
		cell4.innerHTML = "X";
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

function fillTableRooms(data) {
	console.log('fill table rooms called');
	var response = data;
	$("#tableAllRoom").find("tr").remove();
	var tabela = document.getElementById("tableAllRoom");
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

function fillTableCars(data) {
	console.log('fill table cars called');
	var response = data;
	$("#tableAllCar").find("tr").remove();
	var tabela = document.getElementById("tableAllCar");
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