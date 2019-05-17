package team_three_spring_project_isamrs.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import team_three_spring_project_isamrs.dto.AddFlightDTO;

@Entity
@Table(catalog = "dbtim3", name = "flight")

public class Flight {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "score")
	private Double score;

	@Column(name = "grade_number")
	private Integer gradeNumber;

	@Column(name = "number")
	private String number;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "start_id")
	Airline startAirline;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "final_id")
	Airline finalAirline;

	@Column(name = "cost")
	private double cost;

	@Column(name = "dateOfStart")
	private String dateOfStart;

	@Column(name = "dateOfEnd")
	private String dateOfEnd;

	@Column(name = "numOfStops")
	private int numOfStops;

	@Column(name = "lengthOfFlight")
	private int lengthOfFlight;

	@JsonIgnore
	@OneToMany(mappedBy = "flightStop", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FlightStops> flight = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "flightReservation", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FlightReservation> flightReservation = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;

	@JsonIgnore
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<Seat> seats;

	public Flight(AddFlightDTO flightDTO) {
		super();
		this.number = flightDTO.getFlightNumberRegister();
		this.startAirline = flightDTO.getStartDestinationRegister();
		this.finalAirline = flightDTO.getFinalDestinationRegister();
		this.cost = flightDTO.getCostOfFlight();
		this.dateOfStart = flightDTO.getDateOfFlight();
		this.dateOfEnd = flightDTO.getDateOfArrival();
		this.lengthOfFlight = flightDTO.getLength();
		this.seats = new HashSet<>();
		for (int i = 0; i < flightDTO.getNumOfSeatsEconomy(); i++) {
			this.seats.add(new Seat(this, false,false, FlightClass.ECONOMY,0));
		}
		for (int i = 0; i < flightDTO.getNumOfSeatsBusiness(); i++) {
			this.seats.add(new Seat(this, false,false, FlightClass.BUSINESS,0));
		}
		for (int i = 0; i < flightDTO.getNumOfSeatsFirst(); i++) {
			this.seats.add(new Seat(this,false,false, FlightClass.FIRST,0));
		}
		this.airline = flightDTO.getFlighAirline();
		this.numOfStops = flightDTO.getNumOfStops();
		this.score = 0.0;
		this.gradeNumber = 0;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(String dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public String getDateOfEnd() {
		return dateOfEnd;
	}

	public void setDateOfEnd(String dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}

	public int getLengthOfFlight() {
		return lengthOfFlight;
	}

	public void setLengthOfFlight(int lengthOfFlight) {
		this.lengthOfFlight = lengthOfFlight;
	}

	public List<FlightStops> getFlight() {
		return flight;
	}

	public void setFlight(List<FlightStops> flight) {
		this.flight = flight;
	}

	public Airline getAirline() {
		return airline;
	}

	public Airline getStartAirline() {
		return startAirline;
	}

	public void setStartAirline(Airline startAirline) {
		this.startAirline = startAirline;
	}

	public Airline getFinalAirline() {
		return finalAirline;
	}

	public void setFinalAirline(Airline finalAirline) {
		this.finalAirline = finalAirline;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Flight() {
		this.seats = new HashSet<>();
	}

	public int getNumOfStops() {
		return numOfStops;
	}

	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public List<FlightReservation> getFlightReservation() {
		return flightReservation;
	}

	public void setFlightReservation(List<FlightReservation> flightReservation) {
		this.flightReservation = flightReservation;

	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(Integer gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

}
