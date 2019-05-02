package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Airline;
import tim3.spring.project.isamrs.model.AirlineWorkingDestinations;

@Repository
public interface AirlineWorkingRepository extends JpaRepository<AirlineWorkingDestinations, Long> {

	public List<AirlineWorkingDestinations> findByAirlineThatWorks(Airline air);
}
