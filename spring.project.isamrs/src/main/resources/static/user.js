var urlRoot="http://localhost:8080/getAllFlights";
var urlRoot2="http://localhost:8080/searchFlight"
var urlRoot3="http://localhost:8080/getAllDestinations"

getAllFlights()
getAllDestinations()

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document).on('click','.tablinks',function(e){
	e.preventDefault();
	$("#foundFlights").empty();
})

function getAllFlights(){
	$.ajax({
		type: 'GET',
		url: urlRoot,
		dataType: "json",
		success: function(data){
			if(data==null){
				
			}else{
				$("#foundFlights").empty();
				var list=data==null?[]
				: (data instanceof Array? data: [data]);
				if(list.length>0){
					var tableBody=$('<tbody></tbody>')
					var i=0
					$.each(list,function(index,flight){
						var tr=$('<tr></tr>')
						tr.append('<th scope="row">'+(++i)+'</th><td>'+flight.number+'</td><td>'+flight.startDestination.name+'</td><td>'+flight.finalDestination.name+'</td><td>'+flight.cost+'</td><td>'+flight.dateOfStart+'</td><td>'+flight.dateOfEnd+'</td><td>'+flight.lengthOfFlight+'</td><td><button id="chooseFlightButton" name="'+flight.id+'" align="center">Choose</button></td>')
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

function getAllDestinations(){
	$.ajax({
		type: 'GET',
		url: urlRoot3,
		dataType: "json",
		success: function(data){
			var list=data==null?[]
			: (data instanceof Array? data: [data]);
			if(data==null){
				
			}else{
				$("#foundFlights").empty();
				var list=data==null?[]
				: (data instanceof Array? data: [data]);
				if(list.length>0){
					var i=0
					$.each(list,function(index,destination){
						var option=$('<option name="'+destination.id+'">'+destination.name+'</option>')
						$("#startDestination").append(option);
					})
					$.each(list,function(index,destination){
						var option=$('<option name="'+destination.id+'">'+destination.name+'</option>')
						$("#finalDestination").append(option);
					})
					
				}
			}
		},
		error: function(){
			
		}
	})
	
}

function searchFlights(){
	
}

$(document).on('click','#searchFlightButton',function(e){
	e.preventDefault();
	var startDestination=document.getElementById("startDestination").value;
	var finalDestination=document.getElementById("finalDestination").value;
	var dateOfFlight=document.getElementById("dateOfFlight").value;
	var dateOfArrival=document.getElementById("dateOfReturn").value;
	var startID = $('option:selected', "#startDestination").attr('name');
	var finalID=$('option:selected',"#finalDestination").attr('name');
	if(startDestination=="" || finalDestination=="" || dateOfFlight=="" || dateOfArrival==""){
		alert("All fields must be filled in.")
	}else{
	$.ajax({
		type: 'POST',
		url: urlRoot2,
		contentType : 'application/json',
		dataType : "json",
		data : searchToJson(startID,finalID,dateOfFlight,dateOfArrival),
		success: function(data){
			var list=data==null?[]
			: (data instanceof Array? data: [data]);
			if(data==null){
				
			}else{
				var list=data==null?[]
				: (data instanceof Array? data: [data]);
				if(list.length>0){
						$("#foundFlights").empty();
						var table=$('<table id="foundFlights" class="table table-hover" border="1" bordercolor="#ccc" font-size></table>');
						table.append('<tr><td>#</td><td>Flight number</td><td>Start destination</td><td>Final destination</td><td>Cost of flight</td><td>Date of flight</td><td>Date of arrival</td><td>Length</td></tr>')
						var i=0
						$.each(list,function(index,flight){
							table.append('<tr><td>'+(++i)+'</td><td>'+flight.number+'</td><td>'+flight.startDestination.name+'</td><td>'+flight.finalDestination.name+'</td><td>'+flight.cost+'</td><td>'+flight.dateOfStart+'</td><td>'+flight.dateOfEnd+'</td><td>'+flight.lengthOfFlight+'</td></tr>')
						})
						$("#foundFlights").append('<p align="center" style="font-size:35px;">Found flights:</p>')
						$("#foundFlights").append(table);
			
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
	
	}
	
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
		"endDate":dateOfArrival
	})
}