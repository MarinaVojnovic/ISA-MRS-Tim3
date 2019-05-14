package team_three_spring_project_isamrs.dto;

import team_three_spring_project_isamrs.model.CarReservation;
import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.HotelReservation;

public class FinishedReservationDTO {
	public FlightReservation flightReservation;
	public CarReservation carReservation;
	public HotelReservation hotelReservation;
	public String message;
	public String email;
	public FinishedReservationDTO(FlightReservation flightReservation, CarReservation carReservation,
			HotelReservation hotelReservation, String message,String email) {
		super();
		this.flightReservation = flightReservation;
		this.carReservation = carReservation;
		this.hotelReservation = hotelReservation;
		this.message = message;
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FinishedReservationDTO() {
		super();
	}
	public FlightReservation getFlightReservation() {
		return flightReservation;
	}
	public void setFlightReservation(FlightReservation flightReservation) {
		this.flightReservation = flightReservation;
	}
	public CarReservation getCarReservation() {
		return carReservation;
	}
	public void setCarReservation(CarReservation carReservation) {
		this.carReservation = carReservation;
	}
	public HotelReservation getHotelReservation() {
		return hotelReservation;
	}
	public void setHotelReservation(HotelReservation hotelReservation) {
		this.hotelReservation = hotelReservation;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
