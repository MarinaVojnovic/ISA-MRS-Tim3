package team_three_spring_project_isamrs.dto;

public class FlightDTO {
	String flightNumberRegister;
	String startDestinationRegister;
	String finalDestinationRegister;
	double costOfFlight;
	String dateOfFlight;
	String dateOfArrival;
	int numOfSeatsEconomy;
	int numOfSeatsBusiness;
	int numOfSeatsFirst;
	
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

	public int getNumOfSeatsEconomy() {
		return numOfSeatsEconomy;
	}
	public void setNumOfSeatsEconomy(int numOfSeatsEconomy) {
		this.numOfSeatsEconomy = numOfSeatsEconomy;
	}
	public int getNumOfSeatsBusiness() {
		return numOfSeatsBusiness;
	}
	public void setNumOfSeatsBusiness(int numOfSeatsBusiness) {
		this.numOfSeatsBusiness = numOfSeatsBusiness;
	}
	public int getNumOfSeatsFirst() {
		return numOfSeatsFirst;
	}
	public void setNumOfSeatsFirst(int numOfSeatsFirst) {
		this.numOfSeatsFirst = numOfSeatsFirst;
	}
	public FlightDTO(String flightNumberRegister, String startDestinationRegister, String finalDestinationRegister,
			double costOfFlight, String dateOfFlight, String dateOfArrival, int numOfSeatsEconomy,
			int numOfSeatsBusiness, int numOfSeatsFirst, int numOfStops, String stops) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.numOfSeatsEconomy = numOfSeatsEconomy;
		this.numOfSeatsBusiness = numOfSeatsBusiness;
		this.numOfSeatsFirst = numOfSeatsFirst;
		this.numOfStops = numOfStops;
		this.stops = stops;
	}
	public FlightDTO() {
		super();
	}

	
	
}
