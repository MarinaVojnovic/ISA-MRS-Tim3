//package tim3.spring.project.isamrs.model;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.OneToOne;
//
//@Entity
//@Table(catalog = "dbtim3", name = "destination")
//public class Destination {
//
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	@Column(name = "name")
//	private String name;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	Airline airline;
//	
//	
////	@JsonIgnore
////	@ManyToOne(fetch = FetchType.EAGER)
////	Flight flight;
//	
//	@OneToMany(mappedBy = "startDestination", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<Flight> startDestination = new HashSet<>();
//
//	@OneToMany(mappedBy = "finalDestination", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<Flight> finalDestination = new HashSet<>();
//	
//	@JsonIgnore
//	@OneToMany(mappedBy = "destination",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
//    private List<FlightStops> destination = new ArrayList<FlightStops>();
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Airline getAirline() {
//		return airline;
//	}
//
//	public void setAirline(Airline airline) {
//		this.airline = airline;
//	}
//	
//
//	public Destination() {
//		super();
//	}
//
//	public Destination(Long id, String name, Airline airline, Set<Flight> startDestination,
//			Set<Flight> finalDestination, List<FlightStops> destination) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.airline = airline;
//		this.startDestination = startDestination;
//		this.finalDestination = finalDestination;
//		this.destination = destination;
//	}
//
//	public Destination(String name) {
//		this.name=name;
//	}
//
//	
//
//}
