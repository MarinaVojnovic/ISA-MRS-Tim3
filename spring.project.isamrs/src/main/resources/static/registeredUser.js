var urlShowHotels = "http://localhost:8080/showHotels";
var urlShowRentacars = "http://localhost:8080/showRentACars"
var urlFindRentacars = "http://localhost:8080/findRentacars";

function showHotels(criteria){
	console.log('showing hotels');
	
	$
	.ajax({
		type : 'GET',
		url : urlShowHotels+"/"+criteria,
		dataType : "json",
		success : function(data) {
			
			var response = data;
			$("#tableOfHotels").find("tr").remove();
		    var tabela=tabela = document.getElementById("tableOfHotels");
			console.log(tabela);
			
			for(var counter in response){
				console.log('counter: '+counter);
				var row = tabela.insertRow(counter);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				
				
				cell1.innerHTML = response[counter].name;
				cell2.innerHTML = response[counter].address;
				cell3.innerHTML = response[counter].promotionalDescription;
				cell4.innerHTML= '<button id=\"'+ response[counter].id+'\" class=\"chooseHotel\" class="btn btn-primary">Choose</button>';
				
				
				
			}
			var row = tabela.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
	
			
			
			cell1.innerHTML = "Name";
			cell2.innerHTML = "Address";
			cell3.innerHTML = "Promotional Description";
			cell4.innerHTML = "";
			
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function showRentacars(criteria){
	console.log('showing rentacars');
	
	$
	.ajax({
		type : 'GET',
		url : urlShowRentacars+"/"+criteria,
		dataType : "json",
		success : function(data) {
			fillTabelRentacars(data);
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);
		}

	})
}

function fillTabelRentacars(data){
	var response = data;
	$("#tableOfRentacars").find("tr").remove();
    var tabela=tabela = document.getElementById("tableOfRentacars");
	console.log(tabela);
	
	for(var counter in response){
		console.log('counter: '+counter);
		var row = tabela.insertRow(counter);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		
		
		cell1.innerHTML = response[counter].name;
		cell2.innerHTML = response[counter].address;
		cell3.innerHTML = response[counter].promotionalDescription;
		cell4.innerHTML = response[counter].grade;
		cell5.innerHTML= '<button id=\"'+ response[counter].id+'\" class=\"chooseRentacar\" class="btn btn-primary">Choose</button>';
		
		
		
	}
	var row = tabela.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	
	
	cell1.innerHTML = "Name";
	cell2.innerHTML = "Address";
	cell3.innerHTML = "Promotional Description";
	cell4.innerHTML = "Grade";
	cell5.innerHTML = "";
	
}

function findRentacars(){
	console.log('find rent a car called');
	var field =  document.getElementById("nameLocationRentACar").value;
	console.log('Field: '+field);
	if (field==""){
		alert("Field is not allowed to be empty, fill it!");
	}
	else{
		
		$
		.ajax({
			type : 'GET',
			url : urlFindRentacars+"/"+field,
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

function rentacarReservation(id){
	console.log('Rentacar reservation called.');
}
$(document).on('click','#hotelsButton',function(e){
	console.log('hotels button clicked');
	//e.preventDefault();
	


	showHotels("bez");
   
});

$(document).on('click','#rentACarButton',function(e){
	console.log('rent a car button clicked');
	//e.preventDefault();
	


	showRentacars("bez");
   
});

$(document).on('click','.sortByName',function(e){
	console.log('sort by name clicked');
	//e.preventDefault();
	


	showHotels("sortByName");
   
});


$(document).on('click','.sortByNameRentACars',function(e){
	console.log('sort by name clicked');
	//e.preventDefault();
	
	showRentacars("sortByName");
   
});

$(document).on('click','.sortByAddress',function(e){
	console.log('sort by address clicked');
	//e.preventDefault();
	


	showHotels("sortByAddress");
   
});

$(document).on('click','.sortByAddressRentACars',function(e){
	console.log('sort by address clicked');
	//e.preventDefault();
	


	showRentacars("sortByAddressRentACars");
   
});

$(document).on('click','.searchRentACarButton',function(e){
	console.log('sort by address clicked');
	//e.preventDefault();
	
	findRentacars();

	$(".sortByNameRentACars").hide();
	$(".sortByAddressRentACars").hide();
   
});

$(document).on('click','.chooseRentacar',function(e){
	//e.preventDefault();
	var _this = $(this);

    console.log('car number'+ '   '+this.id);
    openCity(e, 'rentacarReservation');
    rentacarReservation(this.id);
   
});
