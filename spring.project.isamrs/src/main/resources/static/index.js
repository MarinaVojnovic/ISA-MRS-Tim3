var urlRoot1 = "http://localhost:8080/getAllAirlines";
var urlRoot2 = "http://localhost:8080/getAllHotels";
var urlRoot3 = "http://localhost:8080/getAllRentacars";

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

	cell0.innerHTML = "#";
	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "Average grade";
	cell5.innerHTML = "Location";

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

	cell0.innerHTML = "#";
	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "Average grade";
	cell5.innerHTML = "Location";

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

	cell0.innerHTML = "#";
	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "Average grade";
	cell5.innerHTML = "Location";

}