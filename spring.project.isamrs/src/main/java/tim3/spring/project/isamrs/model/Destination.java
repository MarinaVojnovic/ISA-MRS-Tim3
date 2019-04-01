package tim3.spring.project.isamrs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToOne;

@Entity
@Table(catalog = "dbtim3", name = "destination")
public class Destination {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	Airline airline;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	Flight flight;

	@OneToOne(mappedBy = "startDestination")
	Flight startFlight;

	@OneToOne(mappedBy = "finalDestination")
	Flight finalFlight;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	Flight presedanje;

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

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Destination(Long id, String name, Airline airline, Flight flight, Flight startFlight, Flight finalFlight,
			Flight presedanje) {
		super();
		this.id = id;
		this.name = name;
		this.airline = airline;
		this.flight = flight;
		this.startFlight = startFlight;
		this.finalFlight = finalFlight;
		this.presedanje = presedanje;
	}

	public Destination() {

	}

	public Destination(String name) {
		this.name = name;
	}

	

}
