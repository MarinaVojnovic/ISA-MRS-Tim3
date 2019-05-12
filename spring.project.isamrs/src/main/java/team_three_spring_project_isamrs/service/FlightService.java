package team_three_spring_project_isamrs.service;

import java.util.Date;
import java.util.List;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.Flight;

public interface FlightService {
	public Flight getOne(long id);

	public List<Flight> getAll();

	public Flight create(Flight flight);

	public void delete(long id);

	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStartAndDateOfEnd(Airline start, Airline end,
			Date startD, Date endD);

	public List<Flight> findByStartAirlineAndFinalAirlineAndDateOfStart(Airline start, Airline end, Date startD);

	public List<Flight> findByStartAirlineAndFinalAirline(Airline start, Airline end);
}
