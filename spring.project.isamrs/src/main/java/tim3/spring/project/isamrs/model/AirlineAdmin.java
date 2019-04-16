package tim3.spring.project.isamrs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AirlineAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831516149566167290L;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "airline_id", referencedColumnName = "id")
	private Airline airline;

	public AirlineAdmin() {
		super();
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}