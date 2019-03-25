package tim3.spring.project.isamrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

	public Airline findByName(String name);
}
