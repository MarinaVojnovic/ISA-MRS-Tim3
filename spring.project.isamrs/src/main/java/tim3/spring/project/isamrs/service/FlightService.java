package tim3.spring.project.isamrs.service;

import java.util.Date;
import java.util.List;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.Flight;

public interface FlightService {
	public Flight getOne(long id);
	public List<Flight> getAll();
	public Flight create(Flight flight);
	public Flight update(Flight flight);
	public void delete(long id);
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(Airline start,Airline end, Date startD,Date endD);
	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStart(Airline start,Airline end, Date startD);
	public List<Flight> findByStartAirlineAndFinalAirline(Airline start,Airline end);
}
