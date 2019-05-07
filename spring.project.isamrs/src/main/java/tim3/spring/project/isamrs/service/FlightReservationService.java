package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.FlightReservation;
import tim3.spring.project.isamrs.model.RegularUser;
import tim3.spring.project.isamrs.model.RoomReservation;
import tim3.spring.project.isamrs.model.User;;

public interface FlightReservationService {
	public FlightReservation getOne(long id);
	public List<FlightReservation> getAll();
	public FlightReservation create(FlightReservation flight);
	public FlightReservation update(FlightReservation flight);
	public void delete(long id);
	void save(FlightReservation fr);
	public List<FlightReservation> findByNameAndLastName(String name, String lastName);
}
