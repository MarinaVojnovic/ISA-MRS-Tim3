package tim3.spring.project.isamrs.repository;

import tim3.spring.project.isamrs.model.FlightStops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightStopRepository extends JpaRepository<FlightStops, Long>{

}
