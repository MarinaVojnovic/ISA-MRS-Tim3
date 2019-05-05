package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Flight;
import tim3.spring.project.isamrs.model.FlightReservation;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long>   {

}
