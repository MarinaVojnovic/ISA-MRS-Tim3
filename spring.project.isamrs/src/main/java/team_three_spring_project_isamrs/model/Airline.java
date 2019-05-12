package team_three_spring_project_isamrs.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import team_three_spring_project_isamrs.dto.AirlineDTO;

@Entity
@Table(catalog = "dbtim3", name = "airline")
public class Airline {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "score")
	private Double score;

	@Column(name = "grade_number")
	private Integer gradeNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToMany(mappedBy = "airlineAdmin", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<AirlineAdmin> airlineAdmins = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> flights = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airlineBooking", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "quick_booking_tickets")
	private Set<Ticket> quickBookingTickets = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "startAirline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> startAirline = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "finalAirline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> finalAirline = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airlineStop", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<FlightStops> airlineStop = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airlineCustomerService", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "airline_customer_service")
	private Set<AirlineCustomerService> airlineCustomerServices = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airlineThatWorks", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<AirlineWorkingDestinations> airline = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "worksWith", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<AirlineWorkingDestinations> works = new ArrayList<>();

	public Airline() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airline other = (Airline) obj;
		return id == other.id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPromotionalDescription() {
		return promotionalDescription;
	}

	public void setPromotionalDescription(String promotionalDescription) {
		this.promotionalDescription = promotionalDescription;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<Ticket> getQuickBookingTickets() {
		return quickBookingTickets;
	}

	public void setQuickBookingTickets(Set<Ticket> quickBookingTickets) {
		this.quickBookingTickets = quickBookingTickets;
	}

	public Set<AirlineCustomerService> getAirlineCustomerServices() {
		return airlineCustomerServices;
	}

	public void setAirlineServices(Set<AirlineCustomerService> airlineCustomerServices) {
		this.airlineCustomerServices = airlineCustomerServices;
	}

	public Airline(String name, String address, String promotionalDescription, Set<Flight> flights,
			Set<Ticket> quickBookingTickets, Set<AirlineCustomerService> airlineCustomerServices, Integer number,
			Double score, String city) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.flights = flights;
		this.quickBookingTickets = quickBookingTickets;
		this.airlineCustomerServices = airlineCustomerServices;
		this.gradeNumber = number;
		this.score = score;
		this.city = city;
	}

	public Airline(AirlineDTO airlineDTO) {
		this.name = airlineDTO.getAirlineNameRegister();
		this.address = airlineDTO.getAirlineAddressRegister();
		this.promotionalDescription = airlineDTO.getAirlinePromotionalDescription();
		this.gradeNumber = 0;
		this.score = 0.0;
		this.city = airlineDTO.getCity();
	}

	public void setAirlineCustomerServices(Set<AirlineCustomerService> airlineCustomerServices) {
		this.airlineCustomerServices = airlineCustomerServices;
	}

	public Set<AirlineAdmin> getAirlineAdmins() {
		return airlineAdmins;
	}

	public void setAirlineAdmins(Set<AirlineAdmin> airlineAdmins) {
		this.airlineAdmins = airlineAdmins;
	}

	public Set<Flight> getStartAirline() {
		return startAirline;
	}

	public void setStartAirline(Set<Flight> startAirline) {
		this.startAirline = startAirline;
	}

	public Set<Flight> getFinalAirline() {
		return finalAirline;
	}

	public void setFinalAirline(Set<Flight> finalAirline) {
		this.finalAirline = finalAirline;
	}

	public List<FlightStops> getAirlineStop() {
		return airlineStop;
	}

	public void setAirlineStop(List<FlightStops> airlineStop) {
		this.airlineStop = airlineStop;
	}

	public List<AirlineWorkingDestinations> getAirline() {
		return airline;
	}

	public void setAirline(List<AirlineWorkingDestinations> airline) {
		this.airline = airline;
	}

	public List<AirlineWorkingDestinations> getWorks() {
		return works;
	}

	public void setWorks(List<AirlineWorkingDestinations> works) {
		this.works = works;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
