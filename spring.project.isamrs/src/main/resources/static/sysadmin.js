var urlRoot1 = "http://localhost:8080/createAirline";

$(document).on('click', '#logoutClicked', function(e) {
	e.preventDefault();
	window.location.href = "index.html";
})

$(document).on(
    "submit",
    "#form1",
    function(e) {
        e.preventDefault();
        var airlineNameRegister = document
                .getElementById("airlineNameRegister").value;
        var airlineAddressRegister = document
                .getElementById("airlineAddressRegister").value;
        var airlinePromotionalDescription = document
                .getElementById("airlinePromotionalDescription").value;
        if (airlineNameRegister == "" || airlineAddressRegister == ""
                || airlinePromotionalDescription == "") {
            alert('At least one field is blank, please fill it up with proper information!');
        } else {
            $.ajax({
                type : 'POST',
                url : urlRoot1,
                contentType : 'application/json',
                dataType : "json",
                data : createAirlineToJSON(airlineNameRegister, airlineAddressRegister, airlinePromotionalDescription),
                success : function(data) {
                    alert("Congratulations!");
                },
                error : function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status);
                    alert(textStatus);
                    alert(errorThrown);

                }
            })
        }
    });

    function createAirlineToJSON(airlineNameRegister, airlineAddressRegister, airlinePromotionalDescription) {
	return JSON.stringify({
		"airlineNameRegister" : airlineNameRegister,
		"airlineAddressRegister" : airlineAddressRegister,
		"airlinePromotionalDescription" : airlinePromotionalDescription,
	})
}