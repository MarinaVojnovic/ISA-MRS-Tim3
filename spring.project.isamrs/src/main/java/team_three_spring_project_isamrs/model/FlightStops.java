package team_three_spring_project_isamrs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "dbtim3", name = "flightStops")
public class FlightStops {
	@Id
	@GeneratedValue
	private Long id;
	
	//vise presedanja moze imati jedan let
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    private Flight flightStop;
	
	
	//vise presedanja moze imati jednu destinaciju
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="airline_id")
    private Airline airlineStop;

	public FlightStops() {
		
	}

	public FlightStops(Long id, Flight flightStop, Airline airlineStop) {
		super();
		this.id = id;
		this.flightStop = flightStop;
		this.airlineStop = airlineStop;
	}


	public FlightStops(Flight flightStop, Airline airlineStop) {
		super();
		this.flightStop = flightStop;
		this.airlineStop = airlineStop;
	}



	public Flight getFlightStop() {
		return flightStop;
	}


	public void setFlightStop(Flight flightStop) {
		this.flightStop = flightStop;
	}


	public Airline getAirlineStop() {
		return airlineStop;
	}


	public void setAirlineStop(Airline airlineStop) {
		this.airlineStop = airlineStop;
	}
	
	
	
	


	
	
	
	

}
