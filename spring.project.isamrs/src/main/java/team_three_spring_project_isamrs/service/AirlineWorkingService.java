package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Airline;
import team_three_spring_project_isamrs.model.AirlineWorkingDestinations;

public interface AirlineWorkingService {
	public AirlineWorkingDestinations getOne(long id);
	public List<AirlineWorkingDestinations> getAll();
	public AirlineWorkingDestinations create(AirlineWorkingDestinations awd);
	public void delete(long id);
	public AirlineWorkingDestinations save(AirlineWorkingDestinations awd);
	public List<AirlineWorkingDestinations> findByAirlineThatWorks(Airline air);
}
