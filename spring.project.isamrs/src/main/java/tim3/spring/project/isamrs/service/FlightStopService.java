package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.FlightStops;

public interface FlightStopService {
	public FlightStops getOne(long id);
	public List<FlightStops> getAll();
	public FlightStops create(FlightStops flight);
	public FlightStops update(FlightStops flight);
	public void delete(long id);


}
