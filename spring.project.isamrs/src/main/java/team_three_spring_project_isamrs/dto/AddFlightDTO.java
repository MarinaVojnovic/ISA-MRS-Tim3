package team_three_spring_project_isamrs.dto;

import java.sql.Date;

import team_three_spring_project_isamrs.model.Airline;

public class AddFlightDTO {
	String flightNumberRegister;
	Airline startDestinationRegister;
	Airline finalDestinationRegister;
	Airline flighAirline;
	double costOfFlight;
	Date dateOfFlight;
	Date dateOfArrival;
	int length;
	int numOfSeatsEconomy;
	int numOfSeatsBusiness;
	int numOfSeatsFirst;
	int numOfStops;
	
	public AddFlightDTO(String flightNumberRegister, Airline startDestinationRegister, Airline finalDestinationRegister,
			Airline flighAirline, double costOfFlight, Date dateOfFlight, Date dateOfArrival, int length,
			int numOfSeatsEconomy, int numOfSeatsBusiness, int numOfSeatsFirst, int numOfStops) {
		super();
		this.flightNumberRegister = flightNumberRegister;
		this.startDestinationRegister = startDestinationRegister;
		this.finalDestinationRegister = finalDestinationRegister;
		this.flighAirline = flighAirline;
		this.costOfFlight = costOfFlight;
		this.dateOfFlight = dateOfFlight;
		this.dateOfArrival = dateOfArrival;
		this.length = length;
		this.numOfSeatsEconomy = numOfSeatsEconomy;
		this.numOfSeatsBusiness = numOfSeatsBusiness;
		this.numOfSeatsFirst = numOfSeatsFirst;
		this.numOfStops = numOfStops;
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
