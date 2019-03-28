var urlRoot1 = "http://localhost:8080/findRentacar";
var urlRoot2 = "http://localhost:8080/saveChangesRentACar";
var urlRoot3 = "http://localhost:8080/createCar";

findRentacar();

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

function findRentacar() {
	$
			.ajax({
				type : 'GET',
				url : urlRoot1,
				dataType : "json",
				success : function(data) {
					if (data == null) {
						console.log('profile not found');
					} else {
						document.getElementById("rentacarIdEdit").value = data.id;
						document.getElementById("rentacarNameEdit").value = data.name;
						document.getElementById("rentacarAddressEdit").value = data.address;
						document
								.getElementById("rentacarPromotionalDescriptionEdit").value = data.promotionalDescription;
						document.getElementById("rentacarCustomerServicesEdit").value = data.rentacarCustomerServices;
						document.getElementById("rentacarCarsEdit").value = data.cars;
						document.getElementById("rentacarBranchesEdit").value = data.branches;
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
				"submit",
				"#form1",
				function(e) {
					e.preventDefault();
					var name = document.getElementById("rentacarNameEdit").value;
					var address = document
							.getElementById("rentacarAddressEdit").value;
					var promotionalDescription = document
							.getElementById("rentacarPromotionalDescriptionEdit").value;

					var rentacarCustomerServices = document
							.getElementById("rentacarCustomerServicesEdit").value;
					var cars = document.getElementById("rentacarCarsEdit").value;
					var branches = document
							.getElementById("rentacarBranchesEdit").value;

					var id = document.getElementById("rentacarIdEdit").value;

					if (name == "" || address == ""
							|| promotionalDescription == "") {
						alert('None of the fields is allowed to be empty!');
					} else {

						$
								.ajax({
									type : 'PUT',
									url : urlRoot2,
									data : rentACarToJson(id, name, address,
											promotionalDescription,
											rentacarCustomerServices, cars,
											branches),
									dataType : "json",
									contentType : 'application/json',
									success : function(data) {
										alert("Successful editing, congratulations!");

									},
									error : function(XMLHttpRequest) {
										alert("Error while changing profile information ");
									}

								})
					}

				});

$(document).on("submit", "#form2", function(e) {
	e.preventDefault();

	var name = document.getElementById("addCarName").value;
	var price = document.getElementById("addCarPrice").value;
	var year = document.getElementById("addCarYear").value;
	if (name == "" || price == "" || year == "") {
		alert('None of the fields is allowed to be empty!');
	} else {

		$.ajax({
			type : 'POST',
			url : urlRoot3,
			contentType : 'application/json',
			dataType : "json",
			data : createCarDTOToJson(name, price, year),
			success : function(data) {
				alert("Successful adding car, congratulations!");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
				alert(textStatus);
				alert(errorThrown);

			}
		})
	}

})

function rentACarToJson(id, name, address, promotionalDescription,
		rentacarCustomerServices, cars, branches) {
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"address" : address,
		"promotionalDescription" : promotionalDescription,
		"rentacarCustomerServices" : rentacarCustomerServices,
		"cars" : cars,
		"branches" : branches,

	})
}

function createCarDTOToJson(name, price, year) {
	return JSON.stringify({
		"name" : name,
		"price" : price,
		"year" : year

	})
}