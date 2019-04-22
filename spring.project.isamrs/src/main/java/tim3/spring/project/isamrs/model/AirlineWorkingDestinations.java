package tim3.spring.project.isamrs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "airlineWorkingDestinations")
public class AirlineWorkingDestinations {
	@Id
	@GeneratedValue
	private Long id;
	
	//vise  moze da posalje jedan korisnik
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    private Airline airlineThatWorks;
	
	
	//vise zahteva moze biti poslato jednom korisniku
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="works_id")
    private Airline worksWith;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Airline getAirlineThatWorks() {
		return airlineThatWorks;
	}


	public void setAirlineThatWorks(Airline airlineThatWorks) {
		this.airlineThatWorks = airlineThatWorks;
	}


	public Airline getWorksWith() {
		return worksWith;
	}


	public void setWorksWith(Airline worksWith) {
		this.worksWith = worksWith;
	}


	public AirlineWorkingDestinations(Long id, Airline airlineThatWorks, Airline worksWith) {
		super();
		this.id = id;
		this.airlineThatWorks = airlineThatWorks;
		this.worksWith = worksWith;
	}


	public AirlineWorkingDestinations() {
		super();
	}


	public AirlineWorkingDestinations(Airline airlineThatWorks, Airline worksWith) {
		super();
		this.airlineThatWorks = airlineThatWorks;
		this.worksWith = worksWith;
	}


	

	
	

}
