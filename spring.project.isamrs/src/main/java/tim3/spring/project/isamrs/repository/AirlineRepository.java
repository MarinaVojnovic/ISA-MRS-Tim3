package tim3.spring.project.isamrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim3.spring.project.isamrs.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

	public List<Airline> findByName(String name);
	public List<Airline> findByAddress(String name);
}
