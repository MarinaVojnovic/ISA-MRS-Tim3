package team_three_spring_project_isamrs.dto;

import java.util.ArrayList;
import java.util.List;

import team_three_spring_project_isamrs.model.Flight;

public class FoundFlightsDTO {
	List<Flight> flightsGo=new ArrayList<>();
	List<Flight> flightReturn=new ArrayList<>();
	
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
