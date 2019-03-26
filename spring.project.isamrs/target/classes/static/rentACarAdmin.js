
var urlGetProfileInfo = "http://localhost:8080/findRentacar";
var urlSaveChangesRentACar="http://localhost:8080/saveChangesRentACar";
var urlSaveAddedCar="http://localhost:8080/createCar";

function addCarsFormular(){
	console.log('add cars formular');
	$("#makiDiv").children().remove();
	
	
	var forma = $('<form id=\"addCarsForm\"></form>');
	var tabela = $('<table></table>');
	var tr=$('<tr></tr>');
	var td1=$('<td>Name</td>');
	var td2=$('<td><input type=\"text\" name=\"name\"></td>');
	tr.append(td1);
	tr.append(td2);
	tabela.append(tr);
	
	tr=$('<tr></tr>');
	td1=$('<td>Price</td>');
	td2=$('<td><input type=\"text\" name=\"price\"></td>');
	tr.append(td1);
	tr.append(td2);
	tabela.append(tr);
	
	tr=$('<tr></tr>');
	td1=$('<td>Year</td>');
	td2=$('<td><input type=\"text\" name=\"year\"></td>');
	tr.append(td1);
	tr.append(td2);
	tabela.append(tr);

	
	tr=$('<tr></tr>');
	td1=$('<td colspan=\"2\"><input id="saveAddedCarButton" type=\"submit\" value=\"Save\"></td>');
	tr.append(td1);
	tabela.append(tr);
	forma.append(tabela);
	$("#makiDiv").append(forma);
}


function editProfile(){
	$("#makiDiv").children().remove();
		
	$.ajax({
		type : 'GET',
		url : urlGetProfileInfo,
		dataType : "json",
		success : function(data) {
			console.log('LOGUJE NESTO');
			if (data==null){
				console.log('profile not found');
			}
			else{
				console.log('profile successfully found');
				var id = data.id;
				var name = data.name;
				var address = data.address;
				var promotionalDescription=data.promotionalDescription;
				var rentacarCustomerServices=data.rentacarCustomerServices;
				var cars = data.cars;
				var branches = data.branches;
				
				var forma = $('<form id=\"editProfileForm\"></form>');
				var tabela = $('<table></table>');
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
				td1=$('<td>Description</td>');
				td2=$('<td><input type=\"text\" name=\"promotionalDescription\" value=\"'+promotionalDescription + '\"></td>');
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
				td2=$('<td><input type=\"hidden\" name=\"rentacarCustomerServices\" value=\"'+rentacarCustomerServices + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"cars\" value=\"'+cars + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td></td>');
				td2=$('<td><input type=\"hidden\" name=\"branches\" value=\"'+branches + '\"></td>');
				tr.append(td1);
				tr.append(td2);
				tabela.append(tr);
				
				tr=$('<tr></tr>');
				td1=$('<td colspan=\"2\"><input id = \"saveRentACarChanges\" type=\"submit\" value=\"Save\"></td>');
				tr.append(td1);
				tabela.append(tr);
				forma.append(tabela);
				$("#makiDiv").append(forma);
				
			}
			
			
		},
		error: function(XMLHttpRequest){
			alert("Error while changing profile info");
		}
		
	})
	
}

function saveRentACarChanges(){
	console.log('save rent a car changes called');
	var name=document.getElementsByName("name")[0].value;
	var address=document.getElementsByName("address")[0].value;
	var promotionalDescription = document.getElementsByName("promotionalDescription")[0].value;
	
	var rentacarCustomerServices = document.getElementsByName("rentacarCustomerServices")[0].value;
	var cars = document.getElementsByName("cars")[0].value;
	var branches = document.getElementsByName("branches")[0].value;
	
	
	var id=document.getElementsByName("id")[0].value;
	
	if (name=="" || address=="" || promotionalDescription==""){
		alert('None of the fields is allowed to be empty!');
	}
	
	$.ajax({
		type : 'PUT',
		url : urlSaveChangesRentACar,
		data: rentACarToJson(id,name,address,promotionalDescription, rentacarCustomerServices, cars, branches),
		dataType: "json",
		contentType : 'application/json',
		success : function(data) {
			console.log('profile successfully changed');
			$("#makiDiv").children().remove();
			
		},
		error: function(XMLHttpRequest){
			alert("Error while changing profile information ");
		}
		
	})
	
}

function saveAddedCar(){
	
	
	console.log('save added car called');
	
	var name=document.getElementsByName("name")[0].value;
	var price=document.getElementsByName("price")[0].value;
	var year = document.getElementsByName("year")[0].value;
	
	if (name=="" || price=="" || year==""){
		alert('None of the fields is allowed to be empty!');
	}
	
	$("#makiDiv").children().remove();

	$
	.ajax({
		type : 'POST',
		url : urlSaveAddedCar,
		contentType : 'application/json',
		dataType : "json",
		data : createCarDTOToJson(
				name,
				price,
				year),
		success : function(data) {
			alert("Successful adding car, congratulations!");
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			alert(jqXHR.status);
			alert(textStatus);
			alert(errorThrown);

		}
	})
	
	
}


$(document).on("click", "#editProfileButton", function(e) {
	e.preventDefault();
	editProfile();
})

$(document).on("click", "#saveRentACarChanges", function(e) {
	e.preventDefault();
	saveRentACarChanges();
})

$(document).on("click", "#addCarsButton", function(e) {
	e.preventDefault();
	addCarsFormular();
})


$(document).on("click", "#saveAddedCarButton", function(e) {
	console.log('MARINAAAAAAAAA');
	e.preventDefault();
	saveAddedCar();
})

function rentACarToJson(id,name,address,promotionalDescription, rentacarCustomerServices, cars, branches){
	return JSON.stringify({
		"id": id,
		"name": name,
		"address": address,
		"promotionalDescription": promotionalDescription,
		"rentacarCustomerServices": rentacarCustomerServices,
		"cars": cars,
		"branches": branches,
		
		
	})
}

function createCarDTOToJson(name,price,year){
	return JSON.stringify({
		"name": name,
		"price": price,
		"year": year
		
		
	})
}