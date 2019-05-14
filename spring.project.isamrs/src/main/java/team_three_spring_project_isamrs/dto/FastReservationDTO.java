package team_three_spring_project_isamrs.dto;

public class FastReservationDTO {
	String flightNumber;
	String startDestination;
	String finalDestination;
	String dateOfFlight;
	String dateOfArrival;
	
	int length;
	double price;
	int discout;
	double total;
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getStartDestination() {
		return startDestination;
	}
	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}
	public String getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}
	public String getDateOfFlight() {
		return dateOfFlight;
	}
	public void setDateOfFlight(String dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}
	public String getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDiscout() {
		return discout;
	}
	public void setDiscout(int discout) {
		this.discout = discout;
	}
	public FastReservationDTO() {
		super();
	}
	public FastReservationDTO(String flightNumber, String startDestination, String finalDestination,
			String dateOfFlight, String dateOfArrival, int length,double price, int discout) {
		super();
		this.flightNumber = flightNumber;
		this.startDestination = startDestination;
		this.finalDestination = finalDestination;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.length = length;
		this.price=price;
		this.discout = discout;
		this.total=Math.round((price*(100-this.discout))/100);
	}

}
