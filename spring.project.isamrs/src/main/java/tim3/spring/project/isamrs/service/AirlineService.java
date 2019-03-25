package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Airline;

public interface AirlineService {

	public Airline getOne(long id);
	public List<Airline> getAll();
	public Airline create(Airline airline);
	public Airline update(Airline airline);
	public void delete(long id);
}
