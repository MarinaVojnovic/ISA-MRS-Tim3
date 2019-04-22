package tim3.spring.project.isamrs.dto;

public class FlightDTO {
	String flightNumberRegister;
	String startDestinationRegister;
	String finalDestinationRegister;
	double costOfFlight;
	String dateOfFlight;
	String dateOfArrival;
	int length;
	int numOfSeats;
	int numOfStops;
	String stops;
	public String getFlightNumberRegister() {
		return flightNumberRegister;
	}
	public void setFlightNumberRegister(String flightNumberRegister) {
		this.flightNumberRegister = flightNumberRegister;
	}
	public String getStartDestinationRegister() {
		return startDestinationRegister;
	}
	public void setStartDestinationRegister(String startDestinationRegister) {
		this.startDestinationRegister = startDestinationRegister;
	}
	public String getFinalDestinationRegister() {
		return finalDestinationRegister;
	}
	public void setFinalDestinationRegister(String finalDestinationRegister) {
		this.finalDestinationRegister = finalDestinationRegister;
	}
	public double getCostOfFlight() {
		return costOfFlight;
	}
	public void setCostOfFlight(double costOfFlight) {
		this.costOfFlight = costOfFlight;
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
	public int getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public int getNumOfStops() {
		return numOfStops;
	}
	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}
	public String getStops() {
		return stops;
	}
	public void setStops(String stops) {
		this.stops = stops;
	}
	public FlightDTO(String flightNumberRegister, String startDestinationRegister, String finalDestinationRegister,
			double costOfFlight, String dateOfFlight, String dateOfArrival, int length, int numOfSeats, int numOfStops,
			String stops) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.length = length;
		this.numOfSeats = numOfSeats;
		this.numOfStops = numOfStops;
		this.stops = stops;
	}
	public FlightDTO() {
		super();
	}

	
	
}
