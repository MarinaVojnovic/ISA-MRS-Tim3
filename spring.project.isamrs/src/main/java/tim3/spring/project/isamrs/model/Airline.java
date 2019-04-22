package tim3.spring.project.isamrs.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tim3.spring.project.isamrs.dto.AirlineDTO;

@Entity
@Table(catalog = "dbtim3", name = "airline")
public class Airline {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "promotional_description")
	private String promotionalDescription;

	@JsonIgnore
	@OneToOne(mappedBy = "airlineAdmin")
	private AirlineAdmin airlineAdmin;

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> flights = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "airlineBooking", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "quick_booking_tickets")
	private Set<Ticket> quickBookingTickets = new HashSet<>();
	
	@OneToMany(mappedBy = "startAirline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> startAirline = new HashSet<>();

	@OneToMany(mappedBy = "finalAirline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> finalAirline = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "airlineStop",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<FlightStops> airlineStop = new ArrayList<FlightStops>();

	

	@JsonIgnore
	@OneToMany(mappedBy = "airlineCustomerService", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "airline_customer_service")
	private Set<AirlineCustomerService> airlineCustomerServices = new HashSet<>();
	
	

	@JsonIgnore
	@OneToMany(mappedBy = "airlineThatWorks",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<AirlineWorkingDestinations> airline = new ArrayList<AirlineWorkingDestinations>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "worksWith",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	private List<AirlineWorkingDestinations> works= new ArrayList<AirlineWorkingDestinations>();

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

	public Airline(String name, String address, String promotionalDescription, 
			Set<Flight> flights, Set<Ticket> quickBookingTickets, 
			Set<AirlineCustomerService> airlineCustomerServices) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.flights = flights;
		this.quickBookingTickets = quickBookingTickets;
		this.airlineCustomerServices = airlineCustomerServices;
	}

	public Airline(AirlineDTO airlineDTO) {
		this.name = airlineDTO.getAirlineNameRegister();
		this.address = airlineDTO.getAirlineAddressRegister();
		this.promotionalDescription = airlineDTO.getAirlinePromotionalDescription();
	}

	public AirlineAdmin getAirlineAdmin() {
		return airlineAdmin;
	}

	public void setAirlineAdmin(AirlineAdmin airlineAdmin) {
		this.airlineAdmin = airlineAdmin;
	}

	public void setAirlineCustomerServices(Set<AirlineCustomerService> airlineCustomerServices) {
		this.airlineCustomerServices = airlineCustomerServices;
	}

}
