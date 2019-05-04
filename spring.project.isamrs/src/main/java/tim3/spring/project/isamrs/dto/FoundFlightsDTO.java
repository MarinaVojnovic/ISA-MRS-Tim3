package tim3.spring.project.isamrs.dto;

import java.util.ArrayList;
import java.util.List;

import tim3.spring.project.isamrs.model.Flight;

public class FoundFlightsDTO {
	List<Flight> flightsGo=new ArrayList<Flight>();
	List<Flight> flightReturn=new ArrayList<Flight>();
	
	public List<Flight> getFlightsGo() {
		return flightsGo;
	}

	public void setFlightsGo(List<Flight> flightsGo) {
		this.flightsGo = flightsGo;
	}

	public List<Flight> getFlightReturn() {
		return flightReturn;
	}

	public void setFlightReturn(List<Flight> flightReturn) {
		this.flightReturn = flightReturn;
	}

	public FoundFlightsDTO(List<Flight> flightsGo, List<Flight> flightReturn) {
		super();
		this.flightsGo = flightsGo;
		this.flightReturn = flightReturn;
	}

	public FoundFlightsDTO() {
		super();
	}
	

}
