package tim3.spring.project.isamrs.service;

import java.util.Date;
import java.util.List;

import tim3.spring.project.isamrs.model.Destination;
import tim3.spring.project.isamrs.model.Flight;

public interface FlightService {
	public Flight getOne(long id);
	public List<Flight> getAll();
	public Flight create(Flight flight);
	public Flight update(Flight flight);
	//public List<Flight> findByStartDestinationAndFinalDestinationAndDateOfStartAndDateOfEnd(Destination startDestination,Destination finalDestination, Date dateOfStart,Date dateOfEnd);
	public void delete(long id);
	

}
