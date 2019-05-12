package team_three_spring_project_isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team_three_spring_project_isamrs.model.FlightReservation;
import team_three_spring_project_isamrs.model.RegularUser;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long>   {
	public List<FlightReservation> findByRegularUserFlightReservation(RegularUser regularUserFlightReservation);
	public List<FlightReservation> findByNameAndLastName(String name, String lastName);
}
