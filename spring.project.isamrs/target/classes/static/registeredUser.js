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
var urlRootFindDest = "http://localhost:8080/findDest";
var urlRootFindRentacarDest = "http://localhost:8080/findRentacarByDestination";
var urlRootFindHotelDest = "http://localhost:8080/findHotelByDestination";
var urlRootFindSuitCars = "http://localhost:8080/findSuitCars";
var urlRootGetAllCars = "http://localhost:8080/getAllCars";
var urlRootCreateCarRes = "http://localhost:8080/createCarReservation";
var urlRootCancelCarRes = "http://localhost:8080/cancelCarReservation";
var urlRootCancelFlightRes = "http://localhost:8080/cancelFlightReservation";
var urlRootGetMyResCars = "http://localhost:8080/api/getMyResCars";
var urlRootFindRentacarFromRes = "http://localhost:8080/findRentacarFromRes";
var urlRootFindCarFromRes = "http://localhost:8080/findCarFromRes";
var urlRootGradeRentacar = "http://localhost:8080/gradeRentacar";
var urlRootGradeCar = "http://localhost:8080/gradeCar"
var urlRootFindSuitCarsFast = "http://localhost:8080/findSuitCarsFast";
var urlRootCreateCarResFast = "http://localhost:8080/createCarReservationFast";
var urlRoot14 = "http://localhost:8080/getFlight";
var urlRoot15 = "http://localhost:8080/getSeat";
var urlRoot16 = "http://localhost:8080/api/makeReservation";
var urlRootSendMail = "http://localhost:8080/sendEmail";
var urlRootSearchRoomToReserve = "http://localhost:8080/searchRoomToReserve";
var urlRootConcreteHcs = "http://localhost:8080/getConcreteHotelCustomerServices";
var urlRootReserveFastRoomReservation = "http://localhost:8080/createRoomReservationFast";
var urlRootGetConcreteRFR = "http://localhost:8080/getConcreteRoomFastReservations";
var urlRootReserveRegularRoom = "http://localhost:8080/createRoomReservationRegular";
var urlRootGetMyResFlights = "http://localhost:8080/api/getMyResFlights";

var urlRootProfile7 = "http://localhost:8080/findConcreteAirline";
var urlRootProfile8 = "http://localhost:8080/findConcreteHotel";
var urlRootProfile9 = "http://localhost:8080/findConcreteRentacar";
var urlRootProfile10 = "http://localhost:8080/findConcreteFlights";
var urlRootProfile11 = "http://localhost:8080/findConcreteRooms";
var urlRootProfile12 = "http://localhost:8080/findConcreteCars";
var urlRootProfile13 = "http://localhost:8080/getConcreteDestinations";
var urlRootProfile14 = "http://localhost:8080/getConcreteHotelCustomerServices";
var urlRootProfile15 = "http://localhost:8080/getConcreteBranches";

var urlRootGetMyResFlights = "http://localhost:8080/api/getMyResFlights";
var urlRootFindAirlineFromRes = "http://localhost:8080/findAirlineFromRes";
var urlRootFindFlightFromRes = "http://localhost:8080/findFlightFromRes";


var urlRootGradeFlight = "http://localhost:8080/gradeFlight";
var urlRootGradeAirline = "http://localhost:8080/gradeAirline";
var urlRootGetMyResHotels="http://localhost:8080/api/getMyResHotels";
var TOKEN_KEY = 'jwtToken';

var urlRootFindRoomFromRes="http://localhost:8080/findRoomFromRes";
var urlRootFindHotelFromRes="http://localhost:8080/findHotelFromRes";

var urlRootGradeHotel="http://localhost:8080/gradeHotel";
var urlRootCancelHotelRes="http://localhost:8080/cancelHotelReservation";
var finishReservationUrl="http://localhost:8080/api/finishReservation";
var fastReservationAirline="http://localhost:8080/api/fastReservationAirline";
var myReservationUrl="http://localhost:8080/myReservation";
var reserveForFriendUrl="http://localhost:8080/reserveForFriend";
var reserveFastFlight="http://localhost:8080/api/reserveFastFlight";

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

$(document).on('click', '#editProfileButton', function(e) {
	getLogged();
})

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	removeJwtToken(TOKEN_KEY);
	window.location.href = "index.html";
})

function cancelHotelReservation(id){
	console.log('cancel hotel reservation button clicked');
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'DELETE',
		url : urlRootCancelHotelRes + "/" + id,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {

			showMessage('Reservation successfully deleted.',"success");

			showMyReservationsHotels();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
	
}
function saveGradedHotel() {
	console.log('save graded hotel called');

	sessionStorage.removeItem("choosenSeats");
	var hotelId = document.getElementById("hotelId").value;
	console.log('id hotela'+hotelId);
	// var flightId = document.getElementById("flightId").value
	var hotelGrade = document.getElementById("hotelGrade").value;
	// var flightGrade = document.getElementById("flightGrade").value;
	var brojac=document.getElementById("brojac").value;
	
	var grades="";
	var rooms="";
	var roomsGrades="";
	while(brojac!=0){
		
		var grade="";
		grade=document.getElementById("roomNumber"+brojac).value;
		room=document.getElementById("roomNumber"+brojac).name;
		roomsGrades+=room+","+grade+"|";
		grades+=grade+",";
		rooms+=room+",";
		brojac--;
	}
	roomsGrades=roomsGrades.substring(0,roomsGrades.length-1);
	grades=grades.substring(0, grades.length-1);
	rooms=rooms.substring(0, rooms.length-1);
	console.log('GRADEEEEEEEEEEEEEES'+grades);
	console.log('ROMEEEEES'+rooms);
	console.log('ROOMES-GRADES '+roomsGrades);
	/*
	 * console.log(airlineId); console.log(flightId); console.log(airlineGrade);
	 * console.log(flightGrade);
	 */

	if (hotelGrade != "") {
		console.log('hotel grade nije 0');
		if (hotelGrade < 1 || hotelGrade > 5) {
			showMessage('Grade must be between 1 and 5',"warning");
		} else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeHotel + "/" + hotelId + "/"
						+ hotelGrade+"/"+roomsGrades,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage('hotel successfully graded',"success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		}
	}

}

function saveGradedAirline() {
	console.log('save graded airline called');

	sessionStorage.removeItem("choosenSeats");
	var airlineId = document.getElementById("airlineId").value;
	var flightId = document.getElementById("flightId").value
	var airlineGrade = document.getElementById("airlineGrade").value;
	var flightGrade = document.getElementById("flightGrade").value;

	console.log(airlineId);
	console.log(flightId);
	console.log(airlineGrade);
	console.log(flightGrade);

	if (airlineGrade != "") {
		console.log('airline grade nije 0');
		if (airlineGrade < 1 || airlineGrade > 5) {
			showMessage('Grade must be between 1 and 5',"warning");
		} else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeAirline + "/" + airlineId + "/"
						+ airlineGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage('airline successfully graded',"success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		}
	}

	if (flightGrade != "") {
		console.log('flight grade nije 0');
		if (flightGrade < 1 || flightGrade > 5) {
			showMessage('Grade must be between 1 and 5',"warning");
		} else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeFlight + "/" + flightId + "/" + flightGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage('flight successfully graded',"success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		}
	}

}

function fillGradeHotelForm(id, rooms){
	console.log('fill grade hotel form called');
	sessionStorage.removeItem("choosenSeats");
	var hotelName;
	var roomName;
	var brojac=0;
	
	$.ajax({
		type : 'GET',
		url : urlRootFindHotelFromRes + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {

			console.log(data.name);
			
			
			
			var numbers = rooms.split(",");
			var tabela = document.getElementById("gradeHotelTable");
			$("#gradeHotelTable").find("tr").remove();
			console.log('***************************');
			console.log('prvi broj' +numbers[0]);
			console.log('drugi broj '+numbers[1]);
			for (room in numbers){
				brojac++;
				
				var row = tabela.insertRow(room);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				
				
				cell1.innerHTML ='<h2>Room number:'+numbers[room] +'<h2>'
				cell2.innerHTML = '<input type=\"number\" name=\"'+ numbers[room]+'\" value=\"\" placeholder=\"Enter grade from 1 to 5\" id=\"roomNumber'+brojac+'\">';
				
				console.log(numbers[room]);
			
			}
			console.log('***************************');
			document.getElementById("hotelId").value=data.id;
			var row = tabela.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			
			
			cell1.innerHTML ='<h2>Hotel:'+data.name +'<h2>'
			cell2.innerHTML = '<input type=\"number\" name=\"\" value=\"\" placeholder=\"Enter grade from 1 to 5\" id=\"hotelGrade\">';
			
			document.getElementById("brojac").value=brojac;

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
	
}

function fillGradeFlightForm(id) {
	console.log('fill grade flight form called');
	sessionStorage.removeItem("choosenSeats");
	var tabela=document.getElementById("gradeAirlineTable");
	$("#gradeAirlineTable").find("tr").remove();
	var rentacarName;
	var carName;
	var row = tabela.insertRow(0);
	$.ajax({
		type : 'GET',
		url : urlRootFindAirlineFromRes + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {

			console.log(data.name);
			airlineName = data.name;
			/*
			 * $("#airlineGradeName").append( '<h2>Airline ' + airlineName + '</h2>');
			 */
			
			document.getElementById("airlineId").value = data.id;
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			cell1.innerHTML ='<h2>Airline: ' + airlineName + '</h2>';
			cell2.innerHTML ='<td><input type="number" name="" value="" placeholder="Enter grade from 1 to 5" id="airlineGrade" /></td>'
			

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
	var row2 = tabela.insertRow(1);
	$.ajax({
		type : 'GET',
		url : urlRootFindFlightFromRes + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			console.log(data.name);
			flightName = data.name;
			

			document.getElementById("flightId").value = data.id;
			var cell1 = row2.insertCell(0);
			var cell2 = row2.insertCell(1);
			cell1.innerHTML ='<h2>Flight: </h2>';
			cell2.innerHTML ='<td><input type="number" name="" value="" placeholder="Enter grade from 1 to 5" id="flightGrade" /></td>';

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
	

}
function cancelFlightReservation(id) {
	console.log('cancel flight reservation called');
	console.log(id);
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'DELETE',
		url : urlRootCancelFlightRes + "/" + id,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {

			showMessage('Reservation of flight successfully deleted.',"success");

			showMyReservationsFlights();
			showMyReservationsHotels();
			showMyReservationsCars();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
}

const getNestedObject = (nestedObj, pathArr) => {
    return pathArr.reduce((obj, key) =>
        (obj && obj[key] !== 'undefined') ? obj[key] : undefined, nestedObj);
}


function showMyReservationsHotels() {
	console.log('show my reservation hotels called');
	sessionStorage.removeItem("choosenSeats");
	$
			.ajax({
				type : 'GET',
				url : urlRootGetMyResHotels,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				contentType : 'application/json',
				success : function(data) {
					$("#tableOfHotelsRes").find("tr").remove();
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (list.length > 0) {

						var tabela = document
								.getElementById("tableOfHotelsRes");
						var count = 1
						for ( var res in list) {
							var oneDay = 24*60*60*1000;
							var days = Math.round(Math.abs((new Date(list[res].endDate).getTime() - new Date(list[res].startDate).getTime())/(oneDay)));
							
							console.log('counter: ' + res);
							var row = tabela.insertRow(res);
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
							var oldPrice=list[res].price;
							if (list[res].discount!=0){
								oldPrice=list[res].price*(list[res].discount+100)/100;
							}
							 
							cell1.innerHTML = list[res].id;
							cell2.innerHTML = new Date(list[res].startDate).toLocaleString();
							cell3.innerHTML = new Date(list[res].endDate).toLocaleString();
							cell4.innerHTML = Math.round(oldPrice);
							cell5.innerHTML = list[res].discount+"%";
							cell6.innerHTML = list[res].price;
							cell7.innerHTML = Math.round(days*list[res].price);
							cell8.innerHTML = '<p id=\"roomsResNum'+list[res].id+'\">'+list[res].roomNumbers+'</p>';
							if (list[res].hotelCustomerServices==null){
								cell9.innerHTML="";
							}else{
								cell9.innerHTML = list[res].hotelCustomerServices;
							}
							cell10.innerHTML = list[res].hotelName;
							cell11.innerHTML = list[res].numOfPass;
							
							
							
							console.log('roooom numbeeeeeeeeeers'+list[res].roomNumbers);
							/*
							 * var listOfRooms = data == null ? [] :
							 * (list[res].rooms instanceof Set ? list[res].rooms : [
							 * list[res].rooms ]); console.log('treba sada da
							 * udje u rooms' + listOfRooms.length); listOfRooms =
							 * Array.from(listOfRooms);
							 * console.log(listOfRooms.length); var soba =
							 * listOfRooms[0]; console.log(soba.id);
							 */
							/*
							 * for (var room in listOfRooms){ console.log('uslo
							 * '+room);
							 * 
							 * console.log('room number: '+listOfRooms[room]);
							 * rooms+=listOfRooms[room].id+", "; }
							 */
							
							
							
							var ms = new Date().getTime() + 2*86400000;
							var granica = new Date(ms);
							
							if (new Date(list[res].startDate) >= granica) {
								cell12.innerHTML = '<button  style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"cancelHotelResButton\" class="btn btn-primary">Cancel reservation</button>';
							} else if (new Date(list[res].endDate) < new Date()) {
								cell12.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"gradeHotelResButton\" class="btn btn-primary">Grade service</button>';
							} else {
								cell12.innerHTML = "Cannot cancel";
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
						var cell8 = row.insertCell(7);
						var cell9 = row.insertCell(8);
						var cell10= row.insertCell(9);
						var cell11 = row.insertCell(10);
						
						
						cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
						cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Start date</p>';
						cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">End date</p>';
						cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Regular price per day</p>';
						cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Discount</p>';
						cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Your price per day</p>';
						cell7.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Total price</p>';
						cell8.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Rooms</p>';
						cell9.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Additional services</p>';
						cell10.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Hotel</p>';
						cell11.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Number of people</p>';
					} else {

						$("#tableOfHotelsRes").html(
								"<h3>No hotel reservations.</h3>")
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");

				}
			})
}

function showMyReservationsFlights() {
	console.log('show my reservations flights called');

	$
			.ajax({
				type : 'GET',
				url : urlRootGetMyResFlights,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				contentType : 'application/json',
				success : function(data) {
					$("#tableOfFlightsRes").find("tr").remove();
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					if (list.length > 0) {

						var tabela = document
								.getElementById("tableOfFlightsRes");
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
							var cell8 = row.insertCell(7);
							var cell9=row.insertCell(8);
							var cell10=row.insertCell(9);
							var cell11=row.insertCell(10);

							cell1.innerHTML = list[res].id;
							cell2.innerHTML = list[res].passportNum;
							cell3.innerHTML = list[res].startAirport;
							cell4.innerHTML = list[res].endAirport;
							cell5.innerHTML = list[res].seats;
							cell6.innerHTML = list[res].startDate;
							cell7.innerHTML = list[res].endDate;
							
							cell8.innerHTML = Math.round(list[res].price*(100+list[res].discount)/100);
							cell9.innerHTML = list[res].discount;
							cell10.innerHTML = Math.round(list[res].price);
							
							
							

							
							 var ms = new Date().getTime() + 3*86400000; var
							 granica = new Date(ms);
							 
							if (new Date(list[res].startDate) >=granica) {
								cell11.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"cancelFlightResButton\" class="btn btn-primary">Cancel reservation</button>';
							} else if (new Date(
									list[res].endDate) < new Date()) {
								cell11.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"gradeFlightResButton\" class="btn btn-primary">Grade service</button>';
							} else {
								cell11.innerHTML = "Cannot cancel";
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
						var cell8 = row.insertCell(7);
						var cell9 = row.insertCell(8);
						var cell10 = row.insertCell(9);
						
						
						cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
						cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Passport</p>';
						cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Start airport</p>';
						cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">End airport</p>';
						cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Seat number</p>';
						cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Start date</p>';
						cell7.innerHTML = '<p style= "font-weight: 200%; font-size:150%">End date</p>';
						cell8.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Regular price</p>';
						cell9.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Discount</p>';
						cell10.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Your price</p>';
					} else {

						$("#tableOfFlightsRes").html(
								"<h3>No flight reservations.</h3>")
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");

				}
			})
}
function takeCarFast(id) {
	console.log('take car fast called');
	sessionStorage.removeItem("choosenSeats");

	var mainStartDate = document.getElementById("mainStartDate").value;
	var mainEndDate = document.getElementById("mainEndDate").value;
	var id = document.getElementById("carId").value;
	var startDateFast = document.getElementById("startDateFastt").value;
	var endDateFast=document.getElementById("endDateFastt").value;
	
	console.log("*****");
	console.log("start date fast:  "+startDateFast);
	console.log("end date fast:  "+startDateFast);
	console.log("****");
	
	var startDate = document.getElementById("pickupDateFast").value;
	var endDate = document.getElementById("endDateFast").value;
	var typeOfRes = document.getElementById("typeOfRes").value;
	
	console.log("start date mine: "+startDate);
	console.log("end date mine: "+endDate);
	console.log("***");

	var dozvola = 1;
	
	if (startDate == "" || endDate == "") {
		showMessage('None of the fields is allowed to be empty!',"warning");
	} else {
		if (typeOfRes=="1"){
			if (new Date(startDate) < new Date(mainStartDate)) {
				showMessage('start date must be after your holiday starts',"warning");
				dozvola = 0;
			}

			if (mainEndDate != "") {
				if (new Date(startDate) > new Date(mainEndDate)
						|| new Date(endDate) > new Date(mainEndDate)) {
					showMessage('start date must be after your holiday starts',"warning");
					dozvola = 0;
				}
			}
			if (new Date(startDate) > new Date(endDate)){
				showMessage('Start date cannot be after end date.', "warining");
				dozvola=0;
			}
			
			if (new Date(startDate) < new Date(startDateFast)){
				showMessage('Start date cannot be before period of fast reservation of this car starts', "warning");
				dozvola=0;
			}
			
			if (new Date(endDate) > new Date(endDateFast)){
				showMessage('End date cannot be after period of fast reservation of this car ends', "warning");
				dozvola=0;
			}
		}else {
			if (new Date(startDate) < new Date()){
				showMessage('Start date cannot be in past', "warning");
				dozvola=0;
			}
			if (new Date(endDate) < new Date()){
				showMessage('End date cannot be in the past', "warning");
				dozvola=0;
			}
			if (new Date(startDate) > new Date(endDate)){
				showMessage('Start date cannot be after end date.', "warining");
				dozvola=0;
			}
			
			if (new Date(startDate) < new Date(startDateFast)){
				showMessage('Start date cannot be before period of fast reservation of this car starts', "warning");
				dozvola=0;
			}
			
			if (new Date(endDate) > new Date(endDateFast)){
				showMessage('End date cannot be after period of fast reservation of this car ends', "warning");
				dozvola=0;
			}
		}
		

		console.log('Main start date: ' + mainStartDate);
		console.log('Main end date: ' + mainEndDate);
		console.log('start date: ' + startDate);
		console.log('end date: ' + endDate);
		console.log('type of res: '+ typeOfRes);

		if (dozvola == 1) {
			$
					.ajax({
						type : 'POST',
						url : urlRootCreateCarResFast + "/" + id + "/"
								+ startDate + "/" + endDate+"/"+typeOfRes,
						contentType : 'application/json',
						
						headers : createAuthorizationTokenHeader(TOKEN_KEY),
						success : function(data) {
							console.log("-------");
							console.log(data);
							console.log("-------");
							if (!data){
								showMessage("Sorry, car already reserved in specified period!","warning");
								document.getElementById("pickupDateFast").value="";
								document.getElementById("endDateFast").value="";
								
							}else {
								showMessage("Car successfully reserved!","success");
								document.getElementById("pickupDateFast").value="";
								document.getElementById("endDateFast").value="";
							}
							

						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert('hellloooo');
							showMessage(jqXHR.status,"error");
							showMessage(textStatus,"error");
							showMessage(errorThrown,"error");

						}
					})
		}
	}
	if (typeOfRes=="1"){
		
		$("#buttonsDiv").children().remove();
		$("#buttonsDiv")
		.append(
				'<br><br><button type="submit" style="background: #cc0033;align: center; color: white" id="offerHotelsButton" style="float: left;/">Reserve hotel</button>');
		$("#buttonsDiv")
		.append(
				'<br><button type="submit" style="background: #cc0033;align: center; color: white" id="finishReservation" style="float: left;/">Finish reservation </button>');
		
	}
	

}

function showCarsForFastRes() {
	sessionStorage.removeItem("choosenSeats");
	console.log('show cars for fast res called');
	var rentacarId = document.getElementById("rentacarId").value;
	var startDate = document.getElementById("mainStartDate3").value;
	var endDate = document.getElementById("mainEndDate3").value;
	console.log("start date: "+startDate);
	console.log("end date: "+endDate);
	if (endDate == "") {
		endDate = -1;
	}
	
	if (startDate== ""){
		console.log("uslo u pravljenje start date-a");
		startDate= -1;
	}
	console.log(startDate);
	console.log('RENTACAAAAR ID' + rentacarId);
	$
			.ajax({
				type : 'GET',
				url : urlRootFindSuitCarsFast + "/" + rentacarId + "/"
						+ startDate + "/" + endDate,
				dataType : "json",
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				success : function(data) {
					showMessage('success',"success");
					var response = data;
					$(".messageSuitableCarsFast").children().remove();

					$("#tableOfCarsOnFastRes").find("tr").remove();
					var tabela = document
							.getElementById("tableOfCarsOnFastRes");

					console.log(tabela);
					var broj = 0;
					for ( var counter in response) {
						broj++;
						console.log('counter: ' + counter);
						var row = tabela.insertRow(counter);
						console.log(row);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						
						var cell3 = row.insertCell(2);
						
						var cell4 = row.insertCell(3);
						
						var cell5 = row.insertCell(4);
						var cell6 = row.insertCell(5);
						
						var cell7 = row.insertCell(6);
						
						var cell8 = row.insertCell(7);
						
						var cell9 = row.insertCell(8);
						cell9.id = "totalPrice";
						

						var cell10 = row.insertCell(9);
						
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

						cell14.innerHTML = '<button style="background: #cc0033; color: white"id=\"'
								+ response[counter].id
								+ '\" class=\"takeCarButtonFast\" class="btn btn-primary">Reserve</button>';

						console.log(response[counter].carType);
						cell5.innerHTML = response[counter].carType;
						cell7.innerHTML = response[counter].brand;
						cell8.innerHTML = response[counter].model;
						cell6.innerHTML = response[counter].seats;
						var grade= Math.round(response[counter].score / response[counter].number);
						if (response[counter].number==0){
							grade="No grade";
						}
						cell10.innerHTML = grade;
						cell9.innerHTML = response[counter].price * 20;
						var startName="startDateFastt"+ response[counter].id;
						console.log('Start nameee: '+startName);
						cell12.innerHTML = new Date(
								response[counter].fastResStartDate).toLocaleString();
						cell12.id=startName;
						
						cell13.innerHTML = new Date(
								response[counter].fastResEndDate).toLocaleString();
						cell13.id="endDateFastt"+response[counter].id;
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
						cell9.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Regular price per day</p>';
						cell12.innerHTML = '<p style= " font-weight: 200%; font-size:150%">Start date</p>';
						cell13.innerHTML = '<p style= " font-weight: 200%; font-size:150%">End date</p>';
						cell11.innerHTML = '<p style= " font-weight: 200%; font-size:150%">New  price per day</p>';
						;
					} else {
						$(".messageSuitableCarsFast")
								.append(
										'<h3>No cars found to satisfy your criteria.</p>');
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
}
function saveGradedRentacar() {
	console.log('save graded rentacar called');
	sessionStorage.removeItem("choosenSeats");
	var rentacarId = document.getElementById("rentacarId").value;
	var carId = document.getElementById("carId").value
	var rentacarGrade = document.getElementById("rentacarGrade").value;
	var carGrade = document.getElementById("carGrade").value;

	console.log(rentacarId);
	console.log(carId);
	console.log(rentacarGrade);
	console.log(carGrade);

	if (rentacarGrade != "") {
		console.log('rentacar grade nije 0');
		if (rentacarGrade < 1 || rentacarGrade > 5) {
			showMessage('Grade must be between 1 and 5',"warning");
		} else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeRentacar + "/" + rentacarId + "/"
						+ rentacarGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage('rentacar successfully graded',"success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		}
	}

	if (carGrade != "") {
		console.log('car grade nije 0');
		if (carGrade < 1 || carGrade > 5) {
			showMessage('Grade must be between 1 and 5',"warning");
		} else {
			$.ajax({
				type : 'GET',
				url : urlRootGradeCar + "/" + carId + "/" + carGrade,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					showMessage('car successfully graded',"success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		}
	}

}
function fillGradeRentacarForm(id) {
	console.log('fill grade rentacar form');
	sessionStorage.removeItem("choosenSeats");
	var rentacarName;
	var carName;
	var tabela=document.getElementById("gradeRentacarTable");
	$("#gradeRentacarTable").find("tr").remove();
	var row = tabela.insertRow(0);
	$.ajax({
		type : 'GET',
		url : urlRootFindRentacarFromRes + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {

			console.log(data.name);
			rentacarName = data.name;
			$("#rentacarGradeName").append(
					'<h2>Rentacar ' + rentacarName + '</h2>');
			document.getElementById("rentacarId").value = data.id;
			
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			cell1.innerHTML ='<h2>Rentacar: ' + rentacarName + '</h2>';
			cell2.innerHTML ='<td><input type="number" name="" value="" placeholder="Enter grade from 1 to 5" id="rentacarGrade" /></td>'
			

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})

	var row2 = tabela.insertRow(1);
	$.ajax({
		type : 'GET',
		url : urlRootFindCarFromRes + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			console.log(data.name);
			carName = data.name;
			
			document.getElementById("carId").value = data.id;
			var cell1 = row2.insertCell(0);
			var cell2 = row2.insertCell(1);
			cell1.innerHTML ='<h2>Rentacar: ' + carName + '</h2>';
			cell2.innerHTML ='<td><input type="number" name="" value="" placeholder="Enter grade from 1 to 5" id="carGrade" /></td>'

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})

}

function cancelCarReservation(id) {
	console.log('cancel car resrvation called');
	console.log(id);

	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'DELETE',
		url : urlRootCancelCarRes + "/" + id,
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {

			showMessage('Reservation successfully deleted.',"success");

			showMyReservationsCars();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})

}
function showMyReservationsCars() {
	console.log('show my reservations cars called');
	sessionStorage.removeItem("choosenSeats");
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

						var tabela = document
								.getElementById("tableOfRentacarsRes");
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
							var cell8 = row.insertCell(7);
							var cell9 = row.insertCell(8);
							var cell10 = row.insertCell(9);
							var cell11 = row.insertCell(10);
							
							var oneDay = 24*60*60*1000;
							var days = Math.round(Math.abs((new Date(list[res].endDate).getTime() - new Date(list[res].startDate).getTime())/(oneDay)));
							console.log("days"+days);
							if (days==0){
								days=1;
							}
							
							cell1.innerHTML = list[res].id;
							cell2.innerHTML = new Date(list[res].startDate).toLocaleString();
							console.log('*******');
							console.log(list[res].startDate);
							cell3.innerHTML = new Date(list[res].endDate).toLocaleString();
							cell4.innerHTML = list[res].numOfPass;
							console.log(list[res].numOfPass);
							console.log('*******');
							cell5.innerHTML = list[res].car.name;
							cell6.innerHTML = list[res].rentacarRes.name;
							cell7.innerHTML=Math.round(list[res].price*(list[res].discount+100)/100);
							var discount=0;
							if (list[res].discount!=null){
								discount=list[res].discount;
							}
							cell8.innerHTML=discount+"%";
							cell9.innerHTML=list[res].price;
							cell10.innerHTML=Math.round(days*list[res].price);
							
							var ms = new Date().getTime() + 2*86400000;
							var granica = new Date(ms);
							console.log('PROVERA DATUUUUUMAAA'+ granica);
							
							if (new Date(list[res].startDate) >=granica) {
								cell11.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"cancelCarResButton\" class="btn btn-primary">Cancel reservation</button>';
							} else if (new Date(list[res].endDate) < new Date()) {
								cell11.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
										+ list[res].id
										+ '\" class=\"gradeCarResButton\" class="btn btn-primary">Grade service</button>';
							} else {
								cell11.innerHTML = "Cannot cancel";
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
						var cell8 = row.insertCell(7);
						var cell9 = row.insertCell(8);
						var cell10 = row.insertCell(9);
						
						
						cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Id</p>';
						cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Start date</p>';
						cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">End date</p>';
						cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Num of pass</p>';
						cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Car name</p>';
						cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Rentacar name</p>';
						cell7.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Regular per day</p>';
						cell8.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Discount</p>';
						cell9.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Your price per day</p>';
						cell10.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Total price</p>';
						
					} else {

						$("#tableOfRentacarsRes").html(
								"<h3>No rent-a-car reservations.</h3>")
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");

				}
			})
}

function fillTableAirlines(data) {
	console.log('fill table airlines called');
	sessionStorage.removeItem("choosenSeats");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	var response = data;
	$("#tableOfAirlines").find("tr").remove();
	var tabela = document.getElementById("tableOfAirlines");
	console.log(tabela);
	
	if (list.length==0){
		$("#tableOfAirlines").append("<tr><td><h2>No airlines to satisfy your criteria.</h2></td></tr>");
	}else{
		
	
	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].city;
		cell4.innerHTML = response[counter].promotionalDescription;
		cell5.innerHTML = response[counter].score;
		cell6.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showAirlineProfile\" value=\"Show profile\" style=\"background:#cc0033;color: white\">Show profile</button>';
		cell7.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseAirline\" class="btn btn-primary">Choose</button>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);

	cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">City</p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Profile</p>';
	cell7.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	}
}

function fillTableHotels(data) {
	sessionStorage.removeItem("choosenSeats");
	console.log('fill table hotels called');
	var response = data;
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$("#tableOfHotels").find("tr").remove();
	var tabela = document.getElementById("tableOfHotels");
	console.log(tabela);

	if (list.length==0){
		$("#tableOfHotels").append("<tr><td><h2>No hotels to satisfy your criteria.</h2></td></tr");
		
		
	}else{
		
	
	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].city;
		cell4.innerHTML = response[counter].promotionalDescription;
		cell5.innerHTML = response[counter].score;
		cell6.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showHotelProfile\" value=\"Show profile\" style=\"background:#cc0033;color: white\">Show profile</button>';
		cell7.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseHotel\" class="btn btn-primary">Choose</button>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);

	cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">City</p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Profile</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	}

}

function findAirlines() {
	console.log('Find airlines called!');
	sessionStorage.removeItem("choosenSeats");
	var field = document.getElementById("nameLocationAirline").value;
	console.log('Field: ' + field);
	if (field == "") {
		showMessage("Field is not allowed to be empty, fill it!","warning");
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
				showMessage(jqXHR.status,"error");
				showMessage(textStatus,"error");
				showMessage(errorThrown,"error");
			}

		})
	}
}

function findHotels() {
	console.log('Find hotels called!');
	sessionStorage.removeItem("choosenSeats");
	var field = document.getElementById("nameLocationHotel").value;
	console.log('Field: ' + field);
	if (field == "") {
		showMessage("Field is not allowed to be empty, fill it!","warning");
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
				showMessage(jqXHR.status,"error");
				showMessage(textStatus,"error");
				showMessage(errorThrown,"error");
			}

		})
	}
}

function showAirlines(criteria) {
	console.log('showing airlines');
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'GET',
		url : urlShowAirlines + "/" + criteria,
		dataType : "json",
		success : function(data) {
			fillTableAirlines(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
}

function showHotels(criteria) {
	console.log('showing hotels');
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'GET',
		url : urlShowHotels + "/" + criteria,
		dataType : "json",
		success : function(data) {

			fillTableHotels(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
}

function showRentacars(criteria) {
	console.log('showing rentacars');
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'GET',
		url : urlShowRentacars + "/" + criteria,
		dataType : "json",
		success : function(data) {
			fillTableRentacars(data);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
}

function fillTableRentacars(data) {
	console.log("FILL TABLE RENTACARS");
	console.log(data)
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	var response = data;
	sessionStorage.removeItem("choosenSeats");
	$("#tableOfRentacars").find("tr").remove();
	var tabela = document.getElementById("tableOfRentacars");
	console.log(tabela);
	
	if (list.length==0){
		console.log('response je []');
		$("#tableOfRentacars").append("<tr><td><h2>No rentacars to satisfy your criteria.</h2></td></tr>")
	}
	else{
		
	
	for ( var counter in response) {
		console.log('counter: ' + counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);

		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].city;
		cell4.innerHTML = response[counter].promotionalDescription;
		cell5.innerHTML = response[counter].score;
		cell6.innerHTML = '<button id=\"'
				+ response[counter].id
				+ '\" class=\" showRentacarProfile\" value=\"Show profile\" style=\"background:#cc0033;color: white\">Show profile</button>';
		cell7.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseRentacarSingle\" class="btn btn-primary">Choose</button>';

	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);

	cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">City</p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Profile</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	
	}

}

function findRentacars() {
	console.log('find rent a car called');
	sessionStorage.removeItem("choosenSeats");
	var field = document.getElementById("nameLocationRentACar").value;
	console.log('Field: ' + field);
	if (field == "") {
		showMessage("Field is not allowed to be empty, fill it!","warning");
	} else {

		$.ajax({
			type : 'GET',
			url : urlFindRentacars + "/" + field,
			dataType : "json",
			success : function(data) {
				console.log('uslo u success');
				fillTableRentacars(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				showMessage(jqXHR.status,"error");
				showMessage(textStatus,"error");
				showMessage(errorThrown,"error");
			}

		})
	}
}

function findFriends() {
	sessionStorage.removeItem("choosenSeats");
	var name = document.getElementById("friendRequestName").value;
	var surname = document.getElementById("friendRequestSurname").value;
	$("#foundFriends").empty();
	if (name == "" && surname == "") {
		showMessage("Please enter name and/or surname","warning");
	} else {
		if (surname == "") {
			surname = "no_surname";
		}
		if(name==""){
			name="no_name";
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
										showMessage(jqXHR.status,"error");
										showMessage(textStatus,"error");
										showMessage(errorThrown,"error");
									}
								})

					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status,"error");
						showMessage(textStatus,"error");
						showMessage(errorThrown,"error");
					}

				})

	}
}

$(document).on('click', '#addFriend', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var id = $(this).attr("name").split(" ")[0];
	var name = $(this).attr("name");
	console.log("User id" + id);
	$.ajax({
		type : 'POST',
		url : urlRoot7 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			showMessage("Successful sending friend request, congratulations!","success");
			$("button[name='" + name + "']").attr('disabled', 'disabled');
			$("button[name='" + name + "']").text('Request sent');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
})

$(document).on('click', '#searchFriends', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	console.log("Kliknuo");
	findFriends();
})

$(document).on('click', '.tablinks', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$("#foundFlights").empty();
})

function searchFlights() {

}
function searchFilter() {
	sessionStorage.removeItem("choosenSeats");
	$.ajax({
		type : 'GET',
		url : urlRoot3,
		contentType : 'application/json',
		success : function(data) {
			var list = data == null ? [] : (data instanceof Array ? data
					: [ data ]);
			if (data == null) {

			} else {
				$("#startDestination").empty();
				$("#finalDestination").empty();
				var list = data == null ? [] : (data instanceof Array ? data
						: [ data ]);
				if (list.length > 0) {

					var i = 0
					$.each(list, function(index, destination) {
						var option = $('<option value="' + destination.id
								+ '">' + destination.city + '</option>')
						$("#startDestination").append(option);
					})
					$.each(list, function(index, destination) {
						var option = $('<option value="' + destination.id
								+ '">' + destination.city + '</option>')
						$("#finalDestination").append(option);
					})

				}

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})

}

$(document).on(
		'click',
		'.chooseAirline',
		function(e) {
			// e.preventDefault();
			var _this = $(this);
			sessionStorage.removeItem("choosenSeats");
			console.log('airline number' + '   ' + this.id);
			openCity(e, 'airlineReservation');
			$.ajax({
				type : 'GET',
				url : fastReservationAirline+"/"+this.id,
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				contentType : 'application/json',
				success : function(data) {
					if (data) {
						fillTableFlightFastReservations(data,
								"fastReservationAirline");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})

		});

function fillTableFlightFastReservations(data, table) {
	var response = data;
	$("#" + table).find("tr").remove();
	var tabela = document.getElementById(table);

	var indeks = 0;
	var indeks2=1;
	for ( var counter in response) {
		if (!response[counter].reserved) {
			var row = tabela.insertRow(indeks++);
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
			var cell10=row.insertCell(10);
			cell0.innerHTML="<p>"+indeks2+"</p>";
			cell1.innerHTML = response[counter].flightNumber;
			cell2.innerHTML = response[counter].startDestination;
			cell3.innerHTML = response[counter].finalDestination;
			cell4.innerHTML = response[counter].dateOfFlight;
			cell5.innerHTML = response[counter].dateOfArrival;
			cell6.innerHTML = response[counter].length;
			cell7.innerHTML = response[counter].price;
			cell8.innerHTML = response[counter].discout +" %";
			cell9.innerHTML = response[counter].total ;
			cell10.innerHTML = "<button class=\"reserveFastFlight\" name=\""
					+ response[counter].id + "\">Reserve</button>";
			indeks2++;
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
	var cell9=row.insertCell(9);
	var cell10=row.insertCell(10);
	cell0.innerHTML='<p "color:#002699;">#</p>';
	cell1.innerHTML = '<p style= "color:#002699;">Flight number</p>';
	cell2.innerHTML = '<p style= "color:#002699;">Start destination</p>';
	cell3.innerHTML = '<p style= "color:#002699;">Final destination</p>';
	cell4.innerHTML = '<p style= "color:#002699;">Date of flight</p>';
	cell5.innerHTML = '<p style= "color:#002699; ">Date of arrival</p>';
	cell6.innerHTML = '<p style= "color:#002699;">Length</p>';
	cell7.innerHTML = '<p style= "color:#002699;">Price</p>';
	cell8.innerHTML = '<p style= "color:#002699;">Discount</p>';
	cell9.innerHTML = '<p style= "color:#002699;">Total</p>';
	cell10.innerHTML = '<p style= "color:#002699;">    </p>';
}

$(document).on('click','.reserveFastFlight',function(e){
	e.preventDefault();
	var id=$(this).attr("name");
	$.ajax({
		type: 'POST',
		url:  reserveFastFlight+"/"+id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			showMessage(data.message, data.header)
			openCity(e, 'airlines');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

		
	})
})

$(document)
		.on(
				'click',
				'#searchFlightButton',
				function(e) {
					e.preventDefault();
					sessionStorage.removeItem("choosenSeats");
					$("#foundFlights").empty();
					$("#foundFlightsReturn").empty();
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
						showMessage("All fields must be filled in.","warning")
					}
					if (startDestination == finalDestination) {
						showMessage("Start and final destination cannot be the same","warning");
						i = 1;
					}
					if (from == "") {
						from = "noFrom";
					}
					if (to == "") {
						to = "noTo";
					}
					if (fromL == "") {
						fromL = "noFromL";
					}
					if (toL == "") {
						toL = "noToL";
					}
					if (name == "") {
						name = "noName";
					}
					if (dateOfArrival != "") {
						if (new Date(dateOfFlight) > new Date(dateOfArrival)) {
							i = 1;
							showMessage("Date of flight cannot be after date of return!","warning");
						}
						if (new Date() > new Date(dateOfFlight)
								|| new Date() > new Date(dateOfArrival)) {
							i = 1;
							showMessage("Dates cannot be in the past","warning");
						}
						if (new Date() > new Date(dateOfFlight)
								&& new Date() > new Date(dateOfArrival)) {
							i = 1;
							showMessage("Dates cannot be in the past","warning");
						}
					} else {
						if (new Date() > new Date(dateOfFlight)) {
							i = 1;
							showMessage("Dates cannot be in the past","warning");
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
										if (data == null) {

										} else {
											var list = data.flightsGo == null ? []
													: (data.flightsGo instanceof Array ? data.flightsGo
															: [ data.flightsGo ]);
											var list2 = data.flightReturn == null ? []
													: (data.flightReturn instanceof Array ? data.flightReturn
															: [ data.flightReturn ]);

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
													cell5.innerHTML = 
															list[flight].dateOfStart;
													cell6.innerHTML = 
															list[flight].dateOfEnd;
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
											$("#foundFlightsReturn")
											.append("<h3>Found flights return: </h3>")
											if (list2.length > 0) {

												
												var tabela = document
														.getElementById("foundFlightsReturn");
												var count = 1;
												for ( var flight in list2) {
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
													cell1.innerHTML = list2[flight].number;
													cell2.innerHTML = list2[flight].startAirline.name;
													cell3.innerHTML = list2[flight].finalAirline.name;
													cell4.innerHTML = list2[flight].cost;
													cell5.innerHTML = list2[flight].dateOfStart;
													cell6.innerHTML = list2[flight].dateOfEnd;
													cell7.innerHTML = list2[flight].numOfStops;
													cell8.innerHTML = list2[flight].lengthOfFlight;
													cell9.innerHTML = '<button name=\"'
															+ list2[flight].id
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
												$("#foundFlightsReturn")
														.append(
																"<h3>No flights found </h3>");
											}
										}
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										showMessage(jqXHR.status,"error");
										showMessage(textStatus,"error");
										showMessage(errorThrown,"error");

									}
								})

					} else {

					}

				});
function getLogged() {
	var token = getJwtToken(TOKEN_KEY);
	sessionStorage.removeItem("choosenSeats");
	if (token) {
		$
				.ajax({
					type : 'GET',
					url : urlRoot5,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data == null) {
							showMessage('Error while finding loged one!',"warning");
						} else {
							document.getElementById('userUsernameEdit').value = data.username;
							document.getElementById('userFirstNameEdit').value = data.firstName;
							document.getElementById("userLastNameEdit").value = data.lastName;
							document.getElementById("userEmailEdit").value = data.email;
							document.getElementById("userPhoneNumberEdit").value = data.phoneNumber;
						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status,"error");
						showMessage(textStatus,"error");
						showMessage(errorThrown,"error");
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
					sessionStorage.removeItem("choosenSeats");
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
						showMessage('At least one field is blank, please fill it up with proper information!',"warning");
					} else if (password1 != password2) {
						showMessage("Password must match, try again!","warning");
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
										setJwtToken(TOKEN_KEY, data.accessToken);
										showMessage("Successful editing, congratulations!","success");

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										showMessage(jqXHR.status,"error");
										showMessage(textStatus,"error");
										showMessage(errorThrown,"error");

									}
								});
					}
				});

function seeFriendRequests() {
	sessionStorage.removeItem("choosenSeats");
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
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");

				}
			})
}

function showAllFriends() {
	sessionStorage.removeItem("choosenSeats");
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
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");

				}
			})

}

$(document).on('click', '#removeFriend', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'DELETE',
		url : urlRoot13 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			showMessage("Successfully removed friend from a friend list","success");
			document.getElementById("friendList").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})
})

$(document).on('click', '#listOfFri', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$("#friendList").empty();
	showAllFriends();
})

$(document).on('click', '#acceptFriend', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'PUT',
		url : urlRoot9 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			showMessage("Successfully accepted friend request.","success");
			document.getElementById("friendRequestTable").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})

})

$(document).on('click', '#rejectFriend', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var id = $(this).attr('name').split(" ")[0];
	var num = $(this).attr('name').split(" ")[1];
	$.ajax({
		type : 'DELETE',
		url : urlRoot10 + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			showMessage("Successfully rejected friend request.","success");
			document.getElementById("friendRequestTable").deleteRow(num);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})

})

$(document).on('click', '#friendR', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$("#friendRequestTable").empty();
	seeFriendRequests();
})

$(document).on('click', '#searchFilter', function(e) {
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
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
function createCarReservationCriteriums(startDate, endDate, startCity, endCity,
		carType, passengers, fromPrice, toPrice) {
	return JSON.stringify({
		"startDate" : startDate,
		"endDate" : endDate,
		"startCity" : startCity,
		"endCity" : endCity,
		"carType" : carType,
		"passengers" : passengers,
		"startPrice" : fromPrice,
		"endPrice" : toPrice
	})
}
function searchForCars(rentacarId) {
	console.log('search for cars called');
	sessionStorage.removeItem("choosenSeats");
	console.log('MAIN START DATE');
	console.log(document.getElementById("mainStartDate3").value);
	console.log('MAIN END DATE');
	console.log(document.getElementById("mainEndDate3").value);
	var mainStartDate = document.getElementById("mainStartDate3").value;
	var mainEndDate = document.getElementById("mainEndDate3").value

	var startDate = document.getElementById("pickupDateCar").value;
	var endDate = document.getElementById("endDateCar").value;
	var rentacarId = document.getElementById("rentacarId").value
	
	

	var dozvola = 1;
	if (new Date(startDate) < new Date(mainStartDate)) {
		showMessage("Pickup date must be after (or same day) the first day of reservation of flight","warning");
		dozvola = 0;
	}

	if (new Date(endDate) < new Date(mainStartDate)) {
		showMessage("End date cannot be before the first day of holiday","warning");
		dozvola = 0;
	}
	if (new Date(endDate) < new Date(mainStartDate)) {
		showMessage("End date cannot be before the first day of holiday","warning");
		dozvola = 0;
	}
	if (new Date(endDate) < new Date(mainStartDate)) {
		showMessage("End date cannot be before the first day of holiday","warning");
		dozvola = 0;
	}

	if (new Date(endDate) < new Date(mainStartDate)) {
		showMessage("End date cannot be before the first day of holiday","warning");
		dozvola = 0;
	}

	if (mainEndDate != "") {
		if (new Date(startDate) > new Date(mainEndDate)) {
			showMessage("You cannot rent a car after the holiday ends!","warning");
			dozvola = 0;
		}

		if (new Date(endDate) > new Date(mainEndDate)) {
			showMessage("You cannot rent a car after the holiday ends!","warning");
			dozvola = 0;
		}

	}

	if (fromPrice < 0 || toPrice < 0) {
		dozvola = 0;
		showMessage('Prices must be positive',"warning");
	}
	if (dozvola == 1) {
		var dozvola2 = 1;
		var endCity = document.getElementById("endCity").value;
		var startCity = document.getElementById("pickupCity").value;
		var carType = document.getElementById("carType").value;
		var passengers = document.getElementById("passengers").value;
		if (startDate == "" || endDate == "" || startCity == ""
				|| endCity == "" || carType == "" || passengers == "") {
			dozvola2=0;
			showMessage('None of obligational fileds is allowed to be empty',"warning");
		}
		var fromPrice = document.getElementById("fromPrice").value;
		var toPrice = document.getElementById("toPrice").value;
		if (fromPrice == "") {
			fromPrice = -1;

		}
		if (toPrice == "") {
			toPrice = -1;
		}
		var typeOfRes=document.getElementById("typeOfRes").value;

		console.log('Start date: ' + startDate);
		console.log('End date: ' + endDate);
		console.log('Start city: ' + startCity);
		console.log('End city: ' + endCity);
		console.log('Car type: ' + carType);
		console.log('Passengers:' + passengers);
		console.log('From price: ' + fromPrice);
		console.log('End price: ' + toPrice);
		console.log('Rentacar id: ' + rentacarId);
		console.log('type of res: '+typeOfRes);

		if (dozvola2==1){
		$
				.ajax({
					type : 'GET',
					url : urlRootFindSuitCars + "/" + rentacarId + "/"
							+ startDate + "/" + endDate + "/" + startCity + "/"
							+ endCity + "/" + carType + "/" + passengers + "/"
							+ fromPrice + "/" + toPrice,
					dataType : "json",

					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					success : function(data) {

						var response = data;
					

						$("#showSuitableCars").find("tr").remove();
						var tabela = document
								.getElementById("showSuitableCars");

						console.log(tabela);
						var broj = 0;
						for ( var counter in response) {
							broj++;
							console.log('counter: ' + counter);
							var row = tabela.insertRow(counter);
							console.log(row);
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

							cell1.innerHTML = response[counter].id;
							console.log(cell1.innerHTML);
							console.log('ID PRE PODESVANAJA ' + cell1.id);
							cell2.innerHTML = response[counter].name;
							console.log(cell2.innerHTML);
							cell3.innerHTML = response[counter].price;
							cell4.innerHTML = response[counter].year;

							cell11.innerHTML = '<button style="background: #cc0033; color: white"id=\"'
									+ response[counter].id
									+ '\" class=\"takeCarButton\" class="btn btn-primary">Reserve</button>';

							console.log(response[counter].carType);
							cell5.innerHTML = response[counter].carType;
							cell7.innerHTML = response[counter].brand;
							cell8.innerHTML = response[counter].model;
							cell6.innerHTML = response[counter].seats;
							var grade= response[counter].score / response[counter].number;
							if (response[counter].number==0){
								grade="No grade";
							}
							cell10.innerHTML = grade;
							cell9.innerHTML = response[counter].price * 20;

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
							/*
							 * if (typeOfRes=="1"){ $(".messageSuitableCars")
							 * .append( '<br><br><button type="submit"
							 * style="background: #cc0033; align: center; color:
							 * white" id="offerHotelsButton" style="float:
							 * left;/">Reserve hotel</button>');
							 * $(".messageSuitableCars") .append( '<br><button
							 * type="submit" style="background: #cc0033; align:
							 * center; color: white" id="finishReservation"
							 * style="float: left;/">Finish reservation
							 * </button>'); }
							 */
							
						} else {
							/*
							 * if (typeOfRes=="1") { $(".messageSuitableCars")
							 * .append( '<br><br><button type="submit"
							 * style="background: #cc0033;align: center; color:
							 * white" id="offerHotelsButton" style="float:
							 * left;/">Reserve hotel</button>');
							 * $(".messageSuitableCars") .append( '<br><button
							 * type="submit" style="background: #cc0033;align:
							 * center; color: white" id="finishReservation"
							 * style="float: left;/">Finish reservation
							 * </button>'); }
							 */
							$(".messageSuitableCarsTwo").children().remove();
							$(".messageSuitableCarsTwo")
									.append(
											'<h3>No cars found to satisfy your criteria.</p>');
							
						}

					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status,"error");
						showMessage(textStatus,"error");
						showMessage(errorThrown,"error");
					}

				})
		}

		
	}
}

function takeCar(id, startDate, endDate, passengers, typeOfRes) {
	sessionStorage.removeItem("choosenSeats");
	console.log('take car called');
	console.log("Id: " + id);
	console.log("Start date: " + startDate);
	console.log("End date: " + endDate);
	console.log("Passengers: " + passengers);
	console.log("Type of res: "+ typeOfRes);

	$.ajax({
		type : 'POST',
		url : urlRootCreateCarRes + "/" + id + "/" + startDate + "/" + endDate
				+ "/" + passengers+"/"+typeOfRes,
		contentType : 'application/json',
		dataType : "json",
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		success : function(data) {
			showMessage("Successful reservation of a car, congratulations!","success");
			/*
			 * if (typeOfRes=="1") { $(".messageSuitableCars") .append( '<br><br><button
			 * type="submit" style="background: #cc0033;align: center; color:
			 * white" id="offerHotelsButton" style="float: left;/">Reserve hotel</button>');
			 * $(".messageSuitableCars") .append( '<br><button type="submit"
			 * style="background: #cc0033;align: center; color: white"
			 * id="finishReservation" style="float: left;/">Finish reservation
			 * </button>'); }
			 */
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})
}

function rentacarReservation(id) {
	console.log('Rentacar reservation called.');
	sessionStorage.removeItem("choosenSeats");
}

$(document).on('click', '#hotelsButton', function(e) {
	console.log('hotels button clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameHotels").show();
	$(".sortByAddressHotels").show();
	document.getElementById("nameLocationHotel").value="";
	showHotels("bez");

});

$(document).on('submit', '#carReservationForm', function(e) {
	console.log('car reservation form submitted');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	searchForCars();

});

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

		cell0.innerHTML = "<input type=\"checkbox\" name=\"roomPick\" value=\""
				+ response[counter].id + "\">"
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

	cell0.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Pick one room</p>';
	cell1.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Hotel it belongs to</p>';
	cell2.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of room</p';
	cell3.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Price for one day</p>';
	cell4.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Number of people for room</p>';
	cell5.innerHTML = '<p style= "color:#002699; font-weight: 200%; font-size:150%">Grade</p>';
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

$(document)
		.on(
				'submit',
				'#firstPartRoomReservation',
				function(e) {
					e.preventDefault();
					sessionStorage.removeItem("choosenSeats");
					var hotelId = document
							.getElementById("roomReservationHotelId").value;
					var startDate = document
							.getElementById("roomReservationStartDate").value;
					var endDate = document
							.getElementById("roomReservationEndDate").value;
					var lowestPrice = document
							.getElementById("roomReservationLowestPrice").value;
					var highestPrice = document
							.getElementById("roomReservationHighestPrice").value;
					startDate = startDate.replace("T", " ");
					endDate = endDate.replace("T", " ");
					if (hotelId == "" || startDate == "" || endDate == ""
							|| lowestPrice == "" || highestPrice == "") {
						showMessage('At least one field is blank, please fill it up with proper information!',"warning");
					} else if (false) { // new Date() >= new Date(startDate)
						showMessage('You cannot put for reserving in past!',"warning");
					} else if (new Date(startDate) >= new Date(endDate)) {
						showMessage('End date cant be before or equal to start date!',"warning");
					} else if ((new Date(startDate)).getTime() + 3024000000 < (new Date(
							endDate)).getTime()) {
						showMessage('You cant reserve room for more than 5 weeks or 35 days!',"warning");
					} else if ((new Date(startDate)).getTime() + 7200000 > (new Date(
							endDate)).getTime()) {
						showMessage('You cant reserve room for less than 2 hours!',"warning");
					} else {
						var finalPathh = urlRootSearchRoomToReserve + "/"
								+ startDate + "/" + endDate + "/" + lowestPrice
								+ "/" + highestPrice + "/" + hotelId;
						$
								.ajax({
									type : 'GET',
									url : finalPathh,
									contentType : 'application/json',
									dataType : "json",
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									success : function(data) {
										if (data.message) {
											showMessage(data.message,"warning");
										} else if (data.length != 0) {
											fillTableRoomPick(data,
													"roomReservationRoomTable");
											var finalPathh2 = urlRootConcreteHcs
													+ "/" + hotelId;
											$
													.ajax({
														type : 'GET',
														url : finalPathh2,
														headers : createAuthorizationTokenHeader(TOKEN_KEY),
														dataType : "json",
														success : function(data) {
															if (data) {
																fillTableHotelCustomerServicesPick(
																		data,
																		"roomReservationHcsTable");
															}
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															showMessage(jqXHR.status,"error");
															showMessage(textStatus,"error");
															showMessage(errorThrown,"error");
														}

													})
											$("#pickRoomAndHcs").show();
											$("#roomReservationStartDate")
													.prop('disabled', true);
											$("#roomReservationEndDate").prop(
													'disabled', true);
											$("#roomReservationLowestPrice")
													.prop('disabled', true);
											$("#roomReservationHighestPrice")
													.prop('disabled', true);
										} else {
											$("#pickRoomAndHcs").hide();
											$("#roomReservationStartDate")
													.prop('disabled', false);
											$("#roomReservationEndDate").prop(
													'disabled', false);
											$("#roomReservationLowestPrice")
													.prop('disabled', false);
											$("#roomReservationHighestPrice")
													.prop('disabled', false);
											showMessage("There is no room that satisfies your dates and price in this hotel!","warning")
										}

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										showMessage(jqXHR.status,"error");
										showMessage(textStatus,"error");
										showMessage(errorThrown,"error");

									}
								})
					}
				});

$(document)
		.on(
				'submit',
				'#secondPartRoomReservation',
				function(e) {
					e.preventDefault();
					// sessionStorage.removeItem("choosenSeats");
					var roomIds = "";
					$("input:checkbox[name='roomPick']:checked").each(
							function() {
								roomIds += $(this).val() + " ";
							});
					if (roomIds != "") {
						roomIds.substring(0, roomIds.length - 1);
					}
					var hcsIds = "";
					$("input:checkbox[name='hcsPick']:checked").each(
							function() {
								hcsIds += $(this).val() + " ";
							});
					if (hcsIds != "") {
						hcsIds.substring(0, hcsIds.length - 1);
					}
					var discount = 0;
					var startDate = document
							.getElementById("roomReservationStartDate").value;
					var endDate = document
							.getElementById("roomReservationEndDate").value;
					startDate = startDate.replace("T", " ");
					endDate = endDate.replace("T", " ");
					var numberHotelDiscount = getHotelDiscountValue();
					var flightId = sessionStorage.getItem("flightReservationId");
					if (!flightId){
						flightId = -1;
					}
					if (roomIds == "") {
						showMessage('You must pick at least one room!',"warning");
					} else {
						$
								.ajax({
									type : 'POST',
									url : urlRootReserveRegularRoom,
									contentType : 'application/json',
									dataType : "json",
									data : roomReservationToJSON(startDate,
											endDate, discount, hcsIds, roomIds,
											numberHotelDiscount, flightId),
									headers : createAuthorizationTokenHeader(TOKEN_KEY),
									success : function(data) {
										showMessage(data.message,"success")
										openCity(e, 'hotels');
										$("#pickRoomAndHcs").hide();
										$("#roomReservationStartDate").prop(
												'disabled', false);
										$("#roomReservationEndDate").prop(
												'disabled', false);
										$("#roomReservationLowestPrice").prop(
												'disabled', false);
										$("#roomReservationHighestPrice").prop(
												'disabled', false);
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										showMessage(jqXHR.status,"error");
										showMessage(textStatus,"error");
										showMessage(errorThrown,"error");

									}
								})
					}
				});

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
			cell8.innerHTML = "<button class=\"reserveRFR\" id=\""
					+ response[counter].id + "\">Reserve</button>"
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

$(document).on('click', '.reserveRFR', function(e) {
	e.preventDefault();
	var ID = this.id;
	var flightId = sessionStorage.getItem("flightReservationId");
	if (!flightId){
		flightId = -1;
	}
	var finalPath = urlRootReserveFastRoomReservation + "/" + ID + "/" + flightId;
	$.ajax({
		type : 'POST',
		url : finalPath,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			showMessage(data.message, data.header)
			openCity(e, 'hotels');
			$("#pickRoomAndHcs").hide();
			$("#roomReservationStartDate").prop('disabled', false);
			$("#roomReservationEndDate").prop('disabled', false);
			$("#roomReservationLowestPrice").prop('disabled', false);
			$("#roomReservationHighestPrice").prop('disabled', false);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
});

$(document).on('click', '#BackReservationButton', function(e) {
	e.preventDefault();
	$("#pickRoomAndHcs").hide();
	$("#roomReservationStartDate").prop('disabled', false);
	$("#roomReservationEndDate").prop('disabled', false);
	$("#roomReservationLowestPrice").prop('disabled', false);
	$("#roomReservationHighestPrice").prop('disabled', false);
});

$(document).on('click', '#rentACarButton', function(e) {
	console.log('rent a car button clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameRentACars").show();
	$(".sortByAddressRentACars").show();
	document.getElementById("nameLocationRentACar").value="";
	showRentacars("bez");

});

$(document).on('click', '.airlinesButton', function(e) {
	console.log('airlines button clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameAirlines").show();
	$(".sortByAddressAirlines").show();
	document.getElementById("nameLocationAirline").value="";
	showAirlines("bez");

});
$(document).on('click', '.sortByNameHotels', function(e) {
	console.log('sort by name clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showHotels("sortByNameHotels");

});

$(document).on('click', '.sortByNameRentACars', function(e) {
	console.log('sort by name clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showRentacars("sortByNameRentACars");

});

$(document).on('click', '.sortByAddressHotels', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showHotels("sortByAddressHotels");

});

$(document).on('click', '.sortByAddressRentACars', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showRentacars("sortByAddressRentACars");

});

$(document).on('click', '.searchRentACarButton', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();

	findRentacars();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameRentACars").hide();
	$(".sortByAddressRentACars").hide();

});

$(document).on('click', '.searchAirlineButton', function(e) {
	console.log('search airline button clicked');
	// e.preventDefault();

	findAirlines();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameAirlines").hide();
	$(".sortByAddressAirlines").hide();

});

$(document).on('click', '.searchHotelButton', function(e) {
	console.log('search hotel button clicked');
	// e.preventDefault();

	findHotels();
	sessionStorage.removeItem("choosenSeats");
	$(".sortByNameHotels").hide();
	$(".sortByAddressHotels").hide();

});

$(document).on('click', '.chooseRentacar', function(e) {
	// e.preventDefault();
	var _this = $(this);
	sessionStorage.removeItem("choosenSeats");
	console.log('car number' + '   ' + this.id);
	var mainStartDate3 = document.getElementById("mainStartDate2").value;
	var mainEndDate3 = document.getElementById("mainEndDate2").value;
	openCity(e, 'rentacarReservation');
	$("#singleRentacar").children().remove();
	$(".messageSuitableCars").children().remove();
	$(".messageSuitableCarsTwo").children().remove();
	var rentacarId = this.id;
	document.getElementById("mainStartDate3").value = mainStartDate3
	document.getElementById("mainEndDate3").value = mainEndDate3;
	document.getElementById("rentacarId").value = rentacarId;
	document.getElementById("typeOfRes").value="1";
	
	document.getElementById("pickupDateCar").value="";
	document.getElementById("endDateCar").value="";
	document.getElementById("pickupCity").value="";
	document.getElementById("endCity").value="";
	document.getElementById("passengers").value="";
	document.getElementById("fromPrice").value="";
	document.getElementById("toPrice").value="";
	
	$("#singleRentacar").children().remove();
	$(".messageSuitableCars").children().remove();
	$(".messageSuitableCars").append('<button style=\"background: #cc0033; color: white\" type=\"submit\" class=\"btn btn-default\" id=\"offerRentacarsButton\">Back</button>');
		$(".messageSuitableCars")
		.append(
				'<br><br><button type="submit" style="background: #cc0033;align: center; color: white" id="offerHotelsButton" style="float: left;/">Reserve hotel</button>');
		$(".messageSuitableCars")
		.append(
				'<br><button type="submit" style="background: #cc0033;align: center; color: white" id="finishReservation" style="float: left;/">Finish reservation </button>');

});

$(document).on('click', '.chooseRentacarSingle', function(e) {
	console.log("choose rentacar single called")
	// e.preventDefault();
	var _this = $(this);
	sessionStorage.removeItem("choosenSeats");
	console.log('car number' + '   ' + this.id);
	var rentacarId = this.id;
	openCity(e, 'rentacarReservation');
	document.getElementById("rentacarId").value = rentacarId;
	document.getElementById("typeOfRes").value="0";
	document.getElementById("pickupDateCar").value="";
	document.getElementById("endDateCar").value="";
	document.getElementById("pickupCity").value="";
	document.getElementById("endCity").value="";
	document.getElementById("passengers").value="";
	document.getElementById("fromPrice").value="";
	document.getElementById("toPrice").value="";
	$("#singleRentacar").children().remove();
	$(".messageSuitableCars").children().remove();
	$("#singleRentacar").append('<br><button type="submit" style="background: #cc0033;align: center; color: white" id="backToSingleResList" style="float: left;/">Back</button>');

});

$(document).on('click', '#backToSingleResList', function(e) {
	document.getElementById("pickupDateCar").value="";
	document.getElementById("endDateCar").value="";
	document.getElementById("pickupCity").value="";
	document.getElementById("endCity").value="";
	document.getElementById("passengers").value="";
	document.getElementById("fromPrice").value="";
	document.getElementById("toPrice").value="";
	
	
	openCity(e, 'rentACars');
	$(".sortByNameRentACars").show();
	$(".sortByAddressRentACars").show();
	document.getElementById("nameLocationRentACar").value="";
	showRentacars("bez");
});

$(document).on(
		'click',
		'.chooseHotel',
		function(e) {
			// e.preventDefault();
			var _this = $(this);
			sessionStorage.removeItem("choosenSeats");
			console.log('car number' + '   ' + this.id);
			openCity(e, 'hotelReservation');
			var hotelId = this.id;
			document.getElementById("roomReservationHotelId").value = hotelId;
			var finalPath = urlRootGetConcreteRFR + "/" + hotelId;
			$.ajax({
				type : 'GET',
				url : finalPath, // sredi ptanju
				headers : createAuthorizationTokenHeader(TOKEN_KEY),
				dataType : "json",
				success : function(data) {
					if (data) {
						fillTableRoomFastReservations(data,
								"fastReservationRoomTable");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})

		});

$(document).on('click', '.sortByAddressAirlines', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showAirlines("sortByAddressAirlines");

});

$(document).on('click', '.sortByNameAirlines', function(e) {
	console.log('sort by address clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showAirlines("sortByNameAirlines");

});

$(document).on('click', '.takeCarButtonFast', function(e) {
	console.log('TAKE CAR BUTTON FAST CLICKED');
	// e.preventDefault();

	var mainStartDate = $("#mainStartDate3").val();
	var mainEndDate = $("#mainEndDate3").val();
	var carId = this.id;
	
	var ime = "startDateFastt"+carId;
	var startDateFastt= document.getElementById(ime).innerHTML;
	console.log('start date faaast: '+startDateFastt);
	var endDateFastt= document.getElementById("endDateFastt"+carId).innerHTML;
	sessionStorage.removeItem("choosenSeats");
	openCity(e, 'fastResDateDiv');
	document.getElementById("mainStartDate").value = mainStartDate;
	document.getElementById("mainEndDate").value = mainEndDate;
	document.getElementById("carId").value = carId;
	document.getElementById("startDateFastt").value = startDateFastt;
	document.getElementById("endDateFastt").value = endDateFastt;
});

$(document).on('click', '#buttonDateFast', function(e) {
	console.log('button date fast clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	takeCarFast();
});

$(document).on('click', '.takeCarButton', function(e) {
	console.log('take car button  clicked');
	// e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var startDate = document.getElementById("pickupDateCar").value;
	var endDate = document.getElementById("endDateCar").value;
	var passengers = document.getElementById("passengers").value;
	var _this = $(this);
	var typeOfRes = document.getElementById("typeOfRes").value;
	takeCar(this.id, startDate, endDate, passengers, typeOfRes);

});

$(document)
		.on(
				'click',
				'.chooseFlight',
				function(e) {
					console.log('choose flight clicked');
					e.preventDefault();
					sessionStorage.removeItem("choosenSeats");
					var id = $(this).attr('name');
					sessionStorage.setItem("flightId", JSON.stringify(id));
					$("#seatsFlight").empty();
					$("#seatsFlight").append(
							"<h3>Click on seats you want to reserve</h3>")
					$("#seatsFlight")
							.append(
									"<h3>If you want to cancel seat click again on it</h3>")
					$
							.ajax({
								type : 'GET',
								dataType : 'json',
								url : urlRoot14 + "/" + id,
								headers : createAuthorizationTokenHeader(TOKEN_KEY),
								success : function(data) {
									var table = $('<table align="center"></table>')
									var list = data.firstClass == null ? []
											: (data.firstClass instanceof Array ? data.firstClass
													: [ data.firstClass ]);
									var list2 = data.businessClass == null ? []
											: (data.businessClass instanceof Array ? data.businessClass
													: [ data.businessClass ]);
									var list3 = data.economyClass == null ? []
											: (data.economyClass instanceof Array ? data.economyClass
													: [ data.economyClass ]);
									var i = 1;
									var j = 1;

									var tr;
									for ( var fr in list) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}
										console.log(list[fr].taken);

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');

										}
										if (list[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else {
											tr
													.append('<td id="a"><div class="foo blue" id="'
															+ list[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									j = 1;
									for ( var fr in list2) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');
										}
										if (list2[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list2[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else {
											tr
													.append('<td id="a"><div class="foo green" id="'
															+ list2[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									j = 1;
									for ( var fr in list3) {
										if (j % 6 == 1) {
											if (j != 1) {
												table.append(tr);
											}
											tr = $('<tr></tr>');
											j = 1;
										}

										if (j % 4 == 0) {
											tr
													.append('<td><div class="foo seat"></div></td>');
										}
										if (list3[fr].taken == true) {
											tr
													.append('<td><div class="foo red" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else if (list3[fr].quickBooking == true) {
											tr
													.append('<td><div class="foo yellow" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										} else {
											tr
													.append('<td id="a"><div class="foo purple" id="'
															+ list3[fr].id
															+ '">'
															+ i
															+ '</div></td>');
										}
										i++;
										j++;

									}
									table.append(tr);
									$("#seatsFlight").append('<br><br>')
									$("#seatsFlight").append(table);
									$("#seatsFlight").append('<br><br>')
									$("#seatsFlight")
											.append(
													'<button id="reserveFlight" class="btn btn-primary">Reserve</button>');

								},
								error : function(jqXHR, textStatus, errorThrown) {
									showMessage(jqXHR.status,"error");
									showMessage(textStatus,"error");
									showMessage(errorThrown,"error");

								}
							})

					// datum
					// mesto
					var to = document.getElementById("to").value;
					var finalDestination = document
							.getElementById("finalDestination").value;
					var dateOfFlight = document.getElementById("dateOfFlight").value;
					var dateOfArrival = document.getElementById("dateOfReturn").value;
					openCity(e, 'flightReservation');
					document.getElementById("destinationId").value = finalDestination;
					document.getElementById("startDateId").value = dateOfFlight;
					document.getElementById("endDateId").value = dateOfArrival;
					console.log(document.getElementById("destinationId").value);
					console.log(document.getElementById("startDateId").value);
					console.log(document.getElementById("endDateId").value);

				});

$(document).on('click','#inviteMore',function(e){
	e.preventDefault();
	var friendName=document.getElementById("friendName").value;
	var lastName=document.getElementById("friendLastName").value;
	var passportNumber=document.getElementById("passportNumber").value;
	var cekiran;
	if(document.getElementById("checkboxFriend").checked){
		cekiran=true;
	}else{
		cekiran=false;
	}
	if(friendName=="" || lastName=="" || passportNumber==""){
		showMessage("All fields must be filled in.","warning")
	}else if(isNaN(passportNumber)){
		showMessage("Passport number must be number!","warning");
	}else{
		var choosenSeats=JSON.parse(sessionStorage["choosenSeats"]);
		var idSedista=choosenSeats.shift();
		sessionStorage.setItem("choosenSeats", JSON.stringify(choosenSeats));
		var friendNum=JSON.parse(sessionStorage["friendNumber"]);
		var upisi=friendNum+1;
		sessionStorage.setItem("friendNumber", JSON.stringify(upisi));
		var numSeats=JSON.parse(sessionStorage["numSeats"]);
		console.log("Broj sedista",numSeats);
		$.ajax({
			type: 'POST',
			url: reserveForFriendUrl+'/'+friendName+'/'+lastName+'/'+passportNumber+'/'+cekiran+'/'+idSedista,
			headers : createAuthorizationTokenHeader(TOKEN_KEY),
		    success: function(data){
		    	$("#inviteFriend").empty();
		    	if(data.email!="no"){
		    		sendEmail(data);
		    		
		    	}if(numSeats>=friendNum){
		    	
		    		openCity(e, 'inviteFriend');
		    		$("#inviteFriend").append("<h2 align='center'>Invite friend number "+friendNum+": <h2>");
		    		var table=$('<table align="center"></table>');
		    		var tr=$('<tr></tr>');
		    		tr.append('<td><p>  Name:  </p></td>');
		    		tr.append('<td><input type="text" align="center" id="friendName"></td>');
		    		table.append(tr);
		    		var tr1=$('<tr></tr>');
		    		tr1.append('<td><p>  Last name:  </p></td>');
		    		tr1.append('<td><input type="text" align="center" id="friendLastName"></td>');
		    		table.append(tr1);
		    		var tr2=$('<tr></tr>');
		    		tr2.append('<td><p>  Passport number:  </p></td>');
		    		tr2.append('<td><input type="text" align="center" id="passportNumber"></td>');
		    		table.append(tr2);
		    		var tr3=$('<tr></tr>');
		    		tr3.append('<td><p>  Is in friend list?:  </p></td>');
		    		tr3.append('<td><input type="checkbox" id="checkboxFriend" value="Friend"></td>');
		    		table.append(tr3);
		    		table.append('<button align="center" type="submit" id="inviteMore" class="btn btn-primary" style="background-color: #cc0033">Next</button>');
		    		$("#inviteFriend").append(table);
		    		$("#inviteFriend").append('<input type="checkbox" id="friendList">');
		    		
		    	}
		    	else{
		    		$("#inviteFriend").empty();
		    		$("#inviteFriend")
					.append(
							'<button type="submit" style="background: #cc0033; color: white" id="offerRentacarsButton" style="float: left;">Rentacars</button>');
		    		$("#inviteFriend")
					.append(
							'<br><button type="submit" style="background: #cc0033; color: white" id="offerHotelsButton" style="float: left;/">Hotels</button>');
		    		$("#inviteFriend")
					.append(
							'<br><button type="submit" style="background: #cc0033; color: white" id="finishReservation" style="float: left;/">Finish reservation</button>');
		    		
		    	}
		    },
			error : function(jqXHR, textStatus,errorThrown) {
				showMessage(jqXHR.status,"error");
				showMessage(textStatus,"error");
				showMessage(errorThrown,"error");
			
			}
		})
		
	}
})

$(document).on('click',"#nextButton",function(e){
	e.preventDefault();
	var passportNumber=document.getElementById("passportNumber").value;
	if(passportNumber=="" || isNaN(passportNumber)){
		showMessage("Passport number must be number!","warning")
	}else{
	var choosenSeats=JSON.parse(sessionStorage["choosenSeats"]);
	var idSedista=choosenSeats.shift();
	sessionStorage.setItem("friendNumber", JSON.stringify(2));
	sessionStorage.setItem("choosenSeats", JSON.stringify(choosenSeats));
	var duzina=choosenSeats.length;
	sessionStorage.setItem("numSeats", JSON.stringify(duzina));
	$.ajax({
		type: 'POST',
		url: myReservationUrl+'/'+passportNumber+'/'+idSedista,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
	    success: function(data){
	    	console.log(data);
	    	sessionStorage
			.setItem(
					"flightReservationId",
					JSON
							.stringify(data));
	    	if(duzina>=1){
	    		openCity(e, 'inviteFriend');
	    		$("#inviteFriend").children().remove();
	    		$("#inviteFriend").append("<h2 align='center'>" +
	    				" Friend number "+1+": <h2>");
	    		var table=$('<table align="center"></table>');
	    		var tr=$('<tr></tr>');
	    		tr.append('<td><p>  Name:  </p></td>');
	    		tr.append('<td><input type="text" align="center" id="friendName"></td>');
	    		table.append(tr);
	    		var tr1=$('<tr></tr>');
	    		tr1.append('<td><p>  Last name:  </p></td>');
	    		tr1.append('<td><input type="text" align="center" id="friendLastName"></td>');
	    		table.append(tr1);
	    		var tr2=$('<tr></tr>');
	    		tr2.append('<td><p>  Passport number:  </p></td>');
	    		tr2.append('<td><input type="text" align="center" id="passportNumber"></td>');
	    		table.append(tr2);
	    		var tr3=$('<tr></tr>');
	    		tr3.append('<td><p>  Is in friend list?:  </p></td>');
	    		tr3.append('<td><input type="checkbox" id="checkboxFriend" value="Friend"></td>');
	    		table.append(tr3);
	    		table.append('<button align="center" type="submit" id="inviteMore" class="btn btn-primary" style="background-color: #cc0033">Next</button>');
	    		$("#inviteFriend").append(table);
	    		// $("#inviteFriend").append('<div><input type="checkbox"
				// id="friendList"></div>');
	   
	    	}else{
		    		$("#inviteFriend").empty();
		    		$("#inviteFriend")
					.append(
							'<br><button type="submit" style="background: #cc0033; color: white" id="offerRentacarsButton" style="float: left;">Rentacars</button>');
		    		$("#inviteFriend")
					.append(
							'<br><button type="submit" style="background: #cc0033; color: white" id="offerHotelsButton" style="float: left;/">Hotels</button>');
		    		$("#inviteFriend")
					.append(
							'<br><button type="submit" style="background: #cc0033; color: white" id="finishReservation" style="float: left;/">Finish reservation</button>');
		    		
	    	}
	    },
		error : function(jqXHR, textStatus,errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		
		}
	})
	}
	
})


$(document)
		.on(
				'click',
				'#reserveFlight',
				function(e) {
					e.preventDefault();
					
					var destination = document.getElementById("destinationId").value;
					var startDate = document.getElementById("startDateId").value;
					var endDate = document.getElementById("endDateId").value;

					openCity(e, 'inviteFriend');
					document.getElementById("destinationId").value=destination;
					document.getElementById("startDateId").value=startDate;
					document.getElementById("endDateId").value=endDate;
					
					// $("#reserveFlightInfoDiv").empty();
					var tabela = $('<table align="center"></table>');
					var i = 1;
					var choosenSeats = JSON
							.parse(sessionStorage["choosenSeats"]);
					var tr = $('<tr></tr>');
					tr
							.append('<td><p>Insert your passport number: </p></td><td></td>');
					tr
							.append('<td><input type="text" id="passportNumber"></td>');
					tabela.append(tr);
					tabela.append('<button align="center" type="submit" id="nextButton" style="background-color: #cc0033" class="btn btn-primary">Next</button>');
					$("#inviteFriend").children().remove();
					$("#inviteFriend").append(tabela);
					$("#inviteFriend").append('<br><br>');
				})
$(document)
		.on(
				'click',
				"#confirmReserveFlight",
				function(e) {
					e.preventDefault();
					var choosenSeats = JSON
							.parse(sessionStorage["choosenSeats"]);
					var size = choosenSeats.length;
					sessionStorage.setItem("lengthSeats", JSON.stringify(size));
					var brojPasosa = document.getElementById("passportNumber").value;
					var idjeviPutnika = "";
					var sed = "";
					var l = JSON.parse(sessionStorage["flightId"])
					for (var i = 1; i < size; i++) {
						var s = document.getElementById("reservation" + i + "").value;
						idjeviPutnika += s + " ";

					}
					idjeviPutnika.substring(0, idjeviPutnika.length - 1);
					var sedista = JSON.parse(sessionStorage["choosenSeats"]);
					for ( var s in sedista) {
						sed += sedista[s] + " ";

					}
					sed.substring(0, sed.length - 1);
					sessionStorage.removeItem("choosenSeats");
					$
							.ajax({
								type : 'POST',
								url : urlRoot16,
								headers : createAuthorizationTokenHeader(TOKEN_KEY),
								contentType : 'application/json',
								dataType : "json",
								data : createReservationToJSON(sed,
										idjeviPutnika, l, brojPasosa),
								success : function(data) {
									if(data.message!=undefined){
										showMessage(data.message, data.header)
										
									}else{
									sessionStorage
											.setItem(
													"flightReservationId",
													JSON
															.stringify(data.idFlightReservation));
									var choosenSeats = JSON
											.parse(sessionStorage["flightReservationId"]);
									console.log(choosenSeats);
									var list = data.invitedFriends == null ? []
											: (data.invitedFriends instanceof Array ? data.invitedFriends
													: [ data.invitedFriends ]);
									if (list != []) {
										sendEmail(list);
									}
									showMessage("Successful reservation of a flight, congratulations!","success");
									}
								},
								error : function(jqXHR, textStatus, errorThrown) {
									showMessage(jqXHR.status,"error");
									showMessage(textStatus,"error");
									showMessage(errorThrown,"error");
								}
							})

					$("#flightReservation")
							.append(
									'<button type="submit" style="background: #cc0033; color: white" id="offerRentacarsButton" style="float: left;">Rentacars</button>');
					$("#flightReservation")
							.append(
									'<br><button type="submit" style="background: #cc0033; color: white" id="offerHotelsButton" style="float: left;/">Hotels</button>');
					$("#flightReservation")
							.append(
									'<br><button type="submit" style="background: #cc0033; color: white" id="finishReservation" style="float: left;/">Finish reservation</button>');

				})

$(document).on('click', "#finishReservation", function(e) {
	e.preventDefault();
	var id = JSON.parse(sessionStorage["flightReservationId"])
	console.log(id + "ODHHDHD");
	$.ajax({
		type : 'GET',
		url : finishReservationUrl + "/" + id,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		contentType : 'application/json',
		success : function(data) {
			if (data) {
				sendEmailReservation(data.message, data.email);
				showMessage("Successfully finished reservation.","success");
				openCity(e, 'searchAndFilterFlight');
			} else {

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})
})

function sendEmail(f) {
		$.ajax({
			type : 'POST',
			url : urlRootSendMail,
			contentType : 'application/json',

			data : mailToJson(f.email, f.reservationId),
			success : function(data) {

			},
			error : function(jqXHR, textStatus, errorThrown) {
				showMessage(jqXHR.status,"error");
				showMessage(textStatus,"error");
				showMessage(errorThrown,"error");

			}
		})
}

function sendEmailReservation(message, email) {
	$.ajax({
		type : 'POST',
		url : urlRootSendMail,
		contentType : 'application/json',

		data : mailToJsonReservation(message, email),
		success : function(data) {

		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");

		}
	})
}

$(document)
		.on(
				'click',
				'#a',
				function(e) {
					e.preventDefault();
					var id = $(this).find('div').attr('id');
					if (sessionStorage["choosenSeats"]) {

					} else {
						var choosenSeats = [];
						sessionStorage.setItem("choosenSeats", JSON
								.stringify(choosenSeats));
					}
					var choosenSeats = JSON
							.parse(sessionStorage["choosenSeats"]);
					var i = 0;
					for ( var s in choosenSeats) {
						console.log(s);
						if (choosenSeats[s] == id) {
							i = 1;
							$
									.ajax({
										type : 'GET',
										url : urlRoot15 + "/" + id,
										headers : createAuthorizationTokenHeader(TOKEN_KEY),
										dataType : "json",
										success : function(data) {
											console.log((data.fc).toString())
											if ((data.fc).toString() == "ECONOMY") {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo purple");
											} else if ((data.fc).toString() == "BUSINESS") {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo green");
											} else {
												$("#" + id + "").removeClass();
												$("#" + id + "").addClass(
														"foo blue");
											}
											choosenSeats.splice(s, 1);
											sessionStorage
													.setItem(
															"choosenSeats",
															JSON
																	.stringify(choosenSeats));

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											showMessage(jqXHR.status,"error");
											showMessage(textStatus,"error");
											showMessage(errorThrown,"error");
										}

									})
							break;
						}
					}
					if (i == 0) {
						choosenSeats.push(id);
						sessionStorage.setItem("choosenSeats", JSON
								.stringify(choosenSeats));
						$(this).find('div').removeClass(
								$(this).find('div').attr('class')).addClass(
								"foo grey");
					}
				})

function showRentacarsDest(data, startDate, endDate) {
	sessionStorage.removeItem("choosenSeats");
	var mainStartDate = startDate;
	var mainEndDate = endDate;
	document.getElementById("mainStartDate2").value = mainStartDate;
	document.getElementById("mainEndDate2").value = mainEndDate;
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
		var grade="No grade";
		if (response[counter].number!=0){
			grade=response[counter].score/response[counter].number;
		}
		cell4.innerHTML = grade;
		cell6.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
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

	cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Average Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

}

function showHotelsDest(data) {
	sessionStorage.removeItem("choosenSeats");
	console.log('show rentacars dest called');
	var response = data;
	$("#tableOfHotelsDest").find("tr").remove();
	var tabela = document.getElementById("tableOfHotelsDest");
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
		cell6.innerHTML = '<button style="background: #cc0033; color: white" id=\"'
				+ response[counter].id
				+ '\" class=\"chooseHotel\" class="btn btn-primary">Choose</button>';
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

	cell1.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Name</p>';
	cell2.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Address</p>';
	cell3.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Promotional Description</p>';
	cell4.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Average Grade</p>';
	cell6.innerHTML = '<p style= "font-weight: 200%; font-size:150%"></p>';
	cell5.innerHTML = '<p style= "font-weight: 200%; font-size:150%">Location</p>';

}

$(document).on('click', '#allReservationsButtton', function(e) {
	console.log('all reservations button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showMyReservationsCars();
	showMyReservationsFlights();
	showMyReservationsHotels();
});

$(document).on('click', '.gradeCarResButton', function(e) {
	console.log('grade car res button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	openCity(e, 'gradeRentacar');
	fillGradeRentacarForm(this.id);

});

$(document).on('click', '.gradeFlightResButton', function(e) {
	console.log('grade flight res button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	openCity(e, 'gradeAirline');
	fillGradeFlightForm(this.id);

});

$(document).on('click', '.gradeHotelResButton', function(e) {
	console.log('grade hotel res button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	openCity(e, 'gradeHotel');
	var roomId="roomsResNum"+this.id;
	var rooms =  document
	.getElementById(roomId).innerHTML;
	console.log('ROOOOOOOOOMS: '+rooms);
	fillGradeHotelForm(this.id, rooms);

});
$(document).on('click', '.cancelCarResButton', function(e) {
	console.log('cancel car reservation button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	cancelCarReservation(this.id);
});

$(document).on('click', '.cancelHotelResButton', function(e) {
	console.log('cancel hotel reservation button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	cancelHotelReservation(this.id);
});

$(document).on('click', '#submitRentacarGrade', function(e) {
	console.log('submit rentacar grade clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	saveGradedRentacar();
});

$(document).on('click', '#submitAirlineGrade', function(e) {
	console.log('submit airline grade clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	saveGradedAirline();
});

$(document).on('click', '#submitHotelGrade', function(e) {
	console.log('submit hotel grade clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	saveGradedHotel();
});

$(document).on('click', '#fastReservationButton', function(e) {
	console.log('fast reservation button clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	showCarsForFastRes();
});

$(document).on('click', '.cancelFlightResButton', function(e) {
	console.log('CANCEL FLIGHT RES BUTTON CLICKED');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	cancelFlightReservation(this.id);

});



$(document).on('click', '#offerRentacarsButton', function(e) {
	console.log('offer rentacars clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var destination = document.getElementById("destinationId").value;
	var startDate = document.getElementById("startDateId").value;
	var endDate = document.getElementById("endDateId").value;

	openCity(e, 'showRentacarsInDest');

	document.getElementById("endDateId").value = endDate;
	document.getElementById("startDateId").value = startDate;

	// sada cu da pravim ajax poziv u kojem cu da dobavim rentacars koji su na
	// to dest
	// nadje ti destinaciju preko id, uzmes naziv
	// destinacija ti je u pricipu airline iz koje ces iscupati grad
	// i onda u principu pravis poziv sa tim destination
	var address = "";
	$.ajax({
		type : 'GET',
		url : urlRootFindDest + "/" + destination,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('destination not found');
			} else {
				console.log('destination found');
				address = data.city;
				console.log(address);

				$.ajax({
					type : 'GET',
					url : urlRootFindRentacarDest + "/" + address,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						var list = data == null ? []
						: (data instanceof Array ? data : [ data ]);
						
						
						if (list.length == 0) {
							console.log('rentacar not found');
							$("#tableOfRentacarsDest").find("tr").remove();
							$("#tableOfRentacarsDest").append('<p>No rentacars found in your final destination.</p>')
							
						} else {
							console.log('retnacar found');

							showRentacarsDest(data, startDate, endDate);

						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status,"error");
						showMessage(textStatus,"error");
						showMessage(errorThrown,"error");
					}

				})

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})

	console.log(destination);
	console.log(startDate);
	console.log(endDate);

});

$(document).on('click', '#offerHotelsButton', function(e) {
	console.log('offer hotels clicked');
	e.preventDefault();
	sessionStorage.removeItem("choosenSeats");
	var destination = document.getElementById("destinationId").value;

	openCity(e, 'showHotelsInDest');

	var address = "";
	$.ajax({
		type : 'GET',
		url : urlRootFindDest + "/" + destination,
		headers : createAuthorizationTokenHeader(TOKEN_KEY),
		dataType : "json",
		success : function(data) {
			if (data == null) {
				console.log('destination not found');
			} else {
				console.log('destination found');
				address = data.city;
				console.log(address);

				$.ajax({
					type : 'GET',
					url : urlRootFindHotelDest + "/" + address,
					headers : createAuthorizationTokenHeader(TOKEN_KEY),
					dataType : "json",
					success : function(data) {
						if (data == null) {
							console.log('hotel not found');
						} else {
							console.log('hotel found');

							showHotelsDest(data);

						}
					},
					error : function(jqXHR, textStatus, errorThrown) {
						showMessage(jqXHR.status,"error");
						showMessage(textStatus,"error");
						showMessage(errorThrown,"error");
					}

				})

			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMessage(jqXHR.status,"error");
			showMessage(textStatus,"error");
			showMessage(errorThrown,"error");
		}

	})

});

function createCarResDTO(id, startDate, endDate, passengers) {
	return JSON.stringify({
		"id" : id,
		"startDate" : startDate,
		"endDate" : endDate,
		"passengers" : passengers

	})
}

function createReservationToJSON(sed, idjeviPutnik, l, brojPasosa) {
	return JSON.stringify({
		"seats" : sed,
		"users" : idjeviPutnik,
		"flight" : l,
		"passportNum" : brojPasosa,
	})
}

function roomReservationToJSON(startDate, endDate, discount, hcsIds, roomIds,
		numberHotelDiscount, flightId) {
	return JSON.stringify({
		"startDate" : startDate,
		"endDate" : endDate,
		"discount" : discount,
		"hotelCustomerServices" : hcsIds,
		"roomIds" : roomIds,
		"numberHotelDiscount" : numberHotelDiscount,
		"flightId":        flightId,
	})
}

function mailToJson(emailAddress, id) {
	return JSON
			.stringify({
				"emailAddress" : emailAddress,
				"subject" : "Reservation acceptance",
				"body" : "A friend invited you to a flight accept here:\nhttp://localhost:8080/api/acceptFlightReservation/"
						+ id
						+ " \nreject here \nhttp://localhost:8080/api/rejectFlightReservation/"
						+ id
			})
}

function mailToJsonReservation(message, email) {
	return JSON.stringify({
		"emailAddress" : email,
		"subject" : "Finished reservation",
		"body" : message
	})
}

// vaso :)
$(document).on("click", "#airlineBack", function(e) {
	e.preventDefault();
	openCity(event, 'airlines')
});

$(document).on("click", "#hotelBack", function(e) {
	e.preventDefault();
	openCity(event, 'hotels')
});

$(document).on("click", "#rentacarBack", function(e) {
	e.preventDefault();
	openCity(event, 'rentACars')
});

$(document).on("click", "#backToRentacarReservation", function(e) {
	e.preventDefault();
	
	document.getElementById("pickupDateFast").value="";
	document.getElementById("endDateFast").value="";
	openCity(event, 'rentacarReservation');
	
	document.getElementById("pickupDateCar").value="";
	document.getElementById("endDateCar").value="";
	document.getElementById("pickupCity").value="";
	document.getElementById("endCity").value="";
	document.getElementById("passengers").value="";
	document.getElementById("fromPrice").value="";
	document.getElementById("toPrice").value="";
	
});
$(document).on(
		"click",
		".showRentacarProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			openCity(event, 'rentacarProfile');
			var finalPath = urlRootProfile9 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRootProfile12 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})
						ymaps.ready(init(data.address, "rentacarLocation"));
						$("#rentacarProfileName").html(data.name);
						$("#rentacarProfileAddress").html(data.address);
						$("#rentacarProfilePromoDescription").html(
								data.promotionalDescription);
						$("#rentacarProfileScore").html(data.score);

						var finalPath3 = urlRootProfile15 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		});

$(document).on(
		"click",
		".showHotelProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			openCity(event, 'hotelProfile');
			var finalPath = urlRootProfile8 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRootProfile11 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})
						ymaps.ready(init(data.address, "hotelLocation"));
						$("#hotelProfileName").html(data.name);
						$("#hotelProfileAddress").html(data.address);
						$("#hotelProfilePromoDescription").html(
								data.promotionalDescription);
						$("#hotelProfileScore").html(data.score);

						var finalPath3 = urlRootProfile14 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		});

$(document).on(
		"click",
		".showAirlineProfile",
		function(e) {
			e.preventDefault();
			var ID = this.id;
			openCity(event, 'airlineProfile');
			var finalPath = urlRootProfile7 + "/" + ID;
			$.ajax({
				type : 'GET',
				url : finalPath,
				dataType : "json",
				success : function(data) {
					if (data) {
						var finalPath2 = urlRootProfile10 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})
						ymaps.ready(init(data.address, "airlineLocation"));
						$("#airlineProfileName").html(data.name);
						$("#airlineProfileAddress").html(data.address);
						$("#airlineProfilePromoDescription").html(
								data.promotionalDescription);
						$("#airlineProfileScore").html(data.score);

						var finalPath3 = urlRootProfile13 + "/" + ID;
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
								showMessage(jqXHR.status,"error");
								showMessage(textStatus,"error");
								showMessage(errorThrown,"error");
							}

						})

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMessage(jqXHR.status,"error");
					showMessage(textStatus,"error");
					showMessage(errorThrown,"error");
				}

			})
		});

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
