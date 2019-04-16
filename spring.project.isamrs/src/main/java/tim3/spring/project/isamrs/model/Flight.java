package tim3.spring.project.isamrs.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tim3.spring.project.isamrs.dto.FlightDTO;

@Entity
@Table(catalog = "dbtim3", name = "flight")

public class Flight {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number")
	private String number;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "start_id")
	Destination startDestination;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "final_id")
	Destination finalDestination;

	@Column(name = "cost")
	private double cost;

	@Column(name = "dateOfStart")
	private Date dateOfStart;
	
	@Column(name = "dateOfEnd")
	private Date dateOfEnd;
	
	@Column (name="lengthOfFlight")
	private int lengthOfFlight;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY,mappedBy="flight")
	private Set<Destination> destinations=new HashSet<Destination>();
	
	@JsonIgnore 
	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<Seat> seats;

	public Flight(FlightDTO flightDTO) {
		super();
		this.number = flightDTO.getFlightNumberRegister();
		this.startDestination = new Destination();
		this.finalDestination = new Destination();
		this.cost = flightDTO.getCostOfFlight();
		this.dateOfStart = new Date();
		this.dateOfEnd = new Date();
		this.lengthOfFlight = flightDTO.getLength();
		this.seats=new HashSet<Seat>();
		for(int i=0;i<flightDTO.getNumOfSeats();i++) {
			this.seats.add(new Seat(this,false));
		}
		
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

	public Date getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public Date getDateOfEnd() {
		return dateOfEnd;
	}

	public void setDateOfEnd(Date dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}

	public int getLengthOfFlight() {
		return lengthOfFlight;
	}

	public void setLengthOfFlight(int lengthOfFlight) {
		this.lengthOfFlight = lengthOfFlight;
	}

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	public Airline getAirline() {
		return airline;
	}

	public Destination getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}

	public Destination getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(Destination finalDestination) {
		this.finalDestination = finalDestination;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}



	public Flight() {
		this.seats=new HashSet<Seat>();
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	
}
