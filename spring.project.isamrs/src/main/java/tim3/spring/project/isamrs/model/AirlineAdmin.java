package tim3.spring.project.isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AirlineAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831516149566167290L;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "airline_id")
	private Airline airlineAdmin;

	public AirlineAdmin() {
		super();
	}

	public Airline getAirline() {
		return airlineAdmin;
	}

	public void setAirline(Airline airline) {
		this.airlineAdmin = airline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
