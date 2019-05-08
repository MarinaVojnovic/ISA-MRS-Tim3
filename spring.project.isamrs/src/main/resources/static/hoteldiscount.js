function getHotelDiscountValue() {
	retVal = localStorage.getItem("hotelDiscount");
	if (retVal) {
		return retVal;
	} else {
		return 3;
	}
}

function setHotelDiscount(value) {
	if (value == 1 || value == 2 || value == 3 || value == 4 || value == 5) {
		localStorage.setItem("hotelDiscount", value);
	}
	return 3;
}