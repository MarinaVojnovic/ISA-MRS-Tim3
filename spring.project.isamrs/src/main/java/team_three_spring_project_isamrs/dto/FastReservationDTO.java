package team_three_spring_project_isamrs.dto;

public class FastReservationDTO {
	long id;
	String flightNumber;
	String startDestination;
	String finalDestination;
	String dateOfFlight;
	String dateOfArrival;
	
	int length;
	double price;
	int discout;
	
	double total;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public FastReservationDTO() {
		super();
	}
	public FastReservationDTO(long id,String flightNumber, String startDestination, String finalDestination,
			String dateOfFlight, String dateOfArrival, int length,double price, int discout) {
		super();
		this.id=id;
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
