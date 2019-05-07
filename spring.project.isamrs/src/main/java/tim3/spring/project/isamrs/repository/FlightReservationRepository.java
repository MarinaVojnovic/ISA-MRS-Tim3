package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.CarReservation;
import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightReservation;
import tim3.spring.project.isamrs.model.RegularUser;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long>   {
	public List<FlightReservation> findByRegularUserFlightReservation(RegularUser regularUserFlightReservation);
	public List<FlightReservation> findByNameAndLastName(String name, String lastName);
}
