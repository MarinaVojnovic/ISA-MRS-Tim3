package tim3.spring.project.isamrs.dto;

public class FlightReservationDTO {
	String seats;
	String users;
	String flight;
	String passportNum;
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
