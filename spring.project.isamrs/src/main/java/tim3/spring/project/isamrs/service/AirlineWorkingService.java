package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineWorkingDestinations;

public interface AirlineWorkingService {
	public AirlineWorkingDestinations getOne(long id);
	public List<AirlineWorkingDestinations> getAll();
	public AirlineWorkingDestinations create(AirlineWorkingDestinations awd);
	public AirlineWorkingDestinations update(AirlineWorkingDestinations awd);
	public void delete(long id);
	public AirlineWorkingDestinations save(AirlineWorkingDestinations awd);
	public List<AirlineWorkingDestinations> findByAirlineThatWorks(Airline air);
}
