package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightClass;
import tim3.spring.project.isamrs.model.Room;
import tim3.spring.project.isamrs.model.Seat;

public interface SeatService {
	public List<Seat> findByFlightAndFc(Flight f,FlightClass fc);
	public Seat getOne(long id);
	public Seat update(Seat seat);
	public List<Seat> getAll();
	public void save(Seat s);

}
