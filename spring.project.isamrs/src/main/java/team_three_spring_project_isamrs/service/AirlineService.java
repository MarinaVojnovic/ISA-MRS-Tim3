package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Airline;

public interface AirlineService {

	public Airline getOne(long id);
	public List<Airline> getAll();
	public Airline create(Airline airline);
	public Airline save(Airline airline);
	public List<Airline> findByName(String name);
	public List<Airline> findByAddress(String address);
}
