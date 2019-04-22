package tim3.spring.project.isamrs.dto;

import java.sql.Date;

import tim3.spring.project.isamrs.model.Airline;

public class AddFlightDTO {
	String flightNumberRegister;
	Airline startDestinationRegister;
	Airline finalDestinationRegister;
	Airline flighAirline;
	double costOfFlight;
	Date dateOfFlight;
	Date dateOfArrival;
	int length;
	int numOfSeats;
	int numOfStops;
	public AddFlightDTO(String flightNumberRegister, Airline startDestinationRegister, Airline finalDestinationRegister, Airline flightAirline,
			double costOfFlight, Date dateOfFlight, Date dateOfArrival, int length, int numOfSeats,int numOfStops) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.flighAirline=flightAirline;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.length = length;
		this.numOfSeats = numOfSeats;
		this.numOfStops=numOfStops;
	}
	public AddFlightDTO() {
		super();
	}
	public String getFlightNumberRegister() {
		return flightNumberRegister;
	}
	public void setFlightNumberRegister(String flightNumberRegister) {
		this.flightNumberRegister = flightNumberRegister;
	}
	public Airline getStartDestinationRegister() {
		return startDestinationRegister;
	}
	public void setStartDestinationRegister(Airline startDestinationRegister) {
		this.startDestinationRegister = startDestinationRegister;
	}
	public Airline getFinalDestinationRegister() {
		return finalDestinationRegister;
	}
	public void setFinalDestinationRegister(Airline finalDestinationRegister) {
		this.finalDestinationRegister = finalDestinationRegister;
	}
	public double getCostOfFlight() {
		return costOfFlight;
	}
	public void setCostOfFlight(double costOfFlight) {
		this.costOfFlight = costOfFlight;
	}
	public Date getDateOfFlight() {
		return dateOfFlight;
	}
	public void setDateOfFlight(Date dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}
	public Date getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(Date dateOfArrival) {
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
	public Airline getFlighAirline() {
		return flighAirline;
	}
	public void setFlighAirline(Airline flighAirline) {
		this.flighAirline = flighAirline;
	}
	public int getNumOfStops() {
		return numOfStops;
	}
	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}
	

}
