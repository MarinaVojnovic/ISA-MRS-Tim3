package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Destination;
import tim3.spring.project.isamrs.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	public Flight findByNumber(String number);
	public List<Flight> findByStartDestinationAndFinalDestination(Destination startDestination, Destination finalDestination);
}
