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
var urlRootFindDest="http://localhost:8080/findDest";
var urlRootFindRentacarDest= "http://localhost:8080/findRentacar";
var urlRootFindSuitCars =  "http://localhost:8080/findSuitCars";
var urlRootGetAllCars="http://localhost:8080/getAllCars";
var urlRootCreateCarRes="http://localhost:8080/createCarReservation";
var urlRootCancelCarRes = "http://localhost:8080/cancelCarReservation";
var urlRootGetMyResCars = "http://localhost:8080/api/getMyResCars";
var urlRootFindRentacarFromRes="http://localhost:8080/findRentacarFromRes";
var urlRootFindCarFromRes="http://localhost:8080/findCarFromRes";
var urlRootGradeRentacar="http://localhost:8080/gradeRentacar";
var urlRootGradeCar="http://localhost:8080/gradeCar"
var urlRootFindSuitCarsFast="http://localhost:8080/findSuitCarsFast";
var urlRootCreateCarResFast="http://localhost:8080/createCarReservationFast";
var TOKEN_KEY = 'jwtToken';

getLogged();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function takeCarFast(id){
	console.log('take car fast called');
	
	
	var mainStartDate = document.getElementById("mainStartDate").value;
	var mainEndDate = document.getElementById("mainEndDate").value;
	var id = document.getElementById("carId").value;
	
	var startDate=document.getElementById("pickupDateFast").value;
	var endDate=document.getElementById("endDateFast").value;
	
	var dozvola=1;
	
	if (startDate=="" ||endDate==""){
		alert('None of the fields is allowed to be empty!');
	}else{
		if (new Date(startDate) < new Date(mainStartDate)){
			alert('start date must be after your holiday starts');
			dozvola=0;
		}
		
		if (mainEndDate!=""){
			if (new Date(startDate) > new Date(mainEndDate) || new Date(endDate)> new Date(mainEndDate)){
				alert('start date must be after your holiday starts');
				dozvola=0;
			}
		}
		
		console.log('Main start date: '+mainStartDate);
		console.log('Main end date: '+mainEndDate);
		console.log('start date: '+startDate);
		console.log('end date: '+endDate);
			
		if (dozvola==1){
			$.ajax({
				type : 'POST',
				url : urlRootCreateCarResFast+"/"+id+"/"+startDate+"/"+endDate,
				contentType : 'application/json',
				dataType : "json",
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				success : function(data) {
					alert("Successful fast reservation of a car, congratulations!");
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);

				}
			})
		}
	}
	
	
	
	
}
function showCarsForFastRes(){
	console.log('show cars for fast res called');
	var rentacarId = document.getElementById("rentacarId").value;
	var startDate = document.getElementById("mainStartDate3").value;
	var endDate = document.getElementById("mainEndDate3").value;
	
	console.log('RENTACAAAAR ID'+rentacarId);
	$.ajax({
		type : 'GET',
		url : urlRootFindSuitCarsFast+"/"+rentacarId+"/"+startDate+"/"+endDate,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			alert('success');
			var response = data;
			$(".messageSuitableCarsFast").children().remove();

			
		
			$("#tableOfCarsOnFastRes").find("tr").remove();
			var	tabela = document.getElementById("tableOfCarsOnFastRes");
			
			console.log(tabela);
			var broj = 0;
			for ( var counter in response) {
				broj++;
				console.log('counter: ' + counter);
				var row = tabela.insertRow(counter);
				console.log(row);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell2.id = "name";
				var cell3 = row.insertCell(2);
				cell3.id = "price";
				var cell4 = row.insertCell(3);
				cell3.id = "year";
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				cell5.id="carType";
				var cell7 = row.insertCell(6);
				cell7.id="brand";
				var cell8 = row.insertCell(7);
				cell8.id="model";
				var cell9 = row.insertCell(8);
				cell9.id="totalPrice";
				cell6.id="seats";
				
				var cell10 = row.insertCell(9);
				cell10.id="grade";
				var cell11 = row.insertCell(10);
				var cell12 = row.insertCell(11);
				var cell13 = row.insertCell(12);
				var cell14 = row.insertCell(13);
				

				cell1.innerHTML = response[counter].id;
				console.log(cell1.innerHTML);
				console.log('ID PRE PODESVANAJA ' + cell1.id);
				cell2.innerHTML = response[counter].name;
				console.log(cell2.innerHTML);
				cell3.innerHTML = response[counter].price;
				cell4.innerHTML = response[counter].year;
				
					cell14.innerHTML = '<button style="background: #ff1a75; color: white"id=\"'
							+ response[counter].id
							+ '\" class=\"takeCarButtonFast\" class="btn btn-primary">Reserve</button>';
				
				console.log(response[counter].carType);
				cell5.innerHTML = response[counter].carType;
				cell7.innerHTML = response[counter].brand;
				cell8.innerHTML = response[counter].model;
				cell6.innerHTML = response[counter].seats;
				cell10.innerHTML = response[counter].score/response[counter].number;
				cell9.innerHTML = response[counter].price*20;
				cell12.innerHTML = new Date(response[counter].fastResStartDate);
				cell13.innerHTML = new Date(response[counter].fastResEndDate);
				cell11.innerHTML = response[counter].fastResPrice;

			}

			if (broj != 0) {
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
				var cell10 = row.insertCell(9);
				var cell11 = row.insertCell(10);
				var cell12 = row.insertCell(11);
				var cell13 = row.insertCell(12);
				var cell14 = row.insertCell(13);

				cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
				cell2.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Name</p>';
				;
				cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Price</p>';
				;
				cell4.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Year</p>';
				cell5.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Car type</p>';
				cell7.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Brand</p>';
				cell8.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Model</p>';
				cell6.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Seats</p>';
				cell10.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Grade</p>';
				cell9.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Total price</p>';
				cell12.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Start date</p>';
				cell13.innerHTML = '<p style= " font-weight: 200%; font-size:150%">End date</p>';
				cell11.innerHTML = '<p style= " font-weight: 200%; font-size:150%">New  price</p>';
				;
			} else {
				$(".messageSuitableCarsFast").append('<h3>No cars found to satisfy your criteria.</p>');
			}
			

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}
function saveGradedRentacar(){
	console.log('save graded rentacar called');
	var rentacarId = document.getElementById("rentacarId").value;
	var carId = document.getElementById("carId").value
	var rentacarGrade = document.getElementById("rentacarGrade").value;
	var carGrade=document.getElementById("carGrade").value;
	
	console.log(rentacarId);
	console.log(carId);
	console.log(rentacarGrade);
	console.log(carGrade);
	
	if (rentacarGrade!=""){
		console.log('rentacar grade nije 0');
		if (rentacarGrade < 1 || rentacarGrade > 5){
			alert('Grade must be between 1 and 5');
		}else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeRentacar + "/" + rentacarId+"/"+rentacarGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType: "json",
				success : function(data) {
					alert('rentacar successfully graded');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		}
	}
	
	if (carGrade!=""){
		console.log('car grade nije 0');
		if (carGrade < 1 || carGrade > 5){
			alert('Grade must be between 1 and 5');
		}else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeCar + "/" + carId+"/"+carGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType: "json",
				success : function(data) {
					alert('car successfully graded');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status);
					alert(textStatus);
					alert(errorThrown);
				}

			})
		}
	}
	
	
	
}
function fillGradeRentacarForm(id){
	console.log('fill grade rentacar form');
	
	var rentacarName;
	var carName;
	$
	.ajax({
		type : 'GET',
		url : urlRootFindRentacarFromRes+"/"+id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			
			console.log(data.name);
			rentacarName=data.name;
			$("#rentacarGradeName").append('<h2>Rentacar '+rentacarName+'</h2>');
			document.getElementById("rentacarId").value=data.id;
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	
	$
	.ajax({
		type : 'GET',
		url : urlRootFindCarFromRes+"/"+id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			console.log(data.name);
			carName=data.name;
			$("#carGradeName").append('<h2> Car '+carName+'</h2>');
			
			document.getElementById("carId").value=data.id;
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	
	
	
}

function cancelCarReservation(id){
	console.log('cancel car resrvation called');
	console.log(id);
	
	
	$.ajax({
		type : 'DELETE',
		url : urlRootCancelCarRes+"/"+id,
		dataType: "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			
				alert('Reservation successfully deleted.');
				console.log('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');
			
			
			showMyReservationsCars();

		},
		error : function(XMLHttpRequest) {
			alert("Error while deleting car");
		}

	})

}
function showMyReservationsCars(){
	console.log('show my reservations cars called');
	$
	.ajax({
		type : 'GET',
		url : urlRootGetMyResCars,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			$("#tableOfRentacarsRes").find("tr").remove();
			var list = data == null ? []
					: (data instanceof Array ? data : [ data ]);
			if (list.length > 0) {
				
				var tabela = document.getElementById("tableOfRentacarsRes");
				var count = 1
				for ( var res in list) {
					console.log('counter: ' + res);
					var row = tabela.insertRow(res);
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					var cell5 = row.insertCell(4);
					var cell6 = row.insertCell(5);
					var cell7 = row.insertCell(6);
					
				
					
					cell1.innerHTML = list[res].id;
					cell2.innerHTML = new Date(list[res].startDate);
					cell3.innerHTML = new Date(list[res].endDate);
					cell4.innerHTML = list[res].numOfPass;
					cell5.innerHTML = list[res].car.name;
					cell6.innerHTML =  list[res].rentacarRes.name;
					
					if (new Date(list[res].startDate) >= new Date()){
						cell7.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
							+ list[res].id
							+ '\" class=\"cancelCarResButton\" class="btn btn-primary">Cancel reservation</button>';
					}else if (new Date(list[res].endDate)< new Date()){
						cell7.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
							+ list[res].id
							+ '\" class=\"gradeCarResButton\" class="btn btn-primary">Grade service</button>';
					}else {
						cell7.innerHTML="Cannot cancel";
					}
					
					
					
					count++;

				}
				var row = tabela.insertRow(0);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				var cell7 = row.insertCell(6);
				cell1.innerHTML = "Id";
				cell2.innerHTML = "Start date";
				cell3.innerHTML = "End date";
				cell4.innerHTML = "Num of pass";
				cell5.innerHTML = "Car name";
				cell6.innerHTML = "Rentacar name";
			} else {
				
				$("#tableOfRentacarsRes").append("<h3>No rent-a-car reservations.</h3>")
			}
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
		cell5.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseAirline\" class="btn btn-primary">Choose</button>';
		cell4.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	cell1.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

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
		cell5.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseHotel\" class="btn btn-primary">Choose</button>';
		cell4.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	cell1.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

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
		cell6.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseRentacar\" class="btn btn-primary">Choose</button>';
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);

	cell1.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell4.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Average Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

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
													+ '\" class=\"chooseFlight\" class="btn btn-primary">Choose flight</button>';
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
function  createCarReservationCriteriums(startDate,endDate,startCity,endCity,carType,passengers,fromPrice,toPrice) {
	return JSON.stringify({
		"startDate" : startDate,
		"endDate" : endDate,
		"startCity" : startCity,
		"endCity" : endCity,
		"carType" : carType,
		"passengers" : passengers,
		"startPrice": fromPrice,
		"endPrice": toPrice
	})
}
function searchForCars(rentacarId){
	console.log('search for cars called');
	console.log('MAIN START DATE');
	console.log(document.getElementById("mainStartDate3").value);
	console.log('MAIN END DATE');
	console.log(document.getElementById("mainEndDate3").value);
	var mainStartDate=document.getElementById("mainStartDate3").value;
	var mainEndDate=document.getElementById("mainEndDate3").value
	
	var startDate = document.getElementById("pickupDateCar").value;
	var endDate = document.getElementById("endDateCar").value;
	var rentacarId=document.getElementById("rentacarId").value
	
	var dozvola=1;
	if (new Date(startDate)< new Date(mainStartDate)){
		alert("Pickup date must be after (or same day) the first day of reservation of flight");
		dozvola=0;
	}
	
	if (new Date(endDate) < new Date(mainStartDate)){
		alert("End date cannot be before the first day of holiday");
		dozvola=0;
	}
	if (new Date(endDate) < new Date(mainStartDate)){
		alert("End date cannot be before the first day of holiday");
		dozvola=0;
	}
	if (new Date(endDate) < new Date(mainStartDate)){
		alert("End date cannot be before the first day of holiday");
		dozvola=0;
	}
	
	if (new Date(endDate) < new Date(mainStartDate)){
		alert("End date cannot be before the first day of holiday");
		dozvola=0;
	}
	
	if (mainEndDate!=""){
		if (new Date(startDate)> new Date(mainEndDate)){
			alert("You cannot rent a car after the holiday ends!");
			dozvola=0;
		}
		
		if (new Date(endDate) > new Date(mainEndDate)){
			alert("You cannot rent a car after the holiday ends!");
			dozvola=0;
		}
		
	}
	
	if (fromPrice <0 || toPrice<0){
		dozvola=0;
		alert('Prices must be positive');
	}
	if (dozvola==1){
		
	
	var endCity = document.getElementById("endCity").value;
	var startCity = document.getElementById("pickupCity").value;
	var carType = document.getElementById("carType").value;
	var passengers = document.getElementById("passengers").value;
	if (startDate=="" || endDate=="" || startCity=="" || endCity=="" || carType=="" || passengers==""){
		alert('None of obligational fileds is allowed to be empty');
	}
	var fromPrice = document.getElementById("fromPrice").value;
	var toPrice = document.getElementById("toPrice").value;
	if (fromPrice==""){
		fromPrice=-1;
		
	}
	if (toPrice==""){
		toPrice=-1;
	}
	
	console.log('Start date: '+startDate);
	console.log('End date: '+endDate);
	console.log('Start city: '+startCity);
	console.log('End city: '+endCity);
	console.log('Car type: '+carType);
	console.log('Passengers:'+ passengers);
	console.log('From price: '+fromPrice);
	console.log('End price: '+toPrice);
	
	$.ajax({
		type : 'GET',
		url : urlRootFindSuitCars+"/"+rentacarId+"/"+startDate+"/"+endDate+"/"+startCity+"/"+endCity+"/"+carType+"/"+passengers+"/"+fromPrice+"/"+toPrice,
		dataType : "json",
		
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {

			var response = data;
			$(".messageSuitableCars").children().remove();

			
		
			$("#showSuitableCars").find("tr").remove();
			var	tabela = document.getElementById("showSuitableCars");
			
			console.log(tabela);
			var broj = 0;
			for ( var counter in response) {
				broj++;
				console.log('counter: ' + counter);
				var row = tabela.insertRow(counter);
				console.log(row);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell2.id = "name";
				var cell3 = row.insertCell(2);
				cell3.id = "price";
				var cell4 = row.insertCell(3);
				cell3.id = "year";
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				cell5.id="carType";
				var cell7 = row.insertCell(6);
				cell7.id="brand";
				var cell8 = row.insertCell(7);
				cell8.id="model";
				var cell9 = row.insertCell(8);
				cell9.id="totalPrice";
				cell6.id="seats";
				
				var cell10 = row.insertCell(9);
				cell10.id="grade";
				var cell11 = row.insertCell(10);
				
				

				cell1.innerHTML = response[counter].id;
				console.log(cell1.innerHTML);
				console.log('ID PRE PODESVANAJA ' + cell1.id);
				cell2.innerHTML = response[counter].name;
				console.log(cell2.innerHTML);
				cell3.innerHTML = response[counter].price;
				cell4.innerHTML = response[counter].year;
				
					cell11.innerHTML = '<button style="background: #ff1a75; color: white"id=\"'
							+ response[counter].id
							+ '\" class=\"takeCarButton\" class="btn btn-primary">Reserve</button>';
				
				console.log(response[counter].carType);
				cell5.innerHTML = response[counter].carType;
				cell7.innerHTML = response[counter].brand;
				cell8.innerHTML = response[counter].model;
				cell6.innerHTML = response[counter].seats;
				cell10.innerHTML = response[counter].score/response[counter].number;
				cell9.innerHTML = response[counter].price*20;

			}

			if (broj != 0) {
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
				var cell10 = row.insertCell(9);
				var cell11 = row.insertCell(10);

				cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
				cell2.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Name</p>';
				;
				cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Price</p>';
				;
				cell4.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Year</p>';
				cell5.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Car type</p>';
				cell7.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Brand</p>';
				cell8.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Model</p>';
				cell6.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Seats</p>';
				cell10.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Grade</p>';
				cell9.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Total price</p>';
				;
			} else {
				$(".messageSuitableCars").append('<h3>No cars found to satisfy your criteria.</p>');
			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
	
	
	
	
	
	
	
	
	
	
	//Uzmes getCars od chosen rentacar!!!
	//Prolazis kroz sve njih i prvo gledas da li je car type zadovoljavajuci, da li je broj putnika zadovoljavajuci, da li je cenovni rang ako postoji zadvooljavajuci
	//Prolazis kroz branch office i gledas jel odgovara start i end dest
	//Na kraju proveravas cenu ako postoji
	}
}

function takeCar(id, startDate,endDate,passengers){
	console.log('take car called');
	console.log("Id: "+id);
	console.log("Start date: "+startDate);
	console.log("End date: "+endDate);
	console.log("Passengers: "+passengers);
	
	
	$.ajax({
		type : 'POST',
		url : urlRootCreateCarRes+"/"+id+"/"+startDate+"/"+endDate+"/"+passengers,
		contentType : 'application/json',
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			alert("Successful reservation of a car, congratulations!");
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
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


$(document).on('submit', '#carReservationForm', function(e) {
	console.log('car reservation form submitted');
	e.preventDefault();

	searchForCars();

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
	var mainStartDate3=document.getElementById("mainStartDate2").value;
	var mainEndDate3=document.getElementById("mainEndDate2").value;
	openCity(e, 'rentacarReservation');
	var rentacarId=this.id;
	document.getElementById("mainStartDate3").value=mainStartDate3
	document.getElementById("mainEndDate3").value=mainEndDate3;
	document.getElementById("rentacarId").value=rentacarId;
	

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


$(document).on('click', '.takeCarButtonFast', function(e) {
	console.log('TAKE CAR BUTTON FAST CLICKED');
	// e.preventDefault();

	
	var mainStartDate = $("#mainStartDate3").val();
	var mainEndDate = $("#mainEndDate3").val();
	var carId=this.id;
	openCity(e, 'fastResDateDiv');
	document.getElementById("mainStartDate").value=mainStartDate;
	document.getElementById("mainEndDate").value=mainEndDate;
	document.getElementById("carId").value=carId;
});


$(document).on('click', '#buttonDateFast', function(e) {
	console.log('button date fast clicked');
	// e.preventDefault();

	takeCarFast();
});

$(document).on('click', '.takeCarButton', function(e) {
	console.log('take car button  clicked');
	// e.preventDefault();

	var startDate = document.getElementById("pickupDateCar").value;
	var endDate = document.getElementById("endDateCar").value;
	var passengers = document.getElementById("passengers").value;
	var _this = $(this);
	takeCar(this.id, startDate,endDate,passengers);

});

$(document).on('click', '.chooseFlight', function(e) {
	console.log('choose flight clicked');
	e.preventDefault();
	//datum
	//mesto
	var to = document.getElementById("to").value;
	var finalDestination = document.getElementById("finalDestination").value;
	var dateOfFlight = document.getElementById("dateOfFlight").value;
	var dateOfArrival = document.getElementById("dateOfReturn").value;
	openCity(e, 'flightReservation');
	document.getElementById("destinationId").value=finalDestination;
	document.getElementById("startDateId").value=dateOfFlight;
	document.getElementById("endDateId").value=dateOfArrival;
	console.log(document.getElementById("destinationId").value);
	console.log(document.getElementById("startDateId").value);
	console.log(document.getElementById("endDateId").value);
	

});

function showRentacarsDest(data, startDate, endDate) {
	var mainStartDate=startDate;
	var mainEndDate=endDate;
	document.getElementById("mainStartDate2").value=mainStartDate;
	document.getElementById("mainEndDate2").value=mainEndDate;
	console.log('show rentacars dest called');
	var response = data;
	$("#tableOfRentacarsDest").find("tr").remove();
	var tabela = document.getElementById("tableOfRentacarsDest");
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
		cell6.innerHTML = '<button style="background: #ff1a75; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseRentacar\" class="btn btn-primary">Choose</button>';
		cell5.innerHTML = '<a href=\"' + location
				+ '\" target=\"_blank\">Show location</a>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);

	cell1.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell4.innerHTML ='<p style= "font-weight: 200%; font-size:150%">Average Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

}


$(document).on('click', '#allReservationsButtton', function(e) {
	console.log('all reservations button clicked');
	e.preventDefault();
	showMyReservationsCars();;
});


$(document).on('click', '.gradeCarResButton', function(e) {
	console.log('grade car res button clicked');
	e.preventDefault();
	openCity(e, 'gradeRentacar');
	fillGradeRentacarForm(this.id);
	
});
$(document).on('click', '.cancelCarResButton', function(e) {
	console.log('cancel car reservation button clicked');
	e.preventDefault();
	cancelCarReservation(this.id);
});


$(document).on('click', '#submitRentacarGrade', function(e) {
	console.log('submit rentacar grade clicked');
	e.preventDefault();
	saveGradedRentacar();
});

$(document).on('click', '#fastReservationButton', function(e) {
	console.log('fast reservation button clicked');
	e.preventDefault();
	
	showCarsForFastRes();
});


$(document).on('click', '#offerRentacarsButton', function(e) {
	console.log('offer rentacars clicked');
	e.preventDefault();

	var destination = document.getElementById("destinationId").value;
	var startDate=document.getElementById("startDateId").value;
	var endDate = document.getElementById("endDateId").value;
	
	openCity(e, 'showRentacarsInDest');
	
	document.getElementById("endDateId").value=endDate;
	document.getElementById("startDateId").value=startDate;
	
	//sada cu da pravim ajax poziv u kojem cu da dobavim rentacars koji su na to dest
	//nadje ti destinaciju preko id, uzmes naziv
	//destinacija ti je u pricipu airline iz koje ces iscupati grad
	//i onda u principu pravis poziv sa tim destination
	var address="";
	$.ajax({
		type : 'GET',
		url : urlRootFindDest+"/"+destination,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('destination not found');
			} else {
				console.log('destination found');
				address=data.address;
				console.log(address);
				
				$.ajax({
					type : 'GET',
					url : urlRootFindRentacarDest+"/"+address,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data == null) {
							console.log('rentacar not found');
						} else {
							console.log('retnacar found');
							
							showRentacarsDest(data, startDate, endDate);
							
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
	console.log('DVAAAAAAAAAAAA');
	
	
	console.log(destination);
	console.log(startDate);
	console.log(endDate);
	

});

function createCarResDTO(id, startDate, endDate, passengers) {
	return JSON.stringify({
		"id": id,
		"startDate" : startDate,
		"endDate" : endDate,
		"passengers" : passengers
	
	})
}


