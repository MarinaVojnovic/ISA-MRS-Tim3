package tim3.spring.project.isamrs.model;

import java.util.HashSet;
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
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Destination> destinations = new HashSet<Destination>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Flight> flights = new HashSet<Flight>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "quick_booking_tickets")
	private Set<Ticket> quickBookingTickets = new HashSet<Ticket>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Airplane> airplanes = new HashSet<Airplane>();

	@JsonIgnore
	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@Column(name = "airline_service")
	private Set<AirlineService> airlineServices = new HashSet<AirlineService>();

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

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
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

	public Set<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(Set<Airplane> airplanes) {
		this.airplanes = airplanes;
	}

	public Set<AirlineService> getAirlineServices() {
		return airlineServices;
	}

	public void setAirlineServices(Set<AirlineService> airlineServices) {
		this.airlineServices = airlineServices;
	}

	public Airline(String name, String address, String promotionalDescription, Set<Destination> destinations,
			Set<Flight> flights, Set<Ticket> quickBookingTickets, Set<Airplane> airplanes,
			Set<AirlineService> airlineServices) {
		super();
		this.name = name;
		this.address = address;
		this.promotionalDescription = promotionalDescription;
		this.destinations = destinations;
		this.flights = flights;
		this.quickBookingTickets = quickBookingTickets;
		this.airplanes = airplanes;
		this.airlineServices = airlineServices;
	}

	public Airline(AirlineDTO airlineDTO) {
		this.name = airlineDTO.getAirlineNameRegister();
		this.address = airlineDTO.getAirlineAddressRegister();
		this.promotionalDescription = airlineDTO.getAirlinePromotionalDescription();
		this.destinations = new HashSet<Destination>();
		this.flights = new HashSet<Flight>();
		this.quickBookingTickets = new HashSet<Ticket>();
		this.airplanes = new HashSet<Airplane>();
		this.airlineServices = new HashSet<AirlineService>();
	}

}
