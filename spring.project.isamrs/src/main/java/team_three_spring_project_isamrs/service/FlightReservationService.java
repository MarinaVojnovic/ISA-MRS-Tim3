package team_three_spring_project_isamrs.service;

import java.util.List;

import team_three_spring_project_isamrs.model.FlightReservation;

public interface FlightReservationService {
	public FlightReservation getOne(long id);

	public List<FlightReservation> getAll();

	public FlightReservation create(FlightReservation flight);

	public void delete(long id);

	void save(FlightReservation fr);

	public List<FlightReservation> findByNameAndLastName(String name, String lastName);
}
