var urlRoot1= "http://localhost:8080/createFlight";
var urlRoot2 = "http://localhost:8080/findAirline";
var urlRoot3="http://localhost:8080/saveChangesAirline";

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document).on("click", "#editAirline", function(e) {
	e.preventDefault();
	editAirline();
})


function editAirline(){
		
	$.ajax({
		type : 'GET',
		url : urlRoot2,
		dataType : "json",
		success : function(data) {
			console.log('LOGUJE NESTO');
			if (data==null){
				console.log('profile not found');
			}
			else{
				var id = data.id;
				var name = data.name;
				var address = data.address;
				var promotionalDescription=data.promotionalDescription;
				var destinations=data.destinations;
				var flights = data.flights;
				var quickBookingTickets = data.quickBookingTickets;
				var airplanes=data.airplines;
				var airlineCustomerServices=data.airlineCustomerServices;
				
				
				var forma = $('<form id=\"editAirlineForm\"></form>');
				var tabela = $('<table border="1"></table>');
				var tr=$('<tr></tr>');
				var td1=$('<td>Name</td>');
				var td2=$('<td ><input type=\"text\" name=\"name\" value=\"'+name + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td>Address</td>');
				td2=$('<td><input type=\"text\" name=\"address\" value=\"'+address + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
		
				
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"id\" value=\"'+id + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"promotionalDescription\" value=\"'+promotionalDescription + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				console.log(promotionalDescription);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"destinations\" value=\"'+destinations + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"flights\" value=\"'+flights + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"quickBookingTickets\" value=\"'+quickBookingTickets + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"airlines\" value=\"'+quickBookingTickets + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"airlineCustomerServices\" value=\"'+quickBookingTickets + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td colspan=\"2\"><input id = \"saveAirlineChanges\" type=\"submit\" value=\"Save\"></td>');
				tr.append(td1);
				tabela.append(tr);
				forma.append(tabela);
				$("#LeonaDiv").append(forma);
				
			}
			
			
		},
		error: function(XMLHttpRequest){
			alert("Error while changing profile info");
		}
		
	})
	
}


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
					var costOfFlight=document.getElementById("costOfFlight").value;
					var dateOfFlight=document.getElementById("dateOfFlight").value;
				
					if (flightNumberRegister == ""
							|| startDestinationRegister == ""
							|| finalDestinationRegister == "" || costOfFlight==""
								|| dateOfFlight=="") {
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


function saveAirlineChanges(){
	console.log('save rent a car changes called');
	var name=document.getElementsByName("name")[0].value;
	var address=document.getElementsByName("address")[0].value;
	
	var promotionalDescription = document.getElementsByName("promotionalDescription")[0].value;
	var destinations = document.getElementsByName("destinations")[0].value;
	var flights= document.getElementsByName("flights")[0].value;
	
	var quickBookingTickets= document.getElementByName("quickBookingTickets")[0].value;
	var airplanes= document.getElementByName("airplanes")[0].value;
	var airlineCustomerServices= document.getElementByName("airlineCustomerServices")[0].value;
	
	
	if (name=="" || address=="" || description==""){
		alert('None of the fields is allowed to be empty!');
	}
	
	$.ajax({
		type : 'PUT',
		url : urlRoot3,
		data: airlineToJson(name,address,
				promotionalDescription,destinations,
				flights,quickBookingTickets,airplanes,
				airlineCustomerServices),
		dataType: "json", 
		contentType : 'application/json',
		success : function(data) {
			console.log('profile successfully changed');
			$("#LeonaDiv").children().remove();
			
		},
		error: function(XMLHttpRequest){
			alert("Error while changing profile information ");
		}
		
	})
	
}

$(document).on("click", "#saveAirlineChanges", function(e) {
	e.preventDefault();
	saveAirlineChanges();
})


function createFlightToJSON(flightNumberRegister,
		startDestinationRegister,
		finalDestinationRegister,
		costOfFlight, dateOfFlight) {
	return JSON.stringify({
		"flightNumberRegister" : flightNumberRegister,
		"startDestinationRegister" : startDestinationRegister,
		"finalDestinationRegister" : finalDestinationRegister,
		"costOfFlight": costOfFlight,
		"dateOfFlight": dateOfFlight,
	})
}

function airlineToJson(name,address,
		promotionalDescription,destinations,
		flights,quickBookingTickets,airplanes,
		airlineCustomerServices){
	return JSON.stringify({
		"name": name,
		"address": address,
		"promotionalDescription": promotionalDescription,
		"destinations": destinations,
		"flights": flights,
		"quickBookingTicket": quickBookingTickets,
		"airplanes": airplanes,
		"airlineCustomerServices": airlineCustomerServices,
		
		
	})
	
}