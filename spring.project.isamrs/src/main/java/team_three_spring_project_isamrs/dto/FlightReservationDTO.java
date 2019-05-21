package team_three_spring_project_isamrs.dto;

import team_three_spring_project_isamrs.model.FlightReservation;

public class FlightReservationDTO {
	String seats;
	String users;
	String flight;
	String passportNum;
	
	//Marina dodala
	Long id;
	Double price;
	String startAirport;
	String endAirport;
	String startDate;
	String endDate;
	Integer discount;
	
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public FlightReservationDTO(FlightReservation res) {
		this.id=res.getId();
		this.price=res.getPrice();
		this.passportNum=String.valueOf(res.getPassportNum());
		this.startAirport=res.getFlightReservation().getStartAirline().getName();
		this.endAirport=res.getFlightReservation().getFinalAirline().getName();
		this.seats=String.valueOf(res.getSeat().getId());
		this.startDate=res.getFlightReservation().getDateOfStart();
		this.endDate=res.getFlightReservation().getDateOfEnd();
		this.discount=res.getDiscount();
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getStartAirport() {
		return startAirport;
	}
	public void setStartAirport(String startAirport) {
		this.startAirport = startAirport;
	}
	public String getEndAirport() {
		return endAirport;
	}
	public void setEndAirport(String endAirport) {
		this.endAirport = endAirport;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	
	
	public FlightReservationDTO(String seats, String users, String flight, String passportNum) {
		super();
		this.seats = seats;
		this.users = users;
		this.flight = flight;
		this.passportNum = passportNum;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getPassportNum() {
		return passportNum;
	}
	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}
	public FlightReservationDTO() {
		super();
	}
	
	
}
