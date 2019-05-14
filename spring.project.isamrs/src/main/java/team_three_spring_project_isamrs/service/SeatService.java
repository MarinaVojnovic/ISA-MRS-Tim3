package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.Flight;
import team_three_spring_project_isamrs.model.FlightClass;
import team_three_spring_project_isamrs.model.Seat;

public interface SeatService {
	public List<Seat> findByFlightAndFc(Flight f, FlightClass fc);

	public Seat getOne(long id);

	public Seat update(Seat seat);

	public List<Seat> getAll();

	public void save(Seat s);
	
	public void delete(long id);

}
