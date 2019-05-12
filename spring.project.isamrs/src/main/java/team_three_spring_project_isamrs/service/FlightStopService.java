package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.FlightStops;

public interface FlightStopService {
	public FlightStops getOne(long id);

	public List<FlightStops> getAll();

	public FlightStops create(FlightStops flight);

	public void delete(long id);

}
