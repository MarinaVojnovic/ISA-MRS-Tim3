var urlRoot="http://localhost:8080/getAllFlights";
var urlRoot2="http://localhost:8080/searchFlight"

getAllFlights()

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

function getAllFlights(){
	$.ajax({
		type: 'GET',
		url: urlRoot,
		dataType: "json",
		success: function(data){
			if(data==null){
				
			}else{
				var list=data==null?[]
				: (data instanceof Array? data: [data]);
				if(list.length>0){
					var tableBody=$('<tbody></tbody>')
					var i=1
					$.each(list,function(index,flight){
						var tr=$('<tr></tr>')
						tr.append('<th scope="row">'+i+'</th><td>'+flight.number+'</td><td>'+flight.startDestination.name+'</td><td>'+flight.finalDestination.name+'</td><td>'+flight.cost+'</td><td>'+flight.dateOfStart+'</td><td>'+flight.dateOfEnd+'</td><td>'+flight.lengthOfFlight+'</td><td><button id="chooseFlightButton" name="'+flight.id+'">Choose</button></td>')
						tableBody.append(tr)
					})
					$(".table1").append(tableBody)
				}
			}
		},
		error: function(){
			
		}
	})
}

function searchFlights(){
	var startDestination=document.getElementById("startDestination").value;
	var finalDestination=document.getElementById("finalDestination").value;
	var dateOfFlight=document.getElementById("dateOfFlight").value;
	var dateOfArrival=document.getElementById("dateOfArrival").value;
	if(startDestination=="" || finalDestination=="" || dateOfFlight=="" || dateOfArrival==""){
		alert("All fields must be filled in.")
	}else{
	$.ajax({
		type: 'GET',
		url: urlRoot2,
		dataType: "json",
		data: searchToJson(startDestination,finalDestination,dateOfFlight,dateOfArrival),
		success: function(data){
			alert("Successful")
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})
	
	}
}

$(document).on('click','#searchFlightButton',function(e){
	e.preventDefault();
	searchFlights();
})

$(document).on('click','#chooseFlightButton',function(e){
	e.preventDefault();
	console.log($(this).attr('name'));
})

function searchToJson(startDestination,finalDestination,dateOfFlight,dateOfArrival){
	return JSON.stringify({
		"startDestination":startDestination,
		"finalDestination":finalDestination,
		"startDate" : dateOfFlight,
		"endDate":dateOfArrival,
	})
}
